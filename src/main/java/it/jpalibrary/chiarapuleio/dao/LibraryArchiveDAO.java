package it.jpalibrary.chiarapuleio.dao;

import it.jpalibrary.chiarapuleio.classes.Book;
import it.jpalibrary.chiarapuleio.classes.Magazine;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class LibraryArchiveDAO {
    private EntityManager em;

    public LibraryArchiveDAO(EntityManager em) {
        this.em = em;
    }

    public void saveBook(Book book) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(book);
        transaction.commit();
        System.out.println("Book " + book.getTitle() + " has been saved.");
    }

    public Book findBookByIsbn(String isbn) {
        try {
            TypedQuery<Book> query = em.createQuery("SELECT fb FROM Book fb WHERE fb.isbnCode = :isbn", Book.class);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + " No books found for this ISBN.");
            return null;
        }
    }


    public void deleteBook(String isbn) {
        Book foundBook = this.findBookByIsbn(isbn);
        if (foundBook != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundBook);
            transaction.commit();
            System.out.println("Book " + foundBook.getTitle() + " has been removed.");
        }
    }

    public void saveMagazine(Magazine magazine) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(magazine);
        transaction.commit();
        System.out.println("Magazine " + magazine.getTitle() + " has been saved.");
    }

    public Magazine findMagazineByIsbn(String isbn) {
        try {
            TypedQuery<Magazine> query = em.createQuery("SELECT fm FROM Magazine fm WHERE fm.isbnCode = :isbn", Magazine.class);
            query.setParameter("isbn", isbn);
            return query.getSingleResult();
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + " No magazine found for this ISBN.");
            return null;
        }
    }

    public void deleteMagazine(String isbn) {
        Magazine foundMagazine = this.findMagazineByIsbn(isbn);
        if (foundMagazine != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundMagazine);
            transaction.commit();
            System.out.println("Magazine " + foundMagazine.getTitle() + " has been removed.");
        }
    }

    public List<Book> searchBooksByYear(int publicationYear) {
        try {
            TypedQuery<Book> query = em.createQuery( "SELECT b FROM Book b WHERE b.publicationYear = :publicationYear", Book.class);
            query.setParameter("publicationYear", publicationYear);
            List<Book> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                System.out.println("No Books found for year " + publicationYear);
            } else {
                System.out.println("Books found for year " + publicationYear + ":");
                for (Book book : resultList) {
                    System.out.println(book);
                }
            }
            return resultList;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No Books found for year " + publicationYear);
            return Collections.emptyList();
        }
    }


    public List<Magazine> searchMagazineByYear(int publicationYear) {
        try {
            TypedQuery<Magazine> query = em.createQuery( "SELECT m FROM Magazine m WHERE m.publicationYear = :publicationYear", Magazine.class);
            query.setParameter("publicationYear", publicationYear);
            List<Magazine> resultList = query.getResultList();
            if (resultList.isEmpty()) {
                System.out.println("No Magazine found for year " + publicationYear);
            } else {
                System.out.println("Magazine found for year " + publicationYear + ":");
                for (Magazine magazine : resultList) {
                    System.out.println(magazine);
                }
            }
            return resultList;
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No Books found for year " + publicationYear);
            return Collections.emptyList();
        }
    }
    public List<Book> searchBookByAuthor(String author) {
        try {
            TypedQuery<Book> query = em.createQuery("SELECT fa FROM Book fa WHERE fa.author = :author", Book.class);
            query.setParameter("author", author);
            return query.getResultList();
        } catch (NoResultException e) {
            System.err.println(e.getMessage() + "No Books found for author " + author);
            return null;
        }
    }

    public List<Book> searchBookByTitle(String title) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(:title)", Book.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    public List<Magazine> searchMagazineByTitle(String title) {
        TypedQuery<Magazine> query = em.createQuery("SELECT m FROM Magazine m WHERE LOWER(m.title) LIKE LOWER(:title)", Magazine.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }
}
