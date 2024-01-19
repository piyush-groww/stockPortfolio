package net.portfolio.stockPortfolio.repository;

import net.portfolio.stockPortfolio.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

    Optional<Stock> findById(String stockId);

}
