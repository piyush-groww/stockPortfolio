package net.portfolio.stockPortfolio.service;

import net.portfolio.stockPortfolio.model.Trade;
import net.portfolio.stockPortfolio.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TradeApiTest {

    @InjectMocks
    private TradeApiService tradeApiService;

    @Mock
    private TradeRepository tradeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testValidTrade(){
        Trade validTrade = new Trade();
        validTrade.setUserId("ABC");
        validTrade.setTradeType("Buy");
        validTrade.setStockId("INF740KA1SW3");
        validTrade.setQuantity(11);

        when(tradeRepository.save(validTrade)).thenReturn(validTrade);

        Map<String, String> response = tradeApiService.processTrade(validTrade);


        assertEquals("Success", response.get("Status"));

    }

    @Test
    void testInvalidTrade() {
        Trade invalidTrade = new Trade();

        Map<String, String> response = tradeApiService.processTrade(invalidTrade);

        assertEquals("Failure, Reason -> Missing parameters", response.get("Status"));
//        verify(tradeRepository, never()).save(invalidTrade);
    }

}
