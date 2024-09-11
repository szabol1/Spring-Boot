package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String stockSymbol;
    private int quantity;
    private double pricePerUnit;
    private LocalDateTime transactionDate;
    private String transactionType; // "BUY" or "SELL"


    public Transaction() {
    }

    public Transaction(Long id, User user, String stockSymbol, int quantity, double pricePerUnit, LocalDateTime transactionDate, String transactionType) {
        this.id = id;
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStockSymbol() {
        return this.stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public LocalDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction id(Long id) {
        setId(id);
        return this;
    }

    public Transaction user(User user) {
        setUser(user);
        return this;
    }

    public Transaction stockSymbol(String stockSymbol) {
        setStockSymbol(stockSymbol);
        return this;
    }

    public Transaction quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Transaction pricePerUnit(double pricePerUnit) {
        setPricePerUnit(pricePerUnit);
        return this;
    }

    public Transaction transactionDate(LocalDateTime transactionDate) {
        setTransactionDate(transactionDate);
        return this;
    }

    public Transaction transactionType(String transactionType) {
        setTransactionType(transactionType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return Objects.equals(id, transaction.id) && Objects.equals(user, transaction.user) && Objects.equals(stockSymbol, transaction.stockSymbol) && quantity == transaction.quantity && pricePerUnit == transaction.pricePerUnit && Objects.equals(transactionDate, transaction.transactionDate) && Objects.equals(transactionType, transaction.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, stockSymbol, quantity, pricePerUnit, transactionDate, transactionType);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", stockSymbol='" + getStockSymbol() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", pricePerUnit='" + getPricePerUnit() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            "}";
    }
    
}

