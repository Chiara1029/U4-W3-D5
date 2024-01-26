package it.jpalibrary.chiarapuleio;

import it.jpalibrary.chiarapuleio.classes.Book;
import it.jpalibrary.chiarapuleio.classes.LibraryArchive;
import it.jpalibrary.chiarapuleio.classes.Magazine;
import it.jpalibrary.chiarapuleio.enums.Periodicity;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {

        LibraryArchive archive = new LibraryArchive();
        archive.createAll(20,20);
        System.out.println(archive);

        System.out.println("---ADD---");
        Book lotr = new Book("Lord of the Rings", 1950, 1500, "Tolkien", "Fantasy");
        archive.addBook(lotr);
        Magazine ciak = new Magazine("Ciak", 2019, 60, Periodicity.MONTHLY);
        archive.addMagazine(ciak);


        System.out.println("---REMOVE---");
        archive.removeMagazine(1245);
        archive.removeBook(3456);

        System.out.println("---SEARCH---");
        archive.searchBookIsbn(4957);
        archive.searchBookByYear(1950);
        archive.searchMagazineIsbn(1023);
        archive.searchMagazineByYear(2001);
        archive.searchByAuthor("Tolkien");

    }

}