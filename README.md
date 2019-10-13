# Mower  

This Java application is a solution to Blablacar technical test project.

## Project description  

The company X wants to develop an auto-mower for square surfaces.  
The mower can be programmed to go throughout the whole surface. Mower's position is
represented by coordinates (X,Y) and a characters indicate the orientation according to cardinal notations
(N,E,W,S).  
The lawn is divided in grid to simplify navigation.  
For example, the position can be 0,0,N, meaning the mower is in the lower left of the lawn, and oriented to
the north.  
To control the mower, we send a simple sequence of characters. Possible characters are
L,R,F. L and R turn the mower at 90° on the left or right without moving the mower. F means the mower
move forward from one space in the direction in which it faces and without changing the orientation.
If the position after moving is outside the lawn, mower keep it's position. Retains its orientation and go to
the next command.  
We assume the position directly to the north of (X,Y) is (X,Y+1).  
To program the mower, we can provide an input file constructed as follows:  
The first line corresponds to the coordinate of the upper right corner of the lawn. the bottom left corner is
assumed as (0,0).  
The rest of the file can control multiple mowers deployed on the lawn. Each mower has 2 next lines:
The first line gives mower's starting position and orientation as "X Y O". X and Y being the
position and O the orientation.  
The second line gives instructions to the mower to go throughout the lawn. Instructions are
characters without spaces.  
Each mower moves sequentially, meaning that the second mower moves only when the first has fully
performed its series of instructions.  
When a mower has finished, it gives the final position and orientation.  
Example :  
input file  
5 5  
1 2 N  
LFLFLFLFF  
3 3 E  
FFRFFRFRRF  
result  
1 3 N  
5 1 E

The goal is to create a Java application that runs on a JVM ≥ 8 and implements the described behaviour.

## Documentation

The Java doc is available [here].  

## Requirements

Java 8 runtime environment.

## Compiling and Running the application

### Run

Run the application using the bash script:

```
./mower.sh
```

To display the application help run the script with ``` --help ```:
```
./mower.sh --help
```
This will print the following help menu:
```
usage: ./mower.sh [-f <arg>]
 -f,--file <arg>   The mower instructions file absolute path, default /tmp/mower.txt
```

Example,

```
./mower.sh -f mower.txt
```

### Tests

To launch the unit test with maven, run:  

```
mvn test
```

### Build  

An executable jar can be generated using Maven :  
Move to the project root directory ```mower``` and run  
```maven clean install```.  
A jar file ```mower.jar``` will be generated in ```/target``` subdirectory.  In order to make it visible to the bash script it must be moved to the project root directory:  
Move to ```target``` and run :  
```
cp target/mower.jar .
```
Input example :  
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA  
```  

Result :  
```
10:27:46.094 [main] DEBUG com.blablacar.mower.Commander - Mowers set. Here is the initial state of the lawn :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 |   | N |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.111 [main] DEBUG com.blablacar.mower.Mower - The mower 2f9a4872-01c8-479e-a884-b705da3d2653 is coming, brace yourself
10:27:46.111 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 1 L .
10:27:46.112 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 |   | W |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.118 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 2 F .
10:27:46.120 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | W | X |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.149 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 3 L .
10:27:46.153 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | S | X |   |   |   |   |
-----------------------------
 1 |   |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.219 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 4 F .
10:27:46.231 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | S |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.330 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 5 L .
10:27:46.339 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | E |   |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.470 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 6 F .
10:27:46.486 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | E |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.614 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 7 L .
10:27:46.623 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | N |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.720 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 8 F .
10:27:46.730 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   |   |   | E |   |   |
-----------------------------
 2 | X | N |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.835 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 9 F .
10:27:46.850 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | E |   |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:46.971 [main] DEBUG com.blablacar.mower.Mower - Stopping the mower 2f9a4872-01c8-479e-a884-b705da3d2653 at position X: 1, Y: 3, orientation: N.
10:27:46.985 [main] DEBUG com.blablacar.mower.Mower - The mower 9dbcb341-5d02-4d26-a6c2-96ef0002fe6f is coming, brace yourself
10:27:46.988 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 1 F .
10:27:46.998 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | E |   |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.106 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 2 F .
10:27:47.111 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | E |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.167 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 3 R .
10:27:47.169 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | S |
-----------------------------
 2 | X | X |   |   |   |   |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.204 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 4 F .
10:27:47.205 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | S |
-----------------------------
 1 | X | X |   |   |   |   |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.253 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 5 F .
10:27:47.255 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   |   | S |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.311 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 6 R .
10:27:47.314 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   |   | W |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.364 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 7 F .
10:27:47.371 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | W | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.472 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 8 R .
10:27:47.482 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | N | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.564 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 9 R .
10:27:47.571 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | E | X |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.666 [main] DEBUG com.blablacar.mower.Mower - Performing the instruction N° 10 F .
10:27:47.675 [main] DEBUG com.blablacar.mower.Commander - A move has been detected. Here is the lawn status :

 5 |   |   |   |   |   |   |
-----------------------------
 4 |   |   |   |   |   |   |
-----------------------------
 3 |   | N |   | X | X | X |
-----------------------------
 2 | X | X |   |   |   | X |
-----------------------------
 1 | X | X |   |   | X | E |
-----------------------------
 0 |   |   |   |   |   |   |
-----------------------------
     0 | 1 | 2 | 3 | 4 | 5 |

10:27:47.814 [main] DEBUG com.blablacar.mower.Mower - Stopping the mower 9dbcb341-5d02-4d26-a6c2-96ef0002fe6f at position X: 5, Y: 1, orientation: E.
------------------------------------------
Final mowers positions:
1 3 N
5 1 E
------------------------------------------
```
[here]: <https://mehdi-aouadi.github.io/mower/>  