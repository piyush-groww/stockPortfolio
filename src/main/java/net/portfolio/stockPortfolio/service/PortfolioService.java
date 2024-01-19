package net.portfolio.stockPortfolio.service;


import net.portfolio.stockPortfolio.model.Holdings;
import net.portfolio.stockPortfolio.model.TotalHolding;
import net.portfolio.stockPortfolio.model.Trade;
import net.portfolio.stockPortfolio.repository.StockRepository;
import net.portfolio.stockPortfolio.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortfolioService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockApiService stockApiService;
    @Autowired
    private TradeRepository tradeRepository;


    double totalPortfolioHolding = 0;
    double totalBuyPrice = 0;
    double totalProfitAndLossPercentage = 0;

    public Map<String, Holdings> fetchUserDetails(String userId) {

        List<Trade> allTradeOfUser = tradeRepository.findStockId(userId); // Select stock_id from trade where user_id=id;

        if (allTradeOfUser.isEmpty()) {
            return new HashMap<>();
        }

        Set<String> allStocksOfUser = extractUniqueStockIds(allTradeOfUser);

        Map<String,Holdings> detailsOfUserId = new HashMap<>();
        TotalHolding totalHolding = new TotalHolding();

        for(String stockId : allStocksOfUser){

            Holdings stockHolding = calculateStockHolding(stockId, allTradeOfUser);
            if (stockHolding != null) {
                detailsOfUserId.put(stockId, stockHolding);
                updateTotalHolding(stockHolding, totalHolding);
            }
        }

        totalHolding.setPortfolio(detailsOfUserId);
        detailsOfUserId.put("total", totalHolding);
        return detailsOfUserId;

    }

    private void updateTotalHolding(Holdings stockHolding, TotalHolding totalHolding) {
        double singleStockHolding = stockHolding.getCurrentPrice() * stockHolding.getQuantity();
        totalHolding.setTotalPortfolioHolding(totalHolding.getTotalPortfolioHolding() + singleStockHolding);

        totalHolding.setTotalBuyPrice(totalHolding.getTotalBuyPrice() + stockHolding.getBuyPrice());
        totalHolding.setTotalProfitAndLossPercentage(totalHolding.getTotalProfitAndLossPercentage() + stockHolding.getProfit());
    }


    private Holdings calculateStockHolding(String stockId, List<Trade> allTradeOfUser) {
        Holdings stockHolding = new Holdings();

        Optional<StockApiService> stockApiResponseOptional = stockApiService.fetchStock(stockId).getBody();
        if (stockApiResponseOptional.isEmpty()) {
            // Handle the case where stock information is not available
            return null;
        }

        StockApiResponse stockApiResponse = stockApiResponseOptional.get();

        stockHolding.setStockName(stockApiResponse.getStockName());
        stockHolding.setStockId(stockId);
        stockHolding.setCurrentPrice(stockApiResponse.getClosePrice());

        long totalQuantityBuy = tradeRepository.findSumOfTypeQuantity(stockId, "buy");
        long totalQuantitySell = tradeRepository.findSumOfTypeQuantity(stockId, "sell");
        long actualQuantity = totalQuantityBuy - totalQuantitySell;

        stockHolding.setQuantity(actualQuantity);

        Double totalBuyPriceStock = tradeRepository.productOfQuantityAndPrice(stockId, "buy");
        Double totalSellPrice = tradeRepository.productOfQuantityAndPrice(stockId, "sell");

        if (totalQuantityBuy + totalQuantitySell == 0) {
            // Avoid division by zero
            return null;
        }

        double avgBuyPrice = (totalBuyPriceStock - totalSellPrice) / (totalQuantityBuy + totalQuantitySell);
        double profit = stockHolding.getCurrentPrice() * actualQuantity - avgBuyPrice * actualQuantity;

        stockHolding.setBuyPrice(avgBuyPrice);
        stockHolding.setProfit(profit);

        return stockHolding;
    }
    

    private Set<String> extractUniqueStockIds(List<Trade> trades) {
        Set<String> stockIds = new HashSet<>();
        for (Trade trade : trades) {
            stockIds.add(trade.getStockId());
        }
        return stockIds;
    }


}
