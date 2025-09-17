package com.isep.acme.repositories.dataModels.JPA;

import com.isep.acme.model.Product;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class ProductJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productID;

    @Column(nullable = false, unique = true)
    public String sku;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;
    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected ProductJPA(){}

    public ProductJPA(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public ProductJPA(final Long productID, final String sku, final String designation, final String description) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
    }

    public ProductJPA(Product p) {
        setSku(p.getSku());
        setDescription(p.getDescription());
        setDesignation(p.getDesignation());
    }

    public ProductJPA(Product p,String id) {
        this.productID = Long.parseLong(id);
        setSku(p.getSku());
        setDescription(p.getDescription());
        setDesignation(p.getDesignation());
    }

    public ProductJPA(final String sku) {
        setSku(sku);
    }

    public ProductJPA(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
        }

        this.sku = sku;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is a mandatory attribute of Product.");
        }
        if (designation.length() > 50) {
            throw new IllegalArgumentException("Designation must not be greater than 50 characters.");
        }
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }

    public String getSku() {
        return sku;
    }


    public void updateProduct(ProductJPA p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public Long getProductID() {
        return productID;
    }

    public Product toModel(){
        return new Product(this.productID.toString(),this.sku, this.designation, this.description);
    }

    @Override
    public String toString() {
        return "ProductJPA{" +
                "productID=" + productID +
                ", sku='" + sku + '\'' +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
