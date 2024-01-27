package it.jpalibrary.chiarapuleio.dao;

import it.jpalibrary.chiarapuleio.classes.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private EntityManager em;

    public UserDAO(EntityManager em){
        this.em = em;
    }

    public void saveUser(User user) {
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(user);
            transaction.commit();
            System.out.println("User " + user.getName() + " " + user.getSurname() + " has been saved.");
        } catch (Exception e) {
            System.err.println("Failed to save user: " + e.getMessage());
        }
    }


    public User findUserByCard (Long cardNumber){
        return em.find(User.class, cardNumber);
    }

    public void deleteUser(Long cardNumber){
        User foundUser = this.findUserByCard(cardNumber);
        if(foundUser != null){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(foundUser);
            transaction.commit();
            System.out.println("User " + foundUser.getName() + " " + foundUser.getSurname() + " has been deleted.");
        } else System.out.println("User not found.");
    }
}
