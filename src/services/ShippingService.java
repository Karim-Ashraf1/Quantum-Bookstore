package src.services;
import src.book.Book;
import java.util.ArrayList;
import java.util.List;


public class ShippingService {
    private static List<Book> shippingList = new ArrayList<>();

    public static void ship(Book book, String address) {
        shippingList.add(book);
        System.out.println("Quantum book store - Shipping book: " + book.getTitle() + " to " + address);
    }
}
