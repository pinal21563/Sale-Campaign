package com.example.Sale.Campaign.Management.System.Service;

import com.example.Sale.Campaign.Management.System.Model.PriceHistory;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.PriceHistoryRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    ProductRepository productRepository;
//
//    public PriceHistory createPriceHistory(PriceHistory priceHistory) {
//        return priceHistoryRepository.save(priceHistory);
//    }

    public List<PriceHistory>getpricehistoryfordate(String date){
        LocalDate localDate= LocalDate.parse(date);
        return priceHistoryRepository.findByDate(localDate);
    }

}
