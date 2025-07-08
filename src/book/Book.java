package src.book;    


public class Book {
    private String ISBN;
    private String title;
    private int publishYear;
    private float price;

    public Book(String ISBN, String title, int publishYear, float price) {
        this.ISBN = ISBN;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }

    public String getIsbn() { return ISBN; }
    public String getTitle() { return title; }
    public String getPublishYear() { return String.valueOf(publishYear); } // for string retrival
    public int getYear() { return publishYear; } // for int retrival
    public float getPrice() { return price; }
}
