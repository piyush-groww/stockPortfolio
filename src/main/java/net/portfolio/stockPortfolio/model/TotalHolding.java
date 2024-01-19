package net.portfolio.stockPortfolio.model;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TotalHolding extends Holdings {

    Map<String, Holdings> Portfolio = new HashMap<>();
    private double totalPortfolioHolding;
    private double totalBuyPrice;
    private double totalProfitAndLossPercentage;

}
