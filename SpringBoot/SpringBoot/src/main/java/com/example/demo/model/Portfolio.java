package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String stockSymbol;
    private int quantity;
    private double purchasePrice;  // Price at which the stock was bought


    public Portfolio() {
    }

    public Portfolio(Long id, User user, String stockSymbol, int quantity, double purchasePrice) {
        this.id = id;
        this.user = user;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
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

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Portfolio id(Long id) {
        setId(id);
        return this;
    }

    public Portfolio user(User user) {
        setUser(user);
        return this;
    }

    public Portfolio stockSymbol(String stockSymbol) {
        setStockSymbol(stockSymbol);
        return this;
    }

    public Portfolio quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Portfolio purchasePrice(double purchasePrice) {
        setPurchasePrice(purchasePrice);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Portfolio)) {
            return false;
        }
        Portfolio portfolio = (Portfolio) o;
        return Objects.equals(id, portfolio.id) && Objects.equals(user, portfolio.user) && Objects.equals(stockSymbol, portfolio.stockSymbol) && quantity == portfolio.quantity && purchasePrice == portfolio.purchasePrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, stockSymbol, quantity, purchasePrice);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", stockSymbol='" + getStockSymbol() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", purchasePrice='" + getPurchasePrice() + "'" +
            "}";
    }
    
}
