require_relative 'tail'

tests = [
  {cmd: "ruby tail.rb test1.txt", description: "default behavior", expected: "test1 50\ntest1 51\ntest1 52\ntest1 53\ntest1 54\ntest1 55\ntest1 56\ntest1 57\ntest1 58\ntest1 59\n"},
  {cmd: "ruby tail.rb test1.txt test2.txt", description: "two files behavior", expected: "test1 50\ntest1 51\ntest1 52\ntest1 53\ntest1 54\ntest1 55\ntest1 56\ntest1 57\ntest1 58\ntest1 59\ntest2 50\r\ntest2 51\r\ntest2 52\r\ntest2 53\r\ntest2 54\r\ntest2 55\r\ntest2 56\r\ntest2 57\r\ntest2 58\r\ntest2 59\r\n"},
  {cmd: "ruby tail.rb -n 20 test1.txt", description: "-n flag", expected: "test1 40\ntest1 41\ntest1 42\ntest1 43\ntest1 44\ntest1 45\ntest1 46\ntest1 47\ntest1 48\ntest1 49\ntest1 50\ntest1 51\ntest1 52\ntest1 53\ntest1 54\ntest1 55\ntest1 56\ntest1 57\ntest1 58\ntest1 59\n"},
  {cmd: "ruby tail.rb --lines=20 test1.txt", description: "--lines flag", expected: "test1 40\ntest1 41\ntest1 42\ntest1 43\ntest1 44\ntest1 45\ntest1 46\ntest1 47\ntest1 48\ntest1 49\ntest1 50\ntest1 51\ntest1 52\ntest1 53\ntest1 54\ntest1 55\ntest1 56\ntest1 57\ntest1 58\ntest1 59\n"},
  {cmd: "ruby tail.rb --lines=100 test1.txt", description: "k > lines in file", expected: "test1  1\ntest1  2\ntest1  3\ntest1  4\ntest1  5\ntest1  6\ntest1  7\ntest1  8\ntest1  9\ntest1 10\ntest1 11\ntest1 12\ntest1 13\ntest1 14\ntest1 15\ntest1 16\ntest1 17\ntest1 18\ntest1 19\ntest1 20\ntest1 21\ntest1 22\ntest1 23\ntest1 24\ntest1 25\ntest1 26\ntest1 27\ntest1 28\ntest1 29\ntest1 30\ntest1 31\ntest1 32\ntest1 33\ntest1 34\ntest1 35\ntest1 36\ntest1 37\ntest1 38\ntest1 39\ntest1 40\ntest1 41\ntest1 42\ntest1 43\ntest1 44\ntest1 45\ntest1 46\ntest1 47\ntest1 48\ntest1 49\ntest1 50\ntest1 51\ntest1 52\ntest1 53\ntest1 54\ntest1 55\ntest1 56\ntest1 57\ntest1 58\ntest1 59\n"},
]

tests.each_with_index do |test_case, ind|
  puts "\ntest #{ind}: '#{test_case[:cmd]}'"
  puts "description: #{test_case[:description]}"
  test_result = `#{test_case[:cmd]}`
  if test_result == test_case[:expected]
    puts "PASS"
  else
    puts "FAIL, should be"
    p test_case[:expected]
    puts "but instead got"
    p test_result
  end
end
