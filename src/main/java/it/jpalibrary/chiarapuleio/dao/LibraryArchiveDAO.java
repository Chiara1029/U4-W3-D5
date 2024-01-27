package it.jpalibrary.chiarapuleio.dao;

import it.jpalibrary.chiarapuleio.classes.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LibraryArchiveDAO {
    private EntityManager em;

    public LibraryArchiveDAO(EntityManager em){
        this.em = em;
    }

    public void saveBook(Book book){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(book);
        transaction.commit();
        System.out.println("Book " + book.getTitle() + " has been saved.");
    }

    public Book findBookByIsbn (String isbn){
        return em.find(Book.class, isbn);
    }
}
