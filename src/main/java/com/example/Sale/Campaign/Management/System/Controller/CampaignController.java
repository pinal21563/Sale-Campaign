package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import com.example.Sale.Campaign.Management.System.Model.CampaignDiscount;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Repository.ProductRepository;
import com.example.Sale.Campaign.Management.System.Service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("create")
    public Campaign save(@RequestBody Campaign campaign){
        for (CampaignDiscount discount : campaign.getCampaignDiscounts()) {
            if (discount.getProduct() != null) {
                Product product = productRepository.findById(discount.getProduct().getProductId())
                        .orElseThrow(() -> new RuntimeException("product not found"));
                discount.setProduct(product);
            }
            discount.setCampaign(campaign);
        }
        return campaignService.save(campaign);
    }

    @GetMapping("applydiscount")
    public ResponseEntity<String> applyDiscountManually() {
        campaignService.applyDiscount();
        return ResponseEntity.ok("discount apply successfully");
    }

    @GetMapping("revert-price")
    public ResponseEntity<String> revertPrice() {
        campaignService.revertPriceAfterCampaignEnd();
        return ResponseEntity.ok("Product prices reverted successfully");
    }
}
