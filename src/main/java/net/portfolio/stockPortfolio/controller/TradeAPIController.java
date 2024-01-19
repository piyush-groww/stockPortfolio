package net.portfolio.stockPortfolio.controller;

import net.portfolio.stockPortfolio.model.Trade;
import net.portfolio.stockPortfolio.service.TradeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TradeAPIController {

    @Autowired
    TradeApiService tradeApiService;



    @PostMapping("/trade")
    public Map<String,String>fetchTradeResponse(@RequestBody Trade trade){
        return tradeApiService.processTrade(trade);
    }

}
