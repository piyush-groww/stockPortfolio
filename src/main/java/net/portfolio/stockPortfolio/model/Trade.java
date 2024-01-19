package net.portfolio.stockPortfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id")
    private Long tradeId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "trade_type")
    private String tradeType;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "stock_id")
    private String stockId;

    @Column(name = "price")
    private double price;



}
