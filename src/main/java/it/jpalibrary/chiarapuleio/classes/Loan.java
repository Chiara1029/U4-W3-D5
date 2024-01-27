package it.jpalibrary.chiarapuleio.classes;

import it.jpalibrary.chiarapuleio.superClass.LibraryItem;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_card")
    private User user;
    @ManyToOne
    @JoinColumn(name="library_item_id")
    private LibraryItem loanedItem;
    @Column(name="loan_start")
    private LocalDate loanStart;
    @Column(name="loan_end")
    private LocalDate loanEnd;
    private LocalDate restitution;

    public Loan(){}

    public Loan(User user, LibraryItem loanedItem, LocalDate loanStart, LocalDate restitution) {
        this.user = user;
        this.loanedItem = loanedItem;
        this.loanStart = loanStart;
        this.loanEnd = loanStart.plusDays(30);
        this.restitution = restitution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getLoanedItem() {
        return loanedItem;
    }

    public void setLoanedItem(LibraryItem loanedItem) {
        this.loanedItem = loanedItem;
    }

    public LocalDate getLoanStart() {
        return loanStart;
    }

    public void setLoanStart(LocalDate loanStart) {
        this.loanStart = loanStart;
    }

    public LocalDate getLoanEnd() {
        return loanEnd;
    }

    public void setLoanEnd(LocalDate loanEnd) {
        this.loanEnd = loanEnd;
    }

    public LocalDate getRestitution() {
        return restitution;
    }

    public void setRestitution(LocalDate restitution) {
        this.restitution = restitution;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "user=" + user +
                ", loanedItem='" + loanedItem + '\'' +
                ", loanStart=" + loanStart +
                ", loanEnd=" + loanEnd +
                ", restitution=" + restitution +
                '}';
    }
}
