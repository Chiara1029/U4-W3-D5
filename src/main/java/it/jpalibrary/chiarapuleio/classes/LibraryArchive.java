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

    //DA INSERIRE TUTTO NEL DAO CON LE QUERY

//    public void addBook(LibraryItem item){
//        bookArchive.add(item);
//        System.out.println(item.getTitle() + " has been added in Books.");
//    }
//    public void removeBook(String isbn){
//        bookArchive.removeIf(e -> e.getIsbnCode().equals(isbn));
//        System.out.println("Book removed");
//    }
//    public void addMagazine(LibraryItem item){
//        magazineArchive.add(item);
//        System.out.println(item.getTitle() + " has been added in Magazines.");
//    }
//    public void removeMagazine(String isbn){
//        magazineArchive.removeIf(e -> e.getIsbnCode().equals(isbn));
//        System.out.println("Magazine removed");
//    }
//
//    public void searchBookIsbn (String isbn){
//        Optional<LibraryItem> foundBook = bookArchive.stream().filter(e -> e.getIsbnCode().equals(isbn)).findFirst();
//        if(foundBook.isPresent()) {
//            System.out.println("Here's: " + foundBook.get());
//        } else {
//            System.out.println("There are no books with this code.");
//        }
//    }
//    public void searchMagazineIsbn (String isbn){
//        Optional<LibraryItem> foundMagazine = magazineArchive.stream().filter(e -> e.getIsbnCode().equals(isbn)).findFirst();
//        if(foundMagazine.isPresent()) {
//            System.out.println("Here's: " + foundMagazine.get());
//        } else {
//            System.out.println("There are no magazines with this code.");
//        }
//    }
//
//    public void searchBookByYear (int publicationYear){
//        List<LibraryItem> foundBooks =  bookArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
//        if(!foundBooks.isEmpty()){
//            System.out.println("Books published in " + publicationYear + ": " + foundBooks );
//        }else {
//            System.out.println("No books published in " + publicationYear);
//        }
//    }
//    public void searchMagazineByYear (int publicationYear){
//        List<LibraryItem> foundMagazines = magazineArchive.stream().filter(e-> e.getPublicationYear() == publicationYear).collect(Collectors.toList());
//        if(!foundMagazines.isEmpty()){
//            System.out.println("Magazine published in " + publicationYear + ": " + foundMagazines );
//        }else {
//            System.out.println("No magazine published in " + publicationYear);
//        }
//    }
//    public void searchByAuthor (String author){
//        List<LibraryItem> foundBooks = bookArchive.stream()
//                .filter(e -> e instanceof Book && ((Book) e).getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
//        if (!foundBooks.isEmpty()) {
//            System.out.println("Books written by " + author + ": " + foundBooks);
//        } else {
//            System.out.println("No books written by " + author + ".");
//        }
//    }

    //FAKER
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
