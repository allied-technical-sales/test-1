Back end programmer test 1

Use whatever language you wish.

Please implement "tail" Linux program. We are specifically looking for the -n functionality, but are interested in seeing how you would add more functionality for future releases.

https://www.computerhope.com/unix/utail.htm

We will be grading based on:

1) generic design
2) clean code
3) unit testing
4) documentation


during interview We will add follow up questions, and will request an upgrade to the program.

This test should take between 30 - 90 minutes.

Fork this repo, and submit your test via pull request.
Tests not submitted via pull request will not be reviewed.

# Usage:

Script is a simple implementation of tail command. By default script will return last 10 lines of given file. Implementation also include -n functionality. -n flag can be used for specifing number of file required in output.

#### Syntax:

    python tail.py <file_name>

#### optional:
    -n <number_of_lines>
