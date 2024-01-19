package net.portfolio.stockPortfolio.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Holdings {

    private String stockId;
    private String stockName;
    private Long quantity;
    private double buyPrice;
    private double currentPrice;
    private double profit;

}
