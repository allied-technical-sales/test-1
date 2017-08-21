# tail.rb
# author: Ryan Wong (r25wong@gmail.com)
# requirements: tested with ruby 2.4.0
#
# usage:
# ruby tail.rb [OPTION]... [FILE]...
#
# help:
# ruby tail.rb -h
#
# example usage:
# ruby tail.rb -n 20 test1.txt
#
# tests:
# ruby tail_test.rb

require 'optparse'


# use handy ruby optparse to parse flags
options = {}
OptionParser.new do |opts|
  # message when given -h flag
  opts.banner = "Usage: ruby tail.rb [OPTION]... [FILE]..."

  # debug output
  opts.on("-d", "--debug", "Debug output") do |d|
    options[:debug] = d
  end

  # -n, --lines=K flag
  opts.on("-n", "--lines=K", "Output the last K lines, instead of the default of the last 10; alternatively, use \"-n +K\" to output lines starting with the Kth.") do |k|
    if k.include? "+"
      options[:from_start] = true
      options[:lines] = [k.to_i - 1, 0].max
    else
      options[:from_start] = false
      options[:lines] = k.to_i
    end
  end
end.parse!

# set default for :lines option to 10
options[:lines] ||= 10

# output some useful info if debugging
if options[:debug]
  p options
  p ARGV
end

# read from the back, how much to read at a time
SEEK_AMOUNT = 65536

if options[:lines] != nil
  # remaining arguments are assumed to be filenames.
  ARGV.each do |filename|
    begin
      tail_lines = ""
      f1 = File.open(filename)
      if f1.size <= SEEK_AMOUNT # if the file is smaller than SEEK_AMOUNT, read the whole thing at once
        if options[:from_start] # -n +K, start reading from first K lines
          f1lines = f1.readlines
          tail_lines = f1lines.last([f1lines.size - options[:lines], 0].max)
        else
          tail_lines = f1.readlines.last(options[:lines])
        end
      elsif options[:from_start]

        seek_amount = SEEK_AMOUNT

        # initialize buffer
        f1buffer = ""

        overflow = false

        # keep looping until we have enough "\n" in the buffer
        while f1buffer.count("\n") < options[:lines]
          # read from position, seek_amount amount, a prepend to buffer
          f2buffer = f1.read(seek_amount)

          # another edgecase, if we finish reading everything and we still don't have enough lines, just don't print anything
          # otherwise we might accidentally add \n back in
          overflow = true and break if f2buffer.nil?
          f1buffer += f2buffer
        end

        unless overflow
          # small edgecase if we split on \n and \n is the last character, it disappears so we have to readd it later
          readd = f1buffer[-1] == "\n" ? "\n" : ""
          f1lines = (f1buffer).split("\n")

          # this simply takes the "remaining" lines after the first +K lines and adds them to the rest of the file
          # NOTE: it is possible that the line is cut in half since we are reading by arbitrary number of bytes
          tail_lines = (f1lines.last([f1lines.size - options[:lines], 0].max).join("\n") || "") + readd + f1.read
        end
      else # otherwise, seek to the end and keep adding text to the buffer until we have enough lines
        seek_amount = SEEK_AMOUNT

        # seek to the position where we want to start reading from
        # i.e. seek_amount from the end of the file
        f1.seek(-seek_amount, :END)

        # initialize buffer
        f1buffer = ""

        # keep looping until we have enough "\n" in the buffer
        while f1buffer.count("\n") <= options[:lines]

          # read from position, seek_amount amount, a prepend to buffer
          f1buffer = f1.read(seek_amount) + f1buffer

          # read moves the position forward, so we need to move it back to where we "started"
          f1.seek(-seek_amount, :CUR)

          # if current position is less than seek_amount, we just read the rest and exit the loop
          # we might not have enough lines, but that is ok
          if f1.tell <= seek_amount
            tell = f1.tell
            f1.seek(-tell, :CUR)
            f1buffer = f1.read(tell) + f1buffer
            break
          else # otherwise, move position further along and prepare for another read
            f1.seek(-seek_amount, :CUR)
          end
        end
        # when we split the buffer by \n, we want to add \n back in to each line to produce identical output to File.readlines above
        tail_lines = f1buffer.split("\n").last(options[:lines]).map{|x| "#{x}\n"}
      end
      # edge case, if we print nothing it will still print a line
      puts tail_lines unless tail_lines == ""
    rescue Exception => e
      puts e.message
      puts e.backtrace.inspect
    end
  end
end
