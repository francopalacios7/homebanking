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
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
    private Set<Account> accountSet = new HashSet<>();
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> clientLoanSet = new HashSet<>();
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private Set<Card> cardSet = new HashSet<>();
    public Client (){
    }
    public Client(String first, String last, String email) {
        firstName = first;
        lastName = last;
        this.email = email;
    }
    public Set<Account> getAccounts() {
        return accountSet;
    }
    public void addAccount(Account account) {
        account.setOwner(this);
        accountSet.add(account);
    }
    public Set<ClientLoan> getClientLoanSet() {
        return clientLoanSet;
    }
    public void addLoan(ClientLoan clientLoan) {
        clientLoan.setClient(this);
        clientLoanSet.add(clientLoan);
    }
    public Set<Card> getCardSet() {return cardSet;}
    public void addCard(Card card){
        card.setOwner(this);
        cardSet.add(card);
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
    public Long getId() {
        return id;
    }
}
