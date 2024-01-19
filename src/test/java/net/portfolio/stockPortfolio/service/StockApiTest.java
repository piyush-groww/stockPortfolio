package net.portfolio.stockPortfolio.service;

import net.portfolio.stockPortfolio.model.Stock;
import net.portfolio.stockPortfolio.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StockApiTest {

    @InjectMocks
    private StockApiService stockApiService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidStock() {
        String validStockId = "IN0020010081";

        Stock mockStock = new Stock();
        mockStock.setStockId(validStockId);

        when(stockRepository.findById(validStockId)).thenReturn(Optional.of(mockStock));

        ResponseEntity<Stock> responseEntity = stockApiService.fetchStock(validStockId);

        System.out.println(responseEntity);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(mockStock, responseEntity.getBody());
    }

    @Test
    void testNullStock() {

        ResponseEntity<Stock> responseEntity = stockApiService.fetchStock(null);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals(null, responseEntity.getBody());
    }

    @Test
    void testNotFoundStock() {

        String nonExistingStockId = "XYZ";
        when(stockRepository.findById(nonExistingStockId)).thenReturn(Optional.empty());

        ResponseEntity<Stock> responseEntity = stockApiService.fetchStock(nonExistingStockId);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals(null, responseEntity.getBody());
    }

}
