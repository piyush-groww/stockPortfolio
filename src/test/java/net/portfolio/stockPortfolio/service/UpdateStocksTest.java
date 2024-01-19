package net.portfolio.stockPortfolio.service;

import net.portfolio.stockPortfolio.model.Stock;
import net.portfolio.stockPortfolio.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class UpdateStocksTest {

    @InjectMocks
    private UpdateStockService updateStockService;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void validCsvFile_shouldUpdateDatabase() throws IOException {
        String csvData = "SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN,\n"+
                "1018GS2026,GS,115.54,115.54,115.54,115.54,115.54,121.62,1,115.54,12-JAN-2024,1,IN0020010081,\n" +
                "824GS2027,TB,106.6,106.69,106.6,106.69,106.6,106.69,1,106.6,12-JAN-2024,4,IN0020060078,\n" +
                "828GS2027,TB,107,107,107,107,107,107,1,107,12-JAN-2024,2,IN0020070069,\n" +
                "92GS2030,GS,112.25,118.6,115,112.25,115,112.25,1,112.25,12-JAN-2024,1,IN0020130053,\n" +
                "SGBFEB24,TB,6252,6310,6303.58,6310,6303.58,6310,1,6252,12-JAN-2024,5,IN0020150101";


        MockMultipartFile csvFile = new MockMultipartFile("file", "data.csv", "data/csv", csvData.getBytes());
        updateStockService.updateStockFromCsvFile(csvFile);


    }
    @Test
    void parseCsv_ValidCsvData_ShouldReturnStockList() throws IOException {
        // Mock data
        String csvData ="SYMBOL,SERIES,OPEN,HIGH,LOW,CLOSE,LAST,PREVCLOSE,TOTTRDQTY,TOTTRDVAL,TIMESTAMP,TOTALTRADES,ISIN,\n"+
                "1018GS2026,GS,115.54,115.54,115.54,115.54,115.54,121.62,1,115.54,12-JAN-2024,1,IN0020010081,\n" +
                "824GS2027,TB,106.6,106.69,106.6,106.69,106.6,106.69,1,106.6,12-JAN-2024,4,IN0020060078,\n"+
                "828GS2027,TB,107,107,107,107,107,107,1,107,12-JAN-2024,2,IN0020070069,\n";

        MockMultipartFile csvFile = new MockMultipartFile("file", "data.csv", "data/csv", csvData.getBytes());

        List<Stock> stockList = updateStockService.parseCsv(csvFile);
//        for(Stock stock: stockList){
//            System.out.println(stock.getStockId());
//        }
        assertEquals(3, stockList.size());
    }



}
