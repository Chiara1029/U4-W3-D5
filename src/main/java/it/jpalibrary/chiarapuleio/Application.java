package it.jpalibrary.chiarapuleio;

import com.github.javafaker.Faker;
import it.jpalibrary.chiarapuleio.classes.User;
import it.jpalibrary.chiarapuleio.dao.LibraryArchiveDAO;
import it.jpalibrary.chiarapuleio.dao.LoanDAO;
import it.jpalibrary.chiarapuleio.dao.UserDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;
import java.util.function.Supplier;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryarchive");
    public static Supplier<User> createUsers = () -> {
        Faker faker = new Faker(Locale.ENGLISH);
        return new User (faker.name().firstName(), faker.name().lastName(), faker.date().birthday());
    };
    public static void main(String[] args)  {
        EntityManager em = emf.createEntityManager();
        try{
            LibraryArchiveDAO archiveDAO = new LibraryArchiveDAO(em);
            LoanDAO loanDAO = new LoanDAO(em);
            UserDAO userDAO = new UserDAO(em);
        } catch {}



//        User newUser = createUsers.get();
//        System.out.println(newUser);

//
//        Book ciao = new Book("ciao", 1998, 789, "Ciao", "Ciao");
//        System.out.println(ciao);
//
//        LibraryArchive archive = new LibraryArchive();
//        archive.createAll(10,10);
//        System.out.println(archive);
//
//        Loan loanCiao = new Loan(chiara, ciao, LocalDate.of(2024, 1, 1), LocalDate.now());
//        System.out.println(loanCiao);
    }

}