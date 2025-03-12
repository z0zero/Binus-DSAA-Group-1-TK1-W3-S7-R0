# Library Management System

A simple library management system that demonstrates Object-Oriented Programming (OOP) concepts and array operations in Java.

## Project Structure

- `User.java` - Abstract parent class for system users
- `Admin.java` - Class representing library administrators
- `Member.java` - Class representing library members
- `Book.java` - Class representing books in the library
- `Library.java` - Class managing the collection of books
- `Main.java` - Demonstration of the system
- `ClassDiagram.txt` - ASCII representation of the class diagram

## OOP Concepts Demonstrated

### Inheritance

The system uses inheritance through:

- `User` as a parent class
- `Admin` and `Member` as child classes that inherit from `User`

### Polymorphism

Polymorphism is demonstrated through:

- The abstract `interact()` method in the `User` class
- Different implementations of `interact()` in `Admin` and `Member` classes
- Different ways Admin and Member interact with the Library

## Array Operations

The system demonstrates several array operations:

- Adding books to the library array
- Removing books from the library array
- Searching for books based on title
- Displaying available books
- Managing borrowed books for members

## How to Run

1. Compile all Java files:

   ```
   javac *.java
   ```

2. Run the main class:
   ```
   java Main
   ```

## Features

- Administrators can add, remove, and manage books
- Members can borrow and return books
- The system keeps track of available and borrowed books
- The system demonstrates inheritance and polymorphism in action
