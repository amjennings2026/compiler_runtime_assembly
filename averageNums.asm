#Program usage:
#  average an unknown amount of numbers until user enters 0
#  variable map:
#    90 = number the user entered
ILOAD  0          #set 0 as sum
STOR  85          #store sum into location 85

ILOAD  0          #set 0 as count
STOR  86          #store count into location 86

READ  90          #read input number
LOAD  90
BZ    14          #break if input number==0

ADD   85          #add number to sum
STOR  85          #store sum at location 85

ILOAD  1          #add 1 to count at location 86
ADD   86
STOR  86          #update count

BR     5          #go to line 5

LOAD  85          #put sum into GPREG
DIV   86          #divide sum by count
STOR  90          #store average in location 90

WRITE 90          #write final answer

HALT  99

