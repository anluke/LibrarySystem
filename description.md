# Library System Code Description

## Overview:
The provided Java code implements a basic library system with functionalities such as adding, removing, and displaying library items. The program supports three types of library items: `Book`, `Thesis`, and `CD/DVD`, each with specific attributes. Additionally, the code includes features like saving items to a CSV file, loading items from a CSV file, and basic error handling.

## Main Components:

### 1. LibraryItem Hierarchy:
- The code defines a hierarchy of library items represented by the abstract class `LibraryItem`. This class is inherited by specific item types (`Book`, `Thesis`, and `CD/DVD`), each with its own set of attributes.

### 2. LibraryUser:
- The `LibraryUser` class represents a user of the library. Users can borrow and return items, and their borrowed items are tracked in a `LinkedList`.

### 3. Lambda Expressions:
- Lambda expressions are used to define customized library operations (`LibraryOperation`) for borrowing and returning items. These expressions add flexibility and allow the definition of operations inline.

- **Usage of Lambda Expressions:**
    - Lambda expressions are utilized in the `LibrarySystem` class to create customized borrowing and returning operations. These operations are applied to each library item when a user borrows or returns an item.

### 4. Example Menu:
- The program presents a main menu for user interaction. The menu looks like this:

  ```
  Library System Menu:
  1. Add Item
  2. Remove Item
  3. Display Borrowed Items
  4. Save Library Items to CSV
  5. Exit
  Enter your choice:
  ```

### 5. Dependencies:
- Ensure you have **JDK 21** installed on your system to run the code.
- **Maven** has been used as the build tool for this project.

### 6. Error Handling and Potential Issues:
- The code includes basic error handling for `IOException` during file operations.
- Input validation is implemented to prevent invalid user inputs, but potential issues may arise if unexpected inputs are provided.

### 7. Example of User Input Validation:
- If a user inputs an invalid choice in the menu or provides incorrect data when adding items, the program prompts the user to enter a valid input.

### 8. Author Name Validation:
- The `getAuthorName` method has been modified to allow spaces in author names, providing a more realistic representation of author information.

### 9. Modular Structure:
- The code is organized into classes, promoting a modular structure that enhances readability and maintainability.

## How to Use:

1. **Running the Code:**
    - Compile and run the `LibrarySystem` class to start the library system program.

2. **Adding Items:**
    - Users can add items to the library by selecting the type of item (Book, Thesis, or CD/DVD) and providing relevant details.

3. **Removing Items:**
    - Items can be removed by entering the title of the item to be removed.

4. **Displaying Borrowed Items:**
    - The program allows users to display a list of borrowed items.

5. **Saving to CSV:**
    - The user can save the current state of the library items to a CSV file (`library_items.csv`).

6. **User Input Validation:**
    - The program validates user inputs to ensure they are within specified ranges and conform to expected formats.

## Modifications:

1. **Author Name Spaces:**
    - The `getAuthorName` method has been modified to allow spaces in author names, facilitating a more realistic representation of author information.

2. **Menu and User Input:**
    - The program has been extended to include a continuous main menu loop, allowing users to perform multiple operations without restarting the program.

## Error Handling and Credits:

### Error Handling and Potential Issues:
- Basic error handling is implemented for `IOException` during file operations. However, additional error scenarios may need consideration.

### Credits:
- The code was contributed to and developed by:
    - Victoriia Chorna
    - Anton Lukenda
    - Ana Rudic
