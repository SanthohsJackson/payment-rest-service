package com.payment.credit.data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Hold the information for a credit card.
 *
 * @Author Santhosh Jackson
 **/
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_user_name")
    @NotNull(message = "Name can not be null")
    @Size(min = 1, message = "Please enter a user name")
    private String userName;


    @Column(name = "card_limit")
    @NotNull(message = "Limit can not be null")
    private Long limit;

    @Column(name = "card_balance")
    private long balance;

    @Column(name = "card_number")
    @NotNull(message = "Card number can not be null")
    //TODO need to verify lower bound is ok ?
    @Size(min = 12, message = "Card number should at least have 12 numbers")
    private String cardNumber;


    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getLimit() {

        return limit;
    }

    public void setLimit(Long limit) {

        this.limit = limit;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
