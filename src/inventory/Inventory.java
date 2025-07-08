package src.inventory;
import src.inventory.InventoryItem;
import src.book.Book;
import src.book.EBook;
import src.book.Paperbook;
import src.book.Showcasebook;
import src.services.ShippingService;
import src.services.MailService;
import java.util.ArrayList;
import java.util.List;


public class Inventory {
    private List<InventoryItem> inventoryItems = new ArrayList<>();

    // add book to inventory and if the book already exists, increase its quantity
    public void addBook(Book book, int quantity) {
        for (InventoryItem item : inventoryItems) {
            if (item.getBook().getIsbn().equals(book.getIsbn())) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        inventoryItems.add(new InventoryItem(book, quantity));
    }

    // remove book from inventory by quantity and if the quantity = 0 it removes the book from the inventory
    public void removeBook(Book book, int quantity, float balance) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            InventoryItem item = inventoryItems.get(i);
            if (item.getBook().getIsbn().equals(book.getIsbn())) {
                if(item.getTotalPrice() > balance) { 
                    throw new IllegalArgumentException("Insufficient balance :( Required: " + item.getTotalPrice() + ", Available: " + balance); 
                }
                if (item.getQuantity() > quantity) { item.decreaseQuantity(quantity); } 
                else if (item.getQuantity() == quantity) { inventoryItems.remove(i); } 
                else { throw new IllegalArgumentException("The quantity you need is not availiable :( "); }
                return; // return if we find and removed the book
            }
        }
        throw new IllegalArgumentException("Book not found in inventory :( ");
    }

    // buy a book and remove it from inventory and handle shipping or mailing
    public float buyBook(String isbn, int quantity, String email, String address, float balance) {
        for (int i = 0; i < inventoryItems.size(); i++) {
            InventoryItem item = inventoryItems.get(i);
            Book book = item.getBook();

            if (book.getIsbn().equals(isbn)) {
                // if the book is a showcase book, we detect early and end the purchase
                if (book instanceof Showcasebook) {
                    System.out.println("Showcase books cannot be purchased :(");
                    return 0.0f;
                }

                // remove the book from inventry after we check that we can buy it
                removeBook(book, quantity, balance);

                //  if the remove book didn't throw an exception, we proceed with shipping or mailing
                if (book instanceof Paperbook) { ShippingService.ship(book, address); } 
                else if (book instanceof EBook) { MailService.mail(book, email); }

                float paidAmount = book.getPrice() * quantity;

                System.out.println("Purchase successful :) Total paid: " + paidAmount);
                return paidAmount;
            }
        }

        System.out.println("Book with ISBN " + isbn + " not found :(");
        return 0.0f;
    }

    // remove outdated books from inventory based on the year
    public void removeOutdatedBooks(int year) {
        for (int i = 0; i < inventoryItems.size(); ) {
            InventoryItem item = inventoryItems.get(i);
            Book book = item.getBook();

            if (book.getYear() < year) {
                System.out.println("Removed \"" + book.getTitle() + "\" (Published: " + book.getYear() + ") as it is outdated.");
                inventoryItems.remove(i);
            } else {
                i++; // move to next item only if the book is not removed 
            }
        }
    }

    // Helper method to print inventory contents
    public void printInventory() {
        if (inventoryItems.isEmpty()) { System.out.println("(Inventory is empty)"); }

        System.out.println("Current inventory:");
        for (InventoryItem item : inventoryItems) {
            Book book = item.getBook();
            System.out.println("- " + book.getTitle() + " (ISBN: " + book.getIsbn() + ") - Quantity: " + item.getQuantity() + " - Price: $" + book.getPrice());
        }
    }

    // Helper method to find inventory item by ISBN
    public InventoryItem findInventoryItem(String isbn) {
        for (InventoryItem item : inventoryItems) {
            if (item.getBook().getIsbn().equals(isbn)) {
                return item;
            }
        }
        return null;
    }

    public List<InventoryItem> getInventoryItems() { return inventoryItems; }
    public boolean isEmpty() { return inventoryItems.isEmpty(); }
    public void clearInventory() { inventoryItems.clear(); }
}