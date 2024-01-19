package net.portfolio.stockPortfolio.service;
import net.portfolio.stockPortfolio.model.Stock;
import net.portfolio.stockPortfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockApiService {

    private final StockRepository stockRepository;

    @Autowired
    public StockApiService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public ResponseEntity<Stock> fetchStock(String stockId) {

        try{
            if(stockId == null){
                throw new RuntimeException("StockId is missing");
            }
            Optional<Stock>stockDetails = stockRepository.findById(stockId);

            if(stockDetails.isPresent()){
                return ResponseEntity.ok(stockDetails.get());
            }else {
                return ResponseEntity.notFound().build(); // Return 404 status when stock is not found
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
