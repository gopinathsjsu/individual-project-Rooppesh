# Individual Project Rooppesh

**Name** : Rooppesh Sarankapani <br />
**ID** : 015253147

## Project Description

The objective of the Java Application is to create a Inventory Management System for Billing. The application maintains an internal, static database. There is also a threshold on the number of products from each category available. If there are any errors in the program, the system generates an .TXT file in /output. The results are stored in /output in .CSV format.   

## Execution Steps

**Requirements**: Java 17, IntelliJ

### Note: 
1. Input .CSV files are present in /input 
2. Output .CSV and .TXT are present in /output

### Instructions:
1. Clone the repository
2. Change the "baseRepoPath" in /config/config.properties
3. Run the program with Billing.java as the main file
4. Input the file names in /input to check different test cases (eg. "input1", "input2" etc.)

## Class Diagram

![Class Diagram0](https://github.com/gopinathsjsu/individual-project-Rooppesh/blob/45759d765b925bd75a4712d4cc0b477191e8d871/documents/Class%20Diagram.png)

## Design Patterns

1) **Singleton Pattern**: The singleton pattern is a design pattern that restricts the instantiation of a class to one object. ConfigHelper.java and Repository.java follows singleton pattern. <br /> <br />
2) **Factory Pattern**: A Factory Pattern is a design pattern that just define an interface or abstract class for creating an object but let the subclasses decide which class to instantiate. ReaderFactory.java decides which Parser is used. <br /> <br />
3) **Builder Pattern**: The builder pattern is a design pattern designed to provide a flexible solution to various object creation problems. ItemBuilder.java builds Item.java.

