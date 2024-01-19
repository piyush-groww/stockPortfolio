package net.portfolio.stockPortfolio.controller;


import net.portfolio.stockPortfolio.model.Stock;
import net.portfolio.stockPortfolio.service.StockApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockAPIController {

    private final StockApiService stockApiService;

    @Autowired
    public StockAPIController(StockApiService stockApiService) {
        this.stockApiService = stockApiService;
    }

    @GetMapping("/stock/{stockId}")
    public ResponseEntity<Stock>getStockDetails(@PathVariable String stockId){
        return stockApiService.fetchStock(stockId);
    }



}
