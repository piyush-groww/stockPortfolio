package net.portfolio.stockPortfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private String stockId;

    @Column
    private String stockName;

    @Column
    private Double openPrice;

    @Column
    private Double closePrice;

    @Column
    private Double highPrice;

    @Column
    private Double lowPrice;


}
