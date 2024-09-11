package com.example.demo.controller;

import com.example.demo.model.Portfolio;
import com.example.demo.model.User;
import com.example.demo.service.PortfolioService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    // Buy stocks endpoint
    @PostMapping("/buy")
    public String buyStock(@RequestParam String stockSymbol, @RequestParam int quantity) {
        // Fetch the currently authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        portfolioService.buyStock(user, stockSymbol, quantity);
        return "Successfully bought " + quantity + " shares of " + stockSymbol;
    }

    // Sell stocks endpoint
    @PostMapping("/sell")
    public String sellStock(@RequestParam String stockSymbol, @RequestParam int quantity) {
        // Fetch the currently authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        portfolioService.sellStock(user, stockSymbol, quantity);
        return "Successfully sold " + quantity + " shares of " + stockSymbol;
    }

    // View portfolio endpoint
    @GetMapping("/portfolio")
    public List<Portfolio> viewPortfolio() {
        // Fetch the currently authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByUsername(username);

        return portfolioService.getUserPortfolio(user);
    }
}
