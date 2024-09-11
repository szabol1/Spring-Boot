package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private double balance;

    @OneToMany(mappedBy = "user")
    private List<Portfolio> portfolio;

    public User() {
        this.balance = 100000;  // Default starting balance
    }

    public User(Long id, String username, String password, double balance, List<Portfolio> portfolio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.portfolio = portfolio;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Portfolio> getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(List<Portfolio> portfolio) {
        this.portfolio = portfolio;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User balance(double balance) {
        setBalance(balance);
        return this;
    }

    public User portfolio(List<Portfolio> portfolio) {
        setPortfolio(portfolio);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && balance == user.balance && Objects.equals(portfolio, user.portfolio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, balance, portfolio);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", balance='" + getBalance() + "'" +
            ", portfolio='" + getPortfolio() + "'" +
            "}";
    }

    // Getters and setters
}
