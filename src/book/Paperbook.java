package src.book;


public class Paperbook extends Book {
    private String fileType;

    public Paperbook(String ISBN, String title, int publishYear, float price, String fileType) {
        super(ISBN, title, publishYear, price);
        this.fileType = fileType;
    }
}
