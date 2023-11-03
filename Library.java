import java.util.ArrayList;

public class Library {
    ArrayList<Book> books = new ArrayList<>();

    ArrayList<Person> administator = new ArrayList<>();
    int cost;

    static boolean taken;
    String name;
    public Library(String name,int cost) {
        this.cost = cost;
        this.name=name;
        Main.libraries.add(this);
    }

    public void getTaken(){
        this.taken=true;
    }

    public void getSold(){
        this.taken=false;
    }






}
