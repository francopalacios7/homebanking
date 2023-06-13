package com.MindHub.HomeBanking.models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
    Set<Account> accountSet = new HashSet<>();
    public Set<Account> getAccounts() {
        return accountSet;
    }

    public void addAccount(Account account) {
        account.setOwner(this);
        accountSet.add(account);
    }
    /*private Set<Loan> loansSet = new HashSet<>();
    public Set<Loan> getLoans() {
        return loansSet;
    }

    public void addLoan(Loan loan) {
        loan.setOwner(this);
        loansSet.add(loan);
    }*/

    public Client (){
    }
    public Client(String first, String last, String email) {
        firstName = first;
        lastName = last;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return firstName + " " + lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
