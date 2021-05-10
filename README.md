# Dijkstra & A Star
This repository contains two Java implementations. One is of Dijkstra's shortest path algorithm and the other is of the A Star algorithm. Both were compared on computational time using the included three topo files. Each file contains increasing amounts of nodes to see how each algorithm responds to more complex networks. The topo files represent graph abstractions where each line in the file represents the cost between two nodes. The first two values in the a line are node numbers and the third value is the cost between them which is used by each of the algorithms for computing the shortest path.

Within the algorithms, these files are read in by entering the correct amount of nodes (4, 8, 16) when prompted. Providing a value other then these three throws an exception. Also, within the code, each of these files is converted to the 2D array called 'costs.'

**To Run and Test the Code Yourself**
Both .java files must be compiled to produce binaries. On my system this was done via a bash terminal using JDK11, however they could easily be compiled in an IDE as well. Once compiled, be sure that you have downloaded the topo.txt files and changed the paths within the code of each .java file to represent the paths to those downloads. The variables to change the paths to are the constants FILE_4, FILE_8, and FILE_16. 

Again, once you run the program, you will initially be prompted for the number of desired nodes. You must enter 4, 8, or 16 in addition to having followed the above instructions in order for the programs to run properly. Entering an node value other than 4, 8, or 16 or not having correctly updated the paths to those variables will throw an exception.

