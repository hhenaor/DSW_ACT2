# DSW_Act2

## mpNotes (JSP version)

Simple free, open source grades taking app made with JAVA/JSP and Glassfish

## Requirements

- [Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html)
- [Glassfish 7.x](https://glassfish.org/download.html)
- [NetBeans](https://netbeans.apache.org/front/main/download/nb25/)

## How to build

### Using NetBeans

The easiest way to run the project locally is to
- Copy the project with git:
 ````git
 git clone https://github.com/hhenaor/DSW_ACT2.git
 ````
- Open it with NetBeans
> [!IMPORTANT] 
>
> You need to create a new library 
> 
> 1. Downloading the [MySQL Connector](https://downloads.mysql.com/archives/c-j/) **PLATFORM INDEPENDENT**
> 
> 2. Creating a library called "MYSQL"
>
> 3. Add ``.jar`` of the connector as Classpath and Sources

- Execute MySQL and create the database with the ``squema.sql`` file. 

- Finally run

### Using ``build.cmd``

Using the ``build.cmd`` script automatically downloads, compiles and executes the project

***MAY NOT WORK FOR ANY REASON, IT IS SAFER TO USE NETBEANS***

## License

This project is licensed under the terms of the GNU General Public License version 3.0 (GPLv3).
See the LICENSE file for details.
