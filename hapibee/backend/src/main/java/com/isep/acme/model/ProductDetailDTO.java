package com.isep.acme.model;

import java.util.Objects;

public class ProductDetailDTO {
    private String sku;
    private String designation;
    private String description;

    public ProductDetailDTO(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailDTO that = (ProductDetailDTO) o;
        return Objects.equals(sku, that.sku) && Objects.equals(designation, that.designation) && Objects.equals(description, that.description);
    }
}
