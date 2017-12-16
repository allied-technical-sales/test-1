## Hello reader!

#### This project contains only 4 files apart from original `README.md`
##### 1. `README_Updated.md`
##### 2. `generate_file.sh`,
##### 3. `view_changes.sh`, and
##### 4. `test.sh`

Attached file "`generated_file.txt`" is the sample only. It will be automatically generated, if does not exist

### To run the project:

Open two terminal screens.

Go to the root folder of 'test-1' on both terminal screens.
e.g. `cd ./test-1`

Run `sh ./generate_file.sh`;

Above command will generate a new file "generated_file.txt" and will write 1 to 10 in increment for one number in each line.

Run it again, `sh ./generate_file.sh`; 

This will start writing new lines with increments using the last line with an interval of 3 seconds between each new line.

This runs for approximately 5 minutes, as it writes next 100 numbers with 3 seconds interval in between.

While running the above command, go to the next terminal screen.

Run `./view_changes.sh`

This command prints out some options on screen as to what kind of tail function needs to be run.

Provide your choice as 1, 2, 3, or 4 to execute selected operation. 

Sit back and view the changes being written down in "`generated_file.txt`"

You can always close this executions by running `CTRL + c`

### Unit Tests

Open a terminal if not already opened.

Go to the root folder of 'test-1'.
e.g. `cd ./test-1`

Run `./test.sh`;