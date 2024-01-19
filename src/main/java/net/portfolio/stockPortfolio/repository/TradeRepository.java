package net.portfolio.stockPortfolio.repository;

import net.portfolio.stockPortfolio.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("SELECT c FROM Trade c WHERE c.userId=:user_id")
    List<Trade> findStockId(@Param("user_id") String userId);

    @Query("SELECT SUM(p.quantity) FROM Trade p WHERE p.stockId=:stock_id AND p.tradeType=:trade_type")
    Integer findSumOfTypeQuantity(@Param("stock_id") String stockId, @Param("trade_type") String type);

    @Query("SELECT SUM(p.quantity * p.price) FROM Trade p WHERE p.stockId=:stock_id AND p.tradeType=:trade_type")
    Double productOfQuantityAndPrice(@Param("stock_id") String stockId, @Param("trade_type") String type);
}

