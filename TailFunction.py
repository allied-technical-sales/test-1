import os

#Tail function that accept the filename
# and number of lines you want to retrieve
def tail(filename, count, offset=1024):

    f_size = os.stat(filename).st_size
    if f_size == 0:
        return []
    with open(filename, 'r') as f:
        if f_size <= offset:
            offset = int(f_size / 2)
        while True:
            seek_to = min(f_size - offset, 0)
            f.seek(seek_to)
            lines = f.readlines()
            # Checking for an empty file
            if seek_to <= 0 and len(lines) == 0:
                return []
            # If the requested lines is greater than the number of line of file
            if seek_to == 0 and len(lines) < count:
                return lines

            if len(lines) >= (count + 1):
                return lines[count * -1:]
def main():
    # get the name of filename
    fname=input('Enter file name:')
    #number of line
    length=input('Enter number of line that you would like to retrieve:')
    tail_data=tail(fname,length)
    #print the retrieved data
    for data in tail_data:
        print data
main()

# Output:
# Enter file name:'face.py'
# Enter number of line that you would like to retrieve:4
#     elif label.description=="female" or label.description=="girl":
#
#         gender="female"
#
#     print(label.description)
#
# print(gender)
