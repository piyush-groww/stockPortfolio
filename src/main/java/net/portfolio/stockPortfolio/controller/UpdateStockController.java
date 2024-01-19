package net.portfolio.stockPortfolio.controller;


import net.portfolio.stockPortfolio.service.UpdateStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UpdateStockController {

    @Autowired
    private UpdateStockService updateStockService;

    @PostMapping("/update-stocks")
    public ResponseEntity<String> updateStocks(@RequestParam("file") MultipartFile csvFile){
        try{
            updateStockService.updateStockFromCsvFile(csvFile);
            return ResponseEntity.ok("Stock Updated in Database Successfully");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error Updating the CSV File");
        }

    }


}
