# DinaWall APP

It is an application written 100% in JavaFX offers the user interface to implement dynamic wallpapers in Unix systems like GNU / Linux and macOS, it implements the DinaWallCore library.

## DinaWallCore

DinaWallApp has been thought of as a decoupled system, consisting of a microkernel **DinaWallCore Project** that is in charge of the central logic and a Graphical Interface **DinaWallApp** for the end user, this allows a much more efficient development or the implementation of new user interfaces independent of the project.

## Dependencies

**DinaWallApp** implements the following libraries:

In addition to downloading the source code, you must download and compile the DinaWallCore project, so you must create a folder outside the project directory named lib and locate within it the compiled dinawallcorelib library resulting from creating the DinaWallCore jar

* [DinaWallCore](https://github.com/NiconDevTeam/dinawallcore)
* JavaFX 14

## Built in Gradle

DinaWallApp has been created using Grade 6 and implements the DinaWallcore library, for the compilation process execute:

* ./gradlew buildJar



