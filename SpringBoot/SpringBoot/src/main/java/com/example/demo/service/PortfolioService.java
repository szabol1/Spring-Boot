package com.example.demo.service;

import com.example.demo.model.Portfolio;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.repository.PortfolioRepository;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AlphaVantageService alphaVantageService;  // To fetch stock prices

    // Buy stock
    public void buyStock(User user, String stockSymbol, int quantity) {
        double pricePerUnit = alphaVantageService.getStockPrice(stockSymbol);  // Get live stock price
        double totalCost = pricePerUnit * quantity;

        // Check if user has enough balance
        if (user.getBalance() < totalCost) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct the cost from user's balance
        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);

        // Check if user already owns this stock
        Portfolio portfolio = portfolioRepository.findByUser(user).stream()
            .filter(p -> p.getStockSymbol().equals(stockSymbol))
            .findFirst()
            .orElse(new Portfolio());

        portfolio.setUser(user);
        portfolio.setStockSymbol(stockSymbol);
        portfolio.setQuantity(portfolio.getQuantity() + quantity);  // Add the bought quantity
        portfolio.setPurchasePrice(pricePerUnit);

        portfolioRepository.save(portfolio);

        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setStockSymbol(stockSymbol);
        transaction.setQuantity(quantity);
        transaction.setPricePerUnit(pricePerUnit);
        transaction.setTransactionType("BUY");
        transactionRepository.save(transaction);
    }

    // Sell stock
    public void sellStock(User user, String stockSymbol, int quantity) {
        Portfolio portfolio = portfolioRepository.findByUser(user).stream()
            .filter(p -> p.getStockSymbol().equals(stockSymbol))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("You don't own this stock"));

        if (portfolio.getQuantity() < quantity) {
            throw new RuntimeException("Not enough shares to sell");
        }

        double pricePerUnit = alphaVantageService.getStockPrice(stockSymbol);
        double totalValue = pricePerUnit * quantity;

        // Update user's balance
        user.setBalance(user.getBalance() + totalValue);
        userRepository.save(user);

        // Update portfolio
        portfolio.setQuantity(portfolio.getQuantity() - quantity);
        if (portfolio.getQuantity() == 0) {
            portfolioRepository.delete(portfolio);  // Remove stock if no shares left
        } else {
            portfolioRepository.save(portfolio);
        }

        // Record the transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setStockSymbol(stockSymbol);
        transaction.setQuantity(quantity);
        transaction.setPricePerUnit(pricePerUnit);
        transaction.setTransactionType("SELL");
        transactionRepository.save(transaction);
    }

    // Get a user's portfolio
    public List<Portfolio> getUserPortfolio(User user) {
        return portfolioRepository.findByUser(user);
    }
}
