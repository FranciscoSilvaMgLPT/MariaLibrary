public class Book {
    int price;
    private String title;

    public Book(int price, String title) {
        this.price = price;
        this.title = title;
        Main.generalLibrary.books.add(this);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

}
