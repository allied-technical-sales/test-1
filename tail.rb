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
  opts.on("-n", "--lines=K", Integer, "Output the last K lines, instead of the default of the last 10; alternatively, use \"-n +K\" to output lines starting with the Kth.") do |k|
    options[:lines] = k
  end
end.parse!

# set default for :lines option to 10
options[:lines] ||= 10

# output some useful info if debugging
if options[:debug]
  p options
  p ARGV
end


if options[:lines] != nil
  # remaining arguments are assumed to be filenames.
  ARGV.each do |filename|
    begin
      f1 = File.new(filename)
      puts f1.readlines.last(options[:lines])
    rescue Exception => e
      puts e.message
      puts e.backtrace.inspect.join("\n")
      return
    end
  end
end
