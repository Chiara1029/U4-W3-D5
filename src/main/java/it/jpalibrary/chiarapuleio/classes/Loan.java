package it.jpalibrary.chiarapuleio.classes;

import java.time.LocalDate;

public class Loan {
    private User user;
    private String loanedItem;
    private LocalDate loanStart;
    private LocalDate loanEnd;
    private LocalDate restitution;

    public Loan(){}

    public Loan(User user, String loanedItem, LocalDate loanStart, LocalDate loanEnd, LocalDate restitution) {
        this.user = user;
        this.loanedItem = loanedItem;
        this.loanStart = loanStart;
        this.loanEnd = loanEnd;
        this.restitution = restitution;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoanedItem() {
        return loanedItem;
    }

    public void setLoanedItem(String loanedItem) {
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
