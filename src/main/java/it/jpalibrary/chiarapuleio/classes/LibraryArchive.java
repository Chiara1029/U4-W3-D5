package it.jpalibrary.chiarapuleio.classes;


import com.github.javafaker.Faker;
import it.jpalibrary.chiarapuleio.superClass.LibraryItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static it.jpalibrary.chiarapuleio.classes.Magazine.getRandomPeriodicity;

public class LibraryArchive {
    private List<LibraryItem> bookArchive;
    private List<LibraryItem> magazineArchive;

    public LibraryArchive() {
        this.bookArchive = bookArchive = new ArrayList<>();
        this.magazineArchive = magazineArchive = new ArrayList<>();
    }

    public static void createBooks(int amount, List<LibraryItem> bookArchive){
        Faker faker = new Faker(Locale.ITALY);
        Supplier<Book> books = () -> new Book (faker.book().title(), faker.number().numberBetween(1800,2024), faker.number().numberBetween(80, 500), faker.book().author(), faker.book().genre());
        for(int i=0; i < amount; i++){
            bookArchive.add((LibraryItem) books.get());
        }
    }
    public static void createMagazines(int amount, List<LibraryItem> magazineArchive){
        Faker faker = new Faker(Locale.ITALY);
        Supplier<Magazine> magazines = () -> new Magazine (faker.book().title(), faker.number().numberBetween(1994,2024), faker.number().numberBetween(20, 100), getRandomPeriodicity());
        for(int i=0; i < amount; i++){
            magazineArchive.add((LibraryItem) magazines.get());
        }
    }
    public void createAll(int bookAmount, int magazineAmount){
        createBooks(bookAmount, bookArchive);
        createMagazines(magazineAmount, magazineArchive);
    }
    public List<LibraryItem> getBookArchive() {
        return bookArchive;
    }

    public List<LibraryItem> getMagazineArchive() {
        return magazineArchive;
    }

    @Override
    public String toString() {
        return "LibraryArchive{" +
                "bookArchive=" + bookArchive + "\n" +
                "magazineArchive=" + magazineArchive +
                '}';
    }
}
