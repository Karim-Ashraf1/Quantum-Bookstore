package src.services;
import src.book.Book;
import java.util.ArrayList;
import java.util.List;


public class MailService {
    private static List<Book> mailList = new ArrayList<>();

    public static void mail(Book book, String email) {
        mailList.add(book);
        System.out.println("Quantum book store - Sending ebook: " + book.getTitle() + " to " + email);
    }
}
