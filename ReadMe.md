# Employee Management System (Java + SQLite)

Console-based application to manage employee records with full CRUD, file persistence, and SQLite database integration.

## Features
- Add, View, Update, Delete employees
- Duplicate ID validation
- Persistent storage (SQLite + file backup)
- Modular OOP design

## Tech Stack
- Java (OOP)
- SQLite (JDBC)
- File I/O

## Setup

### 1) Download dependencies
- sqlite-jdbc (xerial)
- slf4j-api
- slf4j-nop

Place them in the project root.

### 2) Compile
```bash
javac -cp ".:sqlite-jdbc.jar:slf4j-api.jar:slf4j-nop.jar" *.java
```

### 3) Run
```bash
java -cp ".:sqlite-jdbc.jar:slf4j-api.jar:slf4j-nop.jar" Main
```

## Project Structure
```
Employee.java
EmployeeService.java
Database.java
Main.java
```

## Sample Usage
1 → Add employee  
2 → View employees  
3 → Delete  
4 → Update  

## Future Improvements
- GUI (Java Swing)
- REST API (Spring Boot)
- MySQL/PostgreSQL integration