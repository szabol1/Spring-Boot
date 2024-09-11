package com.example.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class AlphaVantageService {

    private static final String API_KEY = "INPUTYOURFREEAPIKEYHERE";//input API (needs to be stored when lauched)
    private static final String BASE_URL = "https://www.alphavantage.co/query";

    public double getStockPrice(String stockSymbol) {
        String url = BASE_URL + "?function=TIME_SERIES_INTRADAY&symbol=" + stockSymbol + "&interval=5min&apikey=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

   
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode timeSeries = jsonNode.get("Time Series (5min)");
            String latestTime = timeSeries.fieldNames().next();  // Get the most recent timestamp
            JsonNode latestData = timeSeries.get(latestTime);

            // Get the latest close price
            return latestData.get("4. close").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Error parsing stock price data", e);
        }
    }
}
