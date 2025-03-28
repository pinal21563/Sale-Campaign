package com.example.Sale.Campaign.Management.System.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CampaignDiscount> campaignDiscounts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<CampaignDiscount> getCampaignDiscounts() {
        return campaignDiscounts;
    }

    public void setCampaignDiscounts(List<CampaignDiscount> campaignDiscounts) {
        this.campaignDiscounts = campaignDiscounts;
    }
}