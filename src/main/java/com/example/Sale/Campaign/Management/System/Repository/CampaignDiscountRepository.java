package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.CampaignDiscount;
import com.example.Sale.Campaign.Management.System.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignDiscountRepository extends JpaRepository<CampaignDiscount,Integer> {

}

