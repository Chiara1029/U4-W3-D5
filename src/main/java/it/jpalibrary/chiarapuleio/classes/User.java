package it.jpalibrary.chiarapuleio.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    private String name;
    private String surname;
    private Date dateOfBirth;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long cardNumber;
@OneToMany(mappedBy = "user")
    private Set<Loan> userLoans;

    public User(){}

    public User(String name, String surname, Date dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Set<Loan> getUserLoans() {
        return userLoans;
    }

    public void setUserLoans(Set<Loan> userLoans) {
        this.userLoans = userLoans;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", cardNumber=" + cardNumber +
                ", userLoans=" + userLoans +
                '}';
    }
}
