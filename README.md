# the-Twin
Group Project for CMPT 276 - Introduction to Software Engineering

This project implements a 2D grid-based adventure game where the player must navigate through a map while avoiding enemies, collecting keys to unlock the door and rescue their twin sister. Optional collectibles such as coins and diamonds provide additional points.


## Table of Contents
1. [Navigation Guide](#1-navigation-guide)
2. [Installation](#install)
3. [Getting Started](#start)



<a name="navigation"></a>

## 1. Navigation Guide

```bash
repository
├── Documents/     ## milestone reports
├── twins-game/    ## project root
    ├── out/...         ## production files
    ├── src/main/       ## source folders
        ├── java/.../*.java     ## source codes
        ├── resources           ## png files for GUI
    ├── pom.xml         ## pom file for mavel project
```



<a name="install"></a>

## 2. Installation

This project requires using Maven

Check installation using the following command:
```bash
java -version
mvn -version
```


<a name="start"></a>

## 3. Getting Started

Follow the instructions to run the main script _main.py_:

#### 3.1. Clone and Navigate
```bash
# 1. Clone this repo to your local machine
git clone $THISREPO
# 2. Navigate into the repository directory
cd $THISREPO
# 3. Navigate into the root directory
cd twins-game/
```

#### 3.2. Start Game
```bash
# 1. package game
mvn clean package
# 2. compile
mvn clean compile
# 3. install
mvn clean install
# 4. start
mvn exec:java
```
