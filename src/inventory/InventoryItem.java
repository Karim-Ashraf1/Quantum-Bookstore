package src.inventory;
import src.book.Book;


public class InventoryItem {
    private Book book;
    private int quantity;

    public InventoryItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public void increaseQuantity(int amount) { this.quantity += amount; }
    public void decreaseQuantity(int amount) { this.quantity -= amount; }
    public float getTotalPrice() { return book.getPrice() * quantity; }

    public Book getBook() { return book; }
    public int getQuantity() { return quantity; }
}
