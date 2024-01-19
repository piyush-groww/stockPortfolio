package net.portfolio.stockPortfolio.service;

import jakarta.transaction.Transactional;
import net.portfolio.stockPortfolio.model.Trade;
import net.portfolio.stockPortfolio.repository.StockRepository;
import net.portfolio.stockPortfolio.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TradeApiService {

    @Autowired
    private TradeRepository tradeRepository;


    public Map<String, String> processTrade(Trade trade) {
        Map<String,String>response = new HashMap<>();
        try{
            if (trade.getUserId() == null ||(!trade.getTradeType().equals("Buy") && !trade.getTradeType().equals("Sell"))
                    ||trade.getStockId()==null||trade.getQuantity()<=0) {
                throw new RuntimeException("Missing parameters");
            }
            tradeRepository.save(trade);
            response.put("Status","Success");
        } catch (Exception e){
            response.put("Status", "Failure, Reason -> "+e.getMessage());
        }
        return response;
    }
}
