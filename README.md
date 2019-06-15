# Aim Project
## Overview
This is a project for mastering your Fortnite Aim. 
## Requirements
- Java Versions 8+ (Recommended: Java 11)
- Windows/Linux/Mac OS
## Compiling and Execution
### Running from the Command Line
#### Java versions below Java 11
On Windows, ensure Java is in your PATH before running `java`. Otherwise, specify `java` path.
Compile and Execute from /aim_project. These commands WILL NOT WORK in /aim_project/src. 
ENSURE `arc.jar` is in /aim_project. NOT /aim_project/src. 
Linux/Mac OS
```
//CURRENT DIRECTORY: ~/Documents/aim_project$
javac -cp "arc.jar" ./src/*.java
java -classpath "./arc.jar:." src.arc_test
```
Windows
```//CURRENT DIRECTORY /Documents/aim_project>
javac -cp "arc.jar" ./src/*.java
java -classpath "./arc.jar;." src.arc_test
```
#### Java 11
On Java 11, you can directly run the program without `javac`. 
### Running from Geany
On toolbar, click on `Build`. Click `Set Build Commands`. 
If there are existing `Compile` and `Execute` commands, set a new one on the space below. 
#### Set Working Directory
On the right of `Command`, set your `Working Directory` to where `/aim_project` is downloaded. 
For example: 
```
/home/william/Documents/aim_project
```
##### Windows
`Working Directory` might look something like this:
```
C:\Users\William\Downloads\aim_project
```
#### Compile 
##### Windows 
If `/bin/java` isn't set to your user PATH variable, replace `java` and `javac` with respective file locations (`"/path/to/javac"`) and (`"/path/to/java"`) 
##### Windows MacOS Linux
With your working directory set, insert the following compile command
```
javac -cp "arc.jar" ./src/*.java
```
This compiles all `.java` files in `/aim_project/src`. 

#### Execute
After compiling, insert the following command to execute.
```
java -classpath "./arc.jar;." src.arc_test
```
##### Linux MacOS
```
java -classpath "./arc.jar:." src.arc_test
```
This will execute with `arc.jar` in the working directory. It will use `src.arc_test` as `Main` to execute. 

## Source Code
All source code is located in `/aim__project/src`. Images required is located in `/aim_project`.
### Github
https://github.com/WilliamTheBeginner/aim_project/

## TroubleShooting
### Compiling and Executing
Ensure you are in the right directory: `/aim_project/` in order to compile and execute. Commands will not work going into `/aim_project/src`. 

If running in `Geany`, ensure working directory is set in build commands. Otherwise, `Geany` will run from `root`. 

If `java` is not set in `PATH` variable, replace all `java` and `javac` commands with `/path/to/java` and `/path/to/javac` in order to compile and execute.
```
"C:/Program Files/(jdk version)/bin/java"
```
### Other Issues
Institution Email: hoikin.kwan20@ycdsbk12.ca

## Credits
Developer: William Kwan

`arc.jar` library: Alfred Ron Cadawas

Course Manager: Daniel Astorino
