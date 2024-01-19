package net.portfolio.stockPortfolio.controller;


import net.portfolio.stockPortfolio.model.Holdings;
import net.portfolio.stockPortfolio.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class PortfolioController {


    @Autowired
    private PortfolioService portfolioService;


    @GetMapping("/portfolio/{userId}")
    public Map<String, Holdings> getUserDetails(@PathVariable String userId){

        return portfolioService.fetchUserDetails(userId);

    }


}
