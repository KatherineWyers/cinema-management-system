----------------------------------------------------------------------------
CINEMA MANAGEMENT SYSTEM
----------------------------------------------------------------------------

PROJECT TITLE: Cinema Management System
PURPOSE OF PROJECT: Data management system for a Cinema
VERSION or DATE: 1.0
HOW TO START THIS PROJECT: Compile and build using either manifest-gui or manifest-cli
AUTHOR: Katherine Wyers


COMPILING AND BUILDING THE APPLICATION

1, In the project root directory, create the classes directory
mkdir classes

2, Compile the java files to the classes directory
javac *.java -d classes

The application can be built with the GUI (Java Swing) version or the CLI version.
You can choose which to build by choosing the manifest-gui or manifest-cli files when
building the jar file.

3, In your project root directory, create the build folder
mkdir build

4, Enter the classes directory
cd classes

BUILD AND RUN WITH THE GRAPHICAL JAVA-SWING USER INTERFACE

5, For the GUI version, run the following from inside the classes directory
jar cvfm ../build/cinema-management-system-gui.jar ../manifest-gui.txt *.class ../resources

6, Open the build directory in Finder (Mac), File Manager (Ubuntu) or Windows Explorer (Windows) and double-click on cinema-management-system-gui.jar


BUILD AND RUN WITH THE COMMAND-LINE INTERFACE

5, For the CLI version, run the following from inside the classes directory
jar cvfm ../build/cinema-management-system-cli.jar ../manifest-cli.txt *.class ../resources

6, Navigate to the build directory
cd ../build

7, From inside the build directory, run the jar file
java -jar cinema-management-system-cli.jar
