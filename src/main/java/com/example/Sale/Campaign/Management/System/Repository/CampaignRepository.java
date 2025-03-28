package com.example.Sale.Campaign.Management.System.Repository;

import com.example.Sale.Campaign.Management.System.Model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer>{

    @Query("SELECT c FROM Campaign c WHERE c.startDate = :currentDate")
    List<Campaign> StartDate (@Param("currentDate") LocalDate currentDate);


    @Query("SELECT c FROM Campaign c WHERE c.endDate >= :currentDate")
    List<Campaign> EndDate (LocalDate currentDate);

}
