package src.main;
import src.book.*;
import src.inventory.*;
import src.services.*;


public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        float Balance = 1000.0f;

        Book paperBook = new Paperbook("692-1213448867", "Java Programming", 2023, 45.00f, "PDF");
        Book eBook = new EBook("285-1111111111", "How to enter fawry as an intern", 2024, 30.00f);
        Book showcaseBook = new Showcasebook("535-9999991268", "Software Architecture from zero to hero", 2025, 0.0f);
        Book outdatedBook = new Paperbook("123-0000000001", "Advanced oop", 2000, 20.00f, "Word");

        
        // Test 1: Adding books to inventory
        System.out.println("Test 1: Adding books to inventory");
        try {
            inventory.addBook(paperBook, 10);
            inventory.addBook(eBook, 5);
            inventory.addBook(showcaseBook, 1);
            inventory.addBook(outdatedBook, 3); // will be removed in test case 9
            System.out.println("Books added :) Total unique items: " + inventory.getInventoryItems().size());
            inventory.printInventory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        

        // Test 2: Adding more amount of an existing book
        System.out.println("Test 2: Adding more amount of an existing book");
        try {
            System.out.println("Before: Paper book quantity = " + inventory.findInventoryItem(paperBook.getIsbn()).getQuantity());
            inventory.addBook(paperBook, 5); // Add 5 more to existing stock
            System.out.println("After: Paper book quantity = " + inventory.findInventoryItem(paperBook.getIsbn()).getQuantity());
            System.out.println("Adding more books successful :)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        
        
        // Test 3: Successful book purchase
        System.out.println("Test 3: Successful book purchase");
        try {
            float total1 = inventory.buyBook(paperBook.getIsbn(), 2, "karim@gmail.com", "Cairo, Egypt", Balance);
            System.out.println("Purchase successful :) Total paid: " + total1);
            System.out.println("Remaining stock: " + inventory.findInventoryItem(paperBook.getIsbn()).getQuantity());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        

        // Test 4: Failed book purchase - Insufficient balance
        System.out.println("Test 4: Failed book purchase - Insufficient balance");
        try {
            float total2 = inventory.buyBook(paperBook.getIsbn(), 2, "Karim@gmail.com", "Cairo, Egypt", 10.0f); // Only $10, but need $59.98
            System.out.println("Purchase result failed :(");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");


        
        // Test 5: Successful book removal
        System.out.println("Test 5: Successful book removal");
        System.out.println("Before removal - eBook stock: " + inventory.findInventoryItem(eBook.getIsbn()).getQuantity());
        try {
            inventory.removeBook(eBook, 2, Balance);
            System.out.println("Removal successful :)");
            System.out.println("After removal - eBook stock: " + inventory.findInventoryItem(eBook.getIsbn()).getQuantity());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        

        // Test 6: Failed book removal - Not enough stock
        System.out.println("Test 6: Failed book removal - Not enough stock");
        try {
            inventory.removeBook(eBook, 10, Balance); // Only 3 left, trying to remove 10
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");

        // Test 7: Failed book purchase - Showcase book
        System.out.println("Test 7: Failed book purchase - Showcase book");
        try {
            float total3 = inventory.buyBook(showcaseBook.getIsbn(), 1, "Karim@gmail.com", "Cairo, Egypt", Balance);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        
        // Test 8: Remove outdated books
        System.out.println("Test 8: Remove outdated books (published before 2020)");
        try {
            System.out.println("Before removal - Total items: " + inventory.getInventoryItems().size());
            inventory.printInventory();
            inventory.removeOutdatedBooks(2020); // Remove books published before 2020
            System.out.println("After removal - Total items: " + inventory.getInventoryItems().size());
            inventory.printInventory();
            System.out.println("Outdated books removed successfully :)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("-----------------------------\n");
        
        // end of the major test cases :)
        System.out.println("\nAll tests complete :)");
        System.out.println("Thanks Fawry team for this great task :)");
    }
}