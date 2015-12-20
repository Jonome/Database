Database
========

## Notes

- holds account information of users and their data
- will provide promtps for input of data and output information
- will accept username and password, check with the database, and provide needed information and options
- after username and password confirmation, options depend on user's level of security clearance
- options to view/change data

-information of users and account data to be stored in a text file, end of program should edit and save the textfile
- format: |username| |password| |access level| |data1| |data2| |data3|
           String     String     int            String  int     double
- program should also alphabetize username with sorting algorithm at the saving phase of the textfile
- searching for the username should be made easier with sorted data file, starts at letter and looks for username

- for the database text file, first create a file, create a PrintWriter, print the needed information, then set the Scanner to the file and print out the information
- uses java.io.File, java.io.PrintWriter, and java.util.Scanner
- first three are user info, last three are data account info
- ver1, input and output through standard in/out, in IDE
- ver2, GUI and flat file database

## Concepts learned

database management, flat file databases, Hashmaps, GUI, sorting algorithms, Git and GitHub usages
