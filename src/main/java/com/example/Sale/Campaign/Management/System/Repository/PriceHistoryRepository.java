package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.PriceHistory;
import com.example.Sale.Campaign.Management.System.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {

     PriceHistory findTopByProductOrderByDateDesc(Product product);

    List<PriceHistory>findByDate(LocalDate date);
}
