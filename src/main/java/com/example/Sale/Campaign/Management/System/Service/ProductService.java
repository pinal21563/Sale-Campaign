package com.example.Sale.Campaign.Management.System.Service;

import com.example.Sale.Campaign.Management.System.Model.CampaignDiscount;
import com.example.Sale.Campaign.Management.System.Model.Pagenationdto;
import com.example.Sale.Campaign.Management.System.Model.PriceHistory;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.CampaignDiscountRepository;
import com.example.Sale.Campaign.Management.System.Repository.PriceHistoryRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PriceHistoryRepository priceHistoryRepository;

    public Product saveProduct(Product product) {
       Product saveproduct=productRepository.save(product);

        PriceHistory priceHistory=new PriceHistory();
        priceHistory.setProduct(saveproduct);
        priceHistory.setDate(LocalDate.now());
        priceHistory.setPrice(product.getCurrentPrice());
        priceHistoryRepository.save(priceHistory);

        return saveproduct;


    }

    public Pagenationdto getproducts(int page, int pagesize) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, pagesize));
        return new Pagenationdto(
                productPage.getContent(),
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalPages()
        );
    }

}
