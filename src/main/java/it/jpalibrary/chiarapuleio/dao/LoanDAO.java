package it.jpalibrary.chiarapuleio.dao;

import it.jpalibrary.chiarapuleio.classes.Loan;
import it.jpalibrary.chiarapuleio.classes.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoanDAO {
    private EntityManager em;
    public LoanDAO(EntityManager em){
        this.em = em;
    }

    public void saveLoan(Loan loan){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(loan);
        transaction.commit();
        System.out.println("Loan of " + loan.getLoanedItem().getTitle() + " has been saved.");
    }

    public Loan findById(Long id){
        return em.find(Loan.class, id);
    }

    public void deleteLoan(Long id){
        Loan foundLoan = this.findById(id);
        if(foundLoan != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundLoan);
            transaction.commit();
            System.out.println("Loan of " + foundLoan.getLoanedItem().getTitle() + " has been deleted.");
        } else System.out.println("Loan not found.");
    }

    public List<Loan> searchLibraryOnLoanByUser(User user){
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE l.user = :user AND CURRENT_DATE < l.restitution", Loan.class);
        query.setParameter("user", user);
        return query.getResultList();
    }
    public List<Loan> searchExpiredLoan(){
        TypedQuery<Loan> query = em.createQuery("SELECT l FROM Loan l WHERE CURRENT_DATE > l.restitution", Loan.class);
        return query.getResultList();
    }


}
