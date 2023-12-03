package org.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LibrarySystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<LibraryItem> libraryItems = new ArrayList<>();

        // Main menu loop
        while (true) {
            displayMainMenu();

            int choice = getUserChoice(scanner, 1, 5);

            switch (choice) {
                case 1:
                    addItem(libraryItems, scanner);
                    break;
                case 2:
                    removeItem(libraryItems, scanner);
                    break;
                case 3:
                    displayBorrowedItems(libraryItems);
                    break;
                case 4:
                    saveLibraryItems(libraryItems, "library_items.csv");
                    System.out.println("Library items saved to library_items.csv");
                    break;
                case 5:
                    System.out.println("Exiting the Library System. Goodbye!");
                    System.exit(0);
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Library System Menu:");
        System.out.println("1. Add Item");
        System.out.println("2. Remove Item");
        System.out.println("3. Display Borrowed Items");
        System.out.println("4. Save Library Items to CSV");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addItem(List<LibraryItem> libraryItems, Scanner scanner) {
        System.out.println("Select the type of item to add:");
        System.out.println("1. Book");
        System.out.println("2. Thesis");
        System.out.println("3. CD/DVD");
        System.out.print("Enter your choice: ");

        int itemType = getUserChoice(scanner, 1, 3);

        switch (itemType) {
            case 1:
                addBook(libraryItems, scanner);
                break;
            case 2:
                addThesis(libraryItems, scanner);
                break;
            case 3:
                addCD(libraryItems, scanner);
                break;
        }
    }

    private static void addBook(List<LibraryItem> libraryItems, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        String author = getAuthorName(scanner);

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, isbn);
        libraryItems.add(book);
        System.out.println("Book added successfully.");
    }

    private static void addThesis(List<LibraryItem> libraryItems, Scanner scanner) {
        System.out.print("Enter thesis title: ");
        String title = scanner.nextLine();
        String author = getAuthorName(scanner);

        System.out.print("Enter topic: ");
        String topic = scanner.nextLine();
        System.out.print("Enter abstract: ");
        String abstractText = scanner.nextLine();
        System.out.print("Enter date published: ");
        String datePublished = scanner.nextLine();

        Thesis thesis = new Thesis(title, author, topic, abstractText, datePublished);
        libraryItems.add(thesis);
        System.out.println("Thesis added successfully.");
    }

    private static void addCD(List<LibraryItem> libraryItems, Scanner scanner) {
        System.out.print("Enter CD/DVD title: ");
        String title = scanner.nextLine();
        String author = getAuthorName(scanner);

        System.out.print("Enter producer: ");
        String producer = scanner.nextLine();
        System.out.print("Enter director: ");
        String director = scanner.nextLine();
        System.out.print("Enter playtime (in minutes): ");
        int playtime = getUserChoice(scanner, 1, Integer.MAX_VALUE);
        // Assuming playtime must be a positive integer

        CD cd = new CD(title, author, producer, director, playtime);
        libraryItems.add(cd);
        System.out.println("CD/DVD added successfully.");
    }

    private static void removeItem(List<LibraryItem> libraryItems, Scanner scanner) {
        System.out.print("Enter the title of the item to remove: ");
        String titleToRemove = scanner.nextLine();

        libraryItems.removeIf(item -> item.title.equals(titleToRemove));
        System.out.println("Item removed successfully.");
    }

    private static void displayBorrowedItems(List<LibraryItem> libraryItems) {
        System.out.println("Borrowed Items:");
        // Lambda expression to filter and print borrowed items
        libraryItems.stream()
                .filter(item -> !item.isAvailable())
                .forEach(item -> System.out.println(item.getType() + ": " + item.title + " by " + item.author));
    }

    private static void saveLibraryItems(List<LibraryItem> items, String fileName) {
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(fileName), CSVFormat.DEFAULT)) {
            // Lambda expression to print each item's details to the CSV file
            items.forEach(item -> {
                try {
                    csvPrinter.printRecord(item.getType(), item.title, item.author, item.availability);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getUserChoice(Scanner scanner, int minValue, int maxValue) {
        int choice = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= minValue && choice <= maxValue) {
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between " + minValue + " and " + maxValue);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return choice;
    }

    private static String getAuthorName(Scanner scanner) {
        String author = "";
        while (author.isEmpty() || !author.matches("^[a-zA-Z\\s]+$")) {
            System.out.print("Enter author name (letters and spaces only): ");
            author = scanner.nextLine();
        }
        return author;
    }

    // Functional interface for library operations
    interface LibraryOperation {
        void perform(LibraryItem item);
    }

    abstract static class LibraryItem {
        protected String title;
        protected String author;
        protected boolean availability;

        public LibraryItem(String title, String author) {
            this.title = title;
            this.author = author;
            this.availability = true;
        }

        public static List<LibraryItem> loadLibraryItems(String fileName) {
            // Load library items from CSV file
            // Implementation not provided for brevity
            return new ArrayList<>();
        }

        public void returnItem(LibraryItem item, LibraryOperation returnOperation) {
            // Lambda expression for returning an item
            returnOperation.perform(item);
            // Assuming borrowedAssets is properly managed
            // ClassPrinterImpl.MapNodeImpl borrowedAssets = null;
            // borrowedAssets.remove(item);
        }

        private boolean isAvailable() {
            return availability;
        }

        private void borrow() {
            if (availability) {
                availability = false;
                System.out.println("Item " + title + " borrowed successfully.");
            } else {
                System.out.println("Item " + title + " is not available for borrowing.");
            }
        }

        private void returnItem() {
            availability = true;
            System.out.println("Item " + title + " returned successfully.");
        }

        public abstract String getType();
    }

    static class Book extends LibraryItem {
        private String ISBN;

        public Book(String title, String author, String ISBN) {
            super(title, author);
            this.ISBN = ISBN;
        }

        @Override
        public String getType() {
            return "Book";
        }

        public void setISBN(String ISBN) {
            this.ISBN = ISBN;
        }
    }

    static class Thesis extends LibraryItem {
        private String topic;
        private String abstractText;
        private String datePublished;

        public Thesis(String title, String author, String topic, String abstractText, String datePublished) {
            super(title, author);
            this.topic = topic;
            this.abstractText = abstractText;
            this.datePublished = datePublished;
        }

        @Override
        public String getType() {
            return "Thesis";
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getAbstractText() {
            return abstractText;
        }

        public void setAbstractText(String abstractText) {
            this.abstractText = abstractText;
        }

        public String getDatePublished() {
            return datePublished;
        }

        public void setDatePublished(String datePublished) {
            this.datePublished = datePublished;
        }
    }

    static class CD extends LibraryItem {
        private String producer;
        private String director;
        private int playtime;

        public CD(String title, String author, String producer, String director, int playtime) {
            super(title, author);
            this.producer = producer;
            this.director = director;
            this.playtime = playtime;
        }

        @Override
        public String getType() {
            return "CD/DVD";
        }

        public String getProducer() {
            return producer;
        }

        public void setProducer(String producer) {
            this.producer = producer;
        }

        public String getDirector() {
            return director;
        }

        public void setDirector(String director) {
            this.director = director;
        }

        public int getPlaytime() {
            return playtime;
        }

        public void setPlaytime(int playtime) {
            this.playtime = playtime;
        }
    }
}
