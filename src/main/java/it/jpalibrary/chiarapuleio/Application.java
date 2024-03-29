package it.jpalibrary.chiarapuleio;

import com.github.javafaker.Faker;
import it.jpalibrary.chiarapuleio.classes.Book;
import it.jpalibrary.chiarapuleio.classes.Loan;
import it.jpalibrary.chiarapuleio.classes.Magazine;
import it.jpalibrary.chiarapuleio.classes.User;
import it.jpalibrary.chiarapuleio.dao.LibraryArchiveDAO;
import it.jpalibrary.chiarapuleio.dao.LoanDAO;
import it.jpalibrary.chiarapuleio.dao.UserDAO;
import it.jpalibrary.chiarapuleio.enums.Periodicity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryarchive");
    public static Supplier<User> createUsers = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new User(faker.name().firstName(), faker.name().lastName(), faker.date().birthday());
    };

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        LibraryArchiveDAO archiveDAO = new LibraryArchiveDAO(em);
        LoanDAO loanDAO = new LoanDAO(em);
        UserDAO userDAO = new UserDAO(em);


        System.out.println("----- CREATE USERS -----");
        //ho utilizzato un for loop per creare un certo numero di Users con Faker, salvandoli automaticamente nel db con il metodo .saveUser()

        int usersToCreate = 10;
        for (int i = 0; i < usersToCreate; i++) {
            User newUser = createUsers.get();
            userDAO.saveUser(newUser);
        }


        System.out.println("----- CREATE ARCHIVE ITEMS -----");
        //ho utilizzato lo stesso metodo utilizzato per gli Users, avendo bisogno di due istanze differenti ho creato due metodi differenti, uno per Book e uno per Magazine

        int booksToCreate = 10;
        Faker faker = new Faker(Locale.ENGLISH);
        for (int i = 0; i < booksToCreate; i++) {
            Book newBook = new Book(faker.book().title(), faker.number().numberBetween(1800, 2024), faker.number().numberBetween(80, 500), faker.book().author(), faker.book().genre());
            archiveDAO.saveBook(newBook);
        }
        int magazinesToCreate = 10;
        for (int i = 0; i < magazinesToCreate; i++) {
            Magazine newMagazine = new Magazine(faker.book().title(), faker.number().numberBetween(1994, 2024), faker.number().numberBetween(20, 100), getRandomPeriodicity());
            archiveDAO.saveMagazine(newMagazine);
        }


        System.out.println("----- CREATE LOANS -----");
        //dato un numero di tessera risalgo a un utente specifico di tipo User, stessa cosa per Book ma con la ricerca per ISBN
        //una volta ottenuti i due oggetti, li passo al costruttore di Loan inserendo le date di inizio del prestito e di effettiva restituzione
        //per inserire il prestito all'interno della lista ho usato il get con il metodo .add e ho risalvato l'utente con il dato aggiornato
        User user = userDAO.findUserByCard(10L);
        Book book = archiveDAO.findBookByIsbn("9790846878963");
        Loan loan = new Loan(user, book, LocalDate.of(2024,1,1), LocalDate.of(2024,2,1));
        user.getUserLoans().add(loan);
        userDAO.saveUser(user);
        loanDAO.saveLoan(loan);



        System.out.println("----- REMOVE BY ISBN -----");
        archiveDAO.deleteBook("9781604165326");
        archiveDAO.deleteMagazine("9781679433153");



        System.out.println("----- SEARCH BY ISBN -----");
        if (archiveDAO.findBookByIsbn("9781907221071") != null) {
            System.out.println(archiveDAO.findBookByIsbn("9781907221071"));
        }
        if (archiveDAO.findMagazineByIsbn("9781000310429") != null) {
            System.out.println(archiveDAO.findMagazineByIsbn("9781000310429"));
        }


        System.out.println("----- SEARCH BY YEAR -----");
        archiveDAO.searchBooksByYear(2001);
        archiveDAO.searchMagazineByYear(2002);


        System.out.println("----- SEARCH BOOK BY AUTHOR -----");
        System.out.println(archiveDAO.searchBookByAuthor("Dr. Anibal Jacobi"));


        System.out.println("----- SEARCH BY TITLE (OR PART OF IT) -----");
        System.out.println(archiveDAO.searchBookByTitle("caged"));
        System.out.println(archiveDAO.searchMagazineByTitle("dream"));


        System.out.println("----- SEARCH LOANS -----");
        //qui ho dovuto usare direttamente l'oggetto User come parametro per come ho inserito le relazioni tra le entities
        System.out.println(loanDAO.searchLibraryOnLoanByUser(user));
        System.out.println(loanDAO.searchExpiredLoan());

        emf.close();
        em.close();
    }

    //non potendo usare un Faker per la periodicità, per creare delle istanze randomiche ho creato un metodo per randomizzare la periodicità delle riviste
    static Periodicity getRandomPeriodicity() {
        Periodicity[] options = Periodicity.values();
        int indexRand = new Random().nextInt(options.length);
        return options[indexRand];
    }
}