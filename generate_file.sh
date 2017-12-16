#!/bin/bash

# Make sure we have permission to execute
logFile=view_changes.sh
if [ ! -x ${logFile} ]; then
    chmod u+x ${logFile}
fi

testFile=test.sh
if [ ! -x ${testFile} ]; then
    chmod u+x ${testFile}
fi

# Starting with the core functionality of this file
generatedFile=generated_file.txt
linesToAdd=100
interval=3
if [ -f ${generatedFile} ]; then
    lastLine=`tail -n1 ${generatedFile}`
    startAt=`expr ${lastLine} + 1`
    endAt=`expr ${lastLine} + ${linesToAdd}`
    echo "last line reads: ${lastLine}"
    echo "next line should be: ${startAt}"
    echo "new last line should be: ${endAt}"
    generatedText=`seq ${startAt} ${endAt}`
    echo "The file ${generatedFile} exists"
    echo "Appending ${linesToAdd} new lines to the file with an interval of ${interval} seconds between each"
    for i in ${generatedText}; do
        sed -i '$ a '${i} ${generatedFile}
        sleep ${interval}
    done
else
    echo "The file ${generatedFile} does not exist."
    echo "..."
    echo "Creating a new one with initial dummy data of 10 lines."
    seq 1 10 > ${generatedFile}
fi

# execute this file as many times as you want
