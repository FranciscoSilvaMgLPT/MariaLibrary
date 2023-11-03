import java.util.ArrayList;
import java.util.Scanner;

public class Person {

    Scanner sc = new Scanner(System.in);
    ArrayList<Book> inventory = new ArrayList<>();
    static ArrayList<Person> persons = new ArrayList<>();

    Library library;
    String name;
    int cash;
    boolean administrator;
    private static int idNumber = 0;

    public Person(String name, int cash, boolean administrator) {
        this.name = name;
        this.cash = cash;
        this.administrator = administrator;
        idNumber++;
        if (administrator == true) {
            this.name = Main.ANSI_YELLOW + "Rich " + Main.ANSI_RESET + name;
            System.out.print("Choose your library name!\nLibrary Name:");
            name = sc.nextLine();
            library = new Library(name,0);
            library.administator.add(this);
            library.getTaken();
        }
        persons.add(this);

    }

    public ArrayList<Book> getInventory() {
        return inventory;
    }


    public void setCash(int cash) {
        this.cash = cash;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public static int getIdNumber() {
        return idNumber;
    }


    public void addBookToLibrary(Book book) {
        if (this.administrator == true && this.cash >= book.price) {
            System.out.println("Book " + book.getTitle() + " costed " + book.price + "€. It has been added to library");
            this.cash = this.cash - book.price;
            this.inventory.remove(book);
            library.books.add(book);
        }
        if (this.administrator != true) {
            System.out.println(Main.ANSI_RED + "You can't donate! You are not administrator!" + Main.ANSI_RESET);
        }
        if (this.administrator == true && !(this.cash >= book.price)) {
            System.out.println(Main.ANSI_RED + "You are an broke administrator! Watch out!!" + Main.ANSI_RESET);
        }
    }

    public void buyBook(Book book) {
        if (this.administrator == true) {
            System.out.println(Main.ANSI_RED + "You are administrator.... Read here, Its like... its your home!" + Main.ANSI_RESET);
        }
        if (this.administrator == false && this.cash >= book.price) {
            System.out.println("It costs " + book.price + "€. Enjoy the book " + book.getTitle());
            this.inventory.add(book);
            library.books.remove(book);
        }
        if (this.administrator == false && !(this.cash >= book.price)) {
            System.out.println(Main.ANSI_RED + "Go to work... You even " + book.price + "€ for book you have..." + Main.ANSI_RESET);
        }
    }

    public void returnBook(Book book) {
        if (this.administrator == true) {
            System.out.println(Main.ANSI_RED + "You are home! You can't return to yourself!" + Main.ANSI_RESET);
        }
        if (this.administrator == false) {
            if (this.inventory.size() > 0) {
                this.inventory.remove(book);
            } else {
                System.out.println(Main.ANSI_RED + "You have nothing to return!" + Main.ANSI_RESET);
            }
        }
    }

    public void buyLibrary(Library library) {
        if (this.administrator) {
            System.out.println(Main.ANSI_RED + "No way... you are already rich, leave it to others! 💩" + Main.ANSI_RESET);
        }
        if(library.taken){
            System.out.println("This library is taken already! Dont fight for it!");
        }
        if (!this.administrator && !library.taken) {
            if (this.cash >= library.cost) {
                System.out.println("AMAZING!!!! YOU BECAME RICH!!! (but you have less " + library.cost + "€.. 👀)");
                this.name = "Rich " + name;
                this.setAdministrator(true);
                this.cash = cash - library.cost;
                library.administator.add(this);
                library.taken=false;
            } else {
                System.out.println(Main.ANSI_RED + "AMAZING!! YOU ARE POOR!! TRY AGAIN LATER! 😈🤡" + Main.ANSI_RESET);
                this.name = "Poor " + name;
            }
        }
    }

}
