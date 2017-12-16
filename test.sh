#!/bin/bash

function executableFile()
{
    if [ -x $1 ]; then
        return 1
    fi
    return 0
}

function executableEquals()
{
    msg=$1; shift
    expected=$1; shift
    actual=$1; shift
    echo -n "$msg: "
    if [ "$expected" != "$actual" ]; then
        echo "FAILED: EXPECTED=$expected ACTUAL=$actual"
    else
        echo PASSED
    fi
}

function linesToAdd()
{
    return $(($1+$2))
}

function assertEquals()
{
    msg=$1; shift
    expected=$1; shift
    actual=$1; shift
    echo -n "$msg: "
    if [ "$expected" != "$actual" ]; then
        echo "FAILED: EXPECTED=$expected ACTUAL=$actual"
    else
        echo PASSED
    fi
}

logFile=view_changes.sh
executableFile ${logFile}
executableEquals "File ${logFile} is executable" 1 $?

testFile=test.sh
executableFile ${testFile}
executableEquals "File ${testFile} is executable" 1 $?

linesToAdd 10 100
assertEquals "Adding two numbers" 110 $?