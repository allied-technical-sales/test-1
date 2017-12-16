#!/usr/bin/env bash
generatedFile=generated_file.txt

declare -a option
option[1]="tail -n 1 -f"
option[2]="tail -n +1"
option[3]="tail -F"
option[4]="less +F --follow-name" # my personal favorite

for i in "${!option[@]}"; do
    echo ${i}. ${option[${i}]}
done

echo "" # extra line space
echo "Option # 4 is my personal favorite."
echo "Which of the above options would you like to run 1, 2, 3, or 4 ?"

while ! [[ "${choice}" =~ ^[1-4]+$ ]]; do
    if [ -n "${choice}" ]; then
        echo "Please enter numbers between 1 and 4 to express your choice"
    fi
    read choice
done

${option[${choice}]} ${generatedFile}
