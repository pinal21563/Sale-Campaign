package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.PriceHistory;
import com.example.Sale.Campaign.Management.System.Service.CampaignService;
import com.example.Sale.Campaign.Management.System.Service.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
public class PriceHistoryController {

    @Autowired
    private PriceHistoryService priceHistoryService;

//    @PostMapping("create")
//    public PriceHistory createPriceHistory(@RequestBody PriceHistory priceHistory) {
//        return priceHistoryService.createPriceHistory(priceHistory);
//    }

    @GetMapping("pricehistory")
    public List<PriceHistory>getpricehistoryfordate(@RequestParam String date){
        return priceHistoryService.getpricehistoryfordate(date);
    }
}
