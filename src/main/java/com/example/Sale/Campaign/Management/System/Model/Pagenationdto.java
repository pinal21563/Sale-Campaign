package com.example.Sale.Campaign.Management.System.Model;


import java.util.List;

public class Pagenationdto {
    private List<Product>products;
    private int page;
    private int pagesize;
    private int totalpages;

    public Pagenationdto(List<Product> products, int page, int pagesize, int totalpages) {
        this.products = products;
        this.page = page;
        this.pagesize = pagesize;
        this.totalpages = totalpages;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }
}
