package net.portfolio.stockPortfolio.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import net.portfolio.stockPortfolio.model.Stock;
import net.portfolio.stockPortfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class UpdateStockService {

    @Autowired
    private StockRepository stockRepository;
    public void updateStockFromCsvFile(MultipartFile csvFile) {

        List<Stock> stockList = parseCsv(csvFile);

        for(Stock stockData: stockList){
            updateStockInDatabase(stockData);
        }

    }

    private void updateStockInDatabase(Stock stockData) {

        Stock stockDetails = new Stock();
        stockDetails.setStockId(stockData.getStockId());
        stockDetails.setStockName(stockData.getStockName());
        stockDetails.setOpenPrice(stockData.getOpenPrice());
        stockDetails.setClosePrice(stockData.getClosePrice());
        stockDetails.setHighPrice(stockData.getHighPrice());
        stockDetails.setLowPrice(stockData.getLowPrice());

        stockRepository.save(stockDetails);

    }

    public List<Stock> parseCsv(MultipartFile csvFile) {

        List<Stock>stockList = new ArrayList<>();
        boolean firstLine = true;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while((line = reader.readLine())!=null){
                if(firstLine){
                    firstLine=false;
                    continue;
                }
                String[] columns = line.split(",");
                Stock stock = new Stock();
                stock.setStockId(columns[12]);
                stock.setStockName(columns[0]);
                stock.setOpenPrice(Double.parseDouble((columns[2])));
                stock.setClosePrice(Double.parseDouble(columns[4]));
                stock.setHighPrice(Double.parseDouble(columns[3]));
                stock.setLowPrice(Double.parseDouble(columns[5]));
                stockList.add(stock);
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return stockList;

    }
}
