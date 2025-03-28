package com.example.Sale.Campaign.Management.System.Controller;

import com.example.Sale.Campaign.Management.System.Model.Pagenationdto;
import com.example.Sale.Campaign.Management.System.Model.Product;
import com.example.Sale.Campaign.Management.System.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("save")
    public Product saveProduct (@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("list")
    public Pagenationdto getproduct(@RequestParam(defaultValue = "0")int page,
                                    @RequestParam(defaultValue = "5")int pagesize){
        return productService.getproducts(page, pagesize);
    }

}
