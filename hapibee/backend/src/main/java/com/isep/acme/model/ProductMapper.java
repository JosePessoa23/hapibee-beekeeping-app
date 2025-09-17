package com.isep.acme.model;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public ProductDTO toDTO(Product product){
        return new ProductDTO(product.getSku(), product.getDesignation());
    }

    public List<ProductDTO> toDTO (Iterable<Product> productList){
        if (productList ==null){
            return null;
        }
        List<ProductDTO> list =new ArrayList<>();
        for (Product product:productList) {
            ProductDTO p = new ProductDTO(product.getSku(),product.getDesignation());
            list.add(p);
        }
        return list;
    }

    public ProductDetailDTO toDetailsDTO(Product product){
        return new ProductDetailDTO(product.getSku(), product.getDesignation(),product.getDescription());
    }




}
