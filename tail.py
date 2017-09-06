"""
tail.py

It is simple implementation of tail command of linux. Script will return by default last 10 lines of given file. -n flag with integer value can be used for specify number of lines required in output.

Usage:

Syntax: python tail.py <file_name>

Optional: -n <number_of_line>
"""

import sys

def parse_args():
	args = {}
	if len(sys.argv) < 2:
		print "Syntax Error:\nPlease provide file name.\nUsage: python tail.py <file_name>"

		return False

	elif len(sys.argv) == 2:
		args['file_name'] = str(sys.argv[1])

	elif len(sys.argv) == 3:
		args['file_name'] = str(sys.argv[1])
		args['command'] = str(sys.argv[2])

	elif len(sys.argv) == 4:
		args['file_name'] = str(sys.argv[1])
		args['command'] = str(sys.argv[2])
		args['value'] = str(sys.argv[3])

	else:
		print "Syntax Error:\nPlease use folloeing syntax.\nUsage: python tail.py <file_name> [optional]<command> <value>"

		return False

	return args

def tail(args):
	if len(args) < 2:
		file_name = args['file_name']
		n = 10
	elif len(args) == 2:
		file_name = args['file_name']
		if args['command'] == '-n':
			print"Syntax Error: Insuffiecient argument\nUsage: python tail.py <file_name> [optional]<command> <value>"

		return False

	elif len(args) == 3:
		file_name = args['file_name']
		if args['command'] == '-n':
			try:
				n = int(args['value'])
			except:
				print "Invalid value\nPlease provide integer value"		
		
	out_buff = ""
	i = 0
	with open(file_name) as file_obj:
		for line in reversed(file_obj.readlines()):
			if i < n:
				out_buff = line + out_buff
				i += 1
			else:
				break
	print out_buff

if __name__ == '__main__':
	args = parse_args()
	if args:
		tail(args)
