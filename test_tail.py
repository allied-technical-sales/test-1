import unittest
from tail import parse_args

class ParseArgsTest(unittest.TestCase):
	#test for general tail
	def test_general(self):
		import sys
		sys.argv = ['tail.py', 'profile']
		self.assertEqual(parse_args(),{'file_name': 'profile'}, "Incorrect arguments for general usage")

	#test for -n
	def test_number(self):
		import sys
		sys.argv = ['tail.py', 'profile', "-n", "10"]
		self.assertEqual(parse_args(),{'file_name': 'profile', 'command': '-n', 'value': '10'}, "Incorrect arguments for -n usage")
	
	#test for insufficient argument
	def test_insufficient(self):
		import sys
		sys.argv = ['tail.py']
		self.assertEqual(parse_args(), False, "Incorrect syntax")
		

if __name__ == '__main__':
    unittest.main()
