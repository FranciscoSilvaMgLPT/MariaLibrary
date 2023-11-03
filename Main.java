import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final Scanner sc = new Scanner(System.in);
    public static String option = "100";
    public static String name;
    public static int cash;
    static ArrayList<Library> libraries = new ArrayList<>();
    static Library generalLibrary = new Library("General Library", 100000);



    public static void main(String[] args) {
        new Book(100,"The Book");
        new Book(10,"The bad book");
        new Book(10000,"Bookao");
        new Book(-100,"Biblia");
        System.out.println("WELCOME!");
        gameIntro();

    }

    public static void gameIntro() {
        while (!option.equals("0")) {
            System.out.print("\n1-Play\n2-Create\n0-EXIT\nOption:");
            option = sc.next();
            switch (option) {
                case "1":
                    play();
                    break;
                case "2":
                    System.out.print("\nCreate a:\n1-Person\n2-Book\n3-Library\n0-Back/Return\nOption:");
                    String option1 = sc.next();
                    switch (option1) {
                        case "1":
                            createPerson();
                            break;
                        case "2":
                            createBook();
                            break;
                        case "3":
                            createLibrary();
                            break;
                        case "0":
                            gameIntro();
                            break;
                        default:
                            System.out.println(ANSI_RED + "Cant read that option!" + ANSI_RESET);
                            break;
                    }
                    break;
                case "0":
                    System.out.println("BYEEEEEE 😥");
                    break;
                default:
                    System.out.println(ANSI_RED + "Cant read that option!" + ANSI_RESET);
                    break;
            }
        }
    }

    public static void play() {
        if (Person.persons.size() == 0) {
            System.out.println("You can't play without a person! First create at least one!");
        } else {
            personPlay(personPick());
        }
    }


    public static void personPlay(Person person) {
        while (!option.equals("0")) {
            System.out.println("Hello " + person.name + "! Choose what you want to do!");
            System.out.print("1- READ A BOOK!\n2- BUY/SELL BOOKS\n3- BUY A LIBRARY!\n0- BACK/EXIT\nOption:");
            option = sc.next();
            switch (option) {
                case "0":
                    personPick();
                    break;
                case "1":
                    readBook(person);
                    break;
                case "2":
                    buysellBooks(person);
                    break;
                case "3":
                    if(!person.administrator) {
                        for (int i = 0; i < libraries.size(); i++) {
                            if (!libraries.get(i).taken) {
                                System.out.println((i + 1) + ": " + libraries.get(i).name + ", " + libraries.get(i).cost);
                            }
                        }
                        System.out.println("Library number:");
                        option = sc.next();
                        if (person.cash - libraries.get(Integer.valueOf(option) - 1).cost >= 0 && !person.administrator) {
                            System.out.println("AMAZING! YOU NOW OWN A LIBRARY!");
                            person.administrator = true;
                            person.library = libraries.get(Integer.valueOf(option) - 1);
                            person.library.getTaken();
                        }
                    }else{
                        System.out.println("You cant! You already have one!! LEAVE IT TO OTHERS! 💩");
                    }
                    break;
                default:
                    System.out.println("What the hell you just input? Try again....");
                    personPlay(person);
            }
        }
    }

    public static Person personPick() {
        System.out.println(ANSI_BLUE + "\n\nPick a person to play." + ANSI_RESET);
        for (int i = 0; i < Person.persons.size(); i++) {
            System.out.println("ID:" + (i + 1) + " → Name: " + Person.persons.get(i).name + "   | Cash: " + Person.persons.get(i).cash);
        }
        System.out.print("ID:");
        option = sc.next();
        return Person.persons.get(Integer.valueOf(option) - 1);
    }

    public static void readBook(Person person) {
        if (person.inventory.isEmpty()) {
            System.out.println("\nYou dont have any book! You need to buy one or go to library to read!");
            personPlay(person);
        } else {
            for (int i = 0; i < person.inventory.size(); i++) {
                System.out.println(i + 1 + "->" + person.inventory.get(i).getTitle());
            }
            System.out.print("Choose a book! 0-EXIT/BACK\nBOOK NUMBER:");
            option = sc.next();
            if (Integer.valueOf(option) == 0) {
                personPlay(person);
            }
            if ((Integer.valueOf(option)-1) >= 0 && Integer.valueOf(option)-1 < person.inventory.size()) {
                System.out.println("READING: " + person.inventory.get(Integer.valueOf(option)-1).getTitle() + "!\n Bla Bla blalalalala" +
                        "\nBla blablalaalalalalallala\nWOOOOW... What a amazing book... 😱");
            } else {
                System.out.println("Amazing... you dont have that kind of book.. Try again, or maybe go buy the one you wish.");
                readBook(person);
            }
        }

    }

    public static void createPerson() {
        System.out.println(ANSI_BLUE + "\nCREATING A PERSON!" + ANSI_RESET);
        System.out.print("Name:");
        name = sc.next();
        System.out.print("Cash:");
        cash = sc.nextInt();
        System.out.print("Administrator?\nOption [true or false]:");
        boolean admin = sc.nextBoolean();
        Person person = new Person(name, cash, admin);
        System.out.println(ANSI_BLUE + "\nYou created a person!" + ANSI_RESET + "\n[Name:" + person.name +
                ",  Cash:" + cash + "€,  Administrator:" + admin + ",  ID:" + person.getIdNumber() + "]");
    }

    public static void createBook() {
        System.out.println(ANSI_BLUE + "\nCREATING A BOOK!" + ANSI_RESET);
        System.out.print("Title:");
        name = sc.nextLine();
        name = sc.nextLine();
        System.out.print("Price:");
        cash = sc.nextInt();
        Book book = new Book(cash, name);
        System.out.println(ANSI_BLUE + "You created a book!" + ANSI_RESET + "\n[Title:" + name +
                ", Cost:" + cash + "€]");
        generalLibrary.books.add(book);
    }

    public static void createLibrary() {
        System.out.print(ANSI_BLUE + "\nCREATING A LIBRARY!" + ANSI_RESET + "\nName:");
        name = sc.nextLine();
        name = sc.nextLine();
        System.out.print("Cost:");
        cash= sc.nextInt();
        new Library(name,cash);
        System.out.println("SUCCESSFUL! Library "+name+" created!!");
    }

    public static void buysellBooks(Person person) {
        System.out.print("1-Buy\n2-Sell\n0-Back/Exit\nOption:");
        option = sc.next();
        switch (option) {
            case "1":
                System.out.println("GENERAL LIBRARY:");
                for (int i = 0; i < generalLibrary.books.size(); i++) {
                    System.out.println((i + 1) + ": " + generalLibrary.books.get(i).getTitle() +" Price-> "+ generalLibrary.books.get(i).getPrice() + "€");
                }
                System.out.print("Tell me which book you want to buy: \nBook number:");
                option = sc.next();
                if (person.cash - generalLibrary.books.get(Integer.valueOf(option) - 1).price >= 0) {
                    System.out.println("Congratz you now own the book " + generalLibrary.books.get(Integer.valueOf(option) - 1).getTitle() + "!!");
                    person.cash = person.cash - generalLibrary.books.get(Integer.valueOf(option) - 1).price;
                    person.inventory.add(generalLibrary.books.get(Integer.valueOf(option) - 1));
                    generalLibrary.books.remove(Integer.valueOf(option) - 1);
                } else {
                    System.out.println("You are broke! You can't buy the book!");
                }
                break;
            case "2":
                if (person.inventory.isEmpty()) {
                    System.out.println("You have no books to sell.... you have to sell other things..... 👀");
                } else {
                    for (int i = 0; i < person.inventory.size(); i++) {
                        System.out.println((i + 1) + ": " + person.inventory.get(i).getTitle() + ", " + person.inventory.get(i).price + "€");
                    }
                    System.out.println("Which one?\nBook number:");
                    option = sc.next();
                    person.cash = person.cash + generalLibrary.books.get(Integer.valueOf(option) - 1).price;
                    System.out.println("WOOOW, Thanks for the book, he will go to General Library! Here, take "
                            + generalLibrary.books.get(Integer.valueOf(option) - 1).price + "€ for it! Thanks");
                    generalLibrary.books.add(generalLibrary.books.get(Integer.valueOf(option) - 1));
                    person.inventory.remove(Integer.valueOf(option) - 1);
                }
                break;
            case "0":
                personPlay(person);
                break;
            default:
                System.out.println("Ahm?!? Try again");
                buysellBooks(person);
        }
    }
}