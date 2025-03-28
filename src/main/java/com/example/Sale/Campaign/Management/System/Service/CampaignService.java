package com.example.Sale.Campaign.Management.System.Service;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Model.CampaignDiscount;
import com.example.Sale.Campaign.Management.System.Model.PriceHistory;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.CampaignDiscountRepository;
import com.example.Sale.Campaign.Management.System.Repository.CampaignRepository;
import com.example.Sale.Campaign.Management.System.Repository.PriceHistoryRepository;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    PriceHistoryRepository priceHistoryRepository;

    @Autowired
    ProductRepository productRepository;

    public Campaign save(Campaign campaign){
        return campaignRepository.save(campaign);
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void applyDiscount(){
        LocalDate currentDate = LocalDate.now();
        List<Campaign> activeCampaigns = campaignRepository.StartDate(currentDate);

        if (activeCampaigns.isEmpty()) {
            System.out.println("No active campaigns found for date: " + currentDate);
        }

        for(Campaign campaign : activeCampaigns){
            System.out.println("Processing Campaign: " + campaign.getTitle());

            for(CampaignDiscount discount : campaign.getCampaignDiscounts()){
                Product product = discount.getProduct();

                double originalPrice = product.getCurrentPrice();
                double discountAmount = originalPrice * (discount.getDiscount() / 100.0);
                double newPrice = originalPrice - discountAmount;

                System.out.println("Applying discount on product: " + product.getProductId());
                System.out.println("Original Price: " + originalPrice + ", Discount: " + discount.getDiscount() + "%, New Price: " + newPrice);

                // Update Product Price
                product.setCurrentPrice(newPrice);
                productRepository.save(product);

                // Save Price History for tracking old price
                PriceHistory priceHistory = new PriceHistory();
                priceHistory.setPrice(newPrice);
                priceHistory.setDate(currentDate);
                priceHistory.setProduct(product);
                priceHistoryRepository.save(priceHistory);

            }
        }
    }

    @Scheduled(cron = "59 59 23 * * *")
    @Transactional
    public void revertPriceAfterCampaignEnd() {
        LocalDate currentDate = LocalDate.now();
        List<Campaign> endedCampaigns = campaignRepository.EndDate(currentDate);

        if (endedCampaigns.isEmpty()) {
            System.out.println("No campaigns ended on: " + currentDate);
        }

        for (Campaign campaign : endedCampaigns) {
            System.out.println("Campaign Ended: " + campaign.getTitle());

            for (CampaignDiscount discount : campaign.getCampaignDiscounts()) {
                Product product = discount.getProduct();

                // Retrieve the last price history to get the original price
                PriceHistory lastPriceHistory = priceHistoryRepository.findTopByProductOrderByDateDesc(product);
                if (lastPriceHistory != null) {
                    double Lastprice = lastPriceHistory.getPrice();
                    System.out.println("Restoring Original price for product:"+product.getProductId());
                    System.out.println("Restored price:"+Lastprice);

                    // Restore Original price
                    product.setCurrentPrice(Lastprice);
                    productRepository.save(product);

                    // Save Price History for tracking restored price
                    PriceHistory priceHistory = new PriceHistory();
                    priceHistory.setProduct(product);
                    priceHistory.setPrice(product.getCurrentPrice());
                    priceHistory.setDate(currentDate);
                    priceHistoryRepository.save(priceHistory);

                }
            }
        }
    }
}