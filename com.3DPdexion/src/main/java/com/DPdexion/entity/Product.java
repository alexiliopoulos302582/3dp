package com.DPdexion.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product_code;

    private String description;

    private String serial_number;

    private String location;

    private String comments;

    public Product() {
    }



    public Product(String product_code, String description, String serial_number, String location, String comments) {
        this.product_code = product_code;
        this.description = description;
        this.serial_number = serial_number;
        this.location = location;
        this.comments = comments;
    }

    public Long getId() {
        return id;    }

    public void setId(Long id) {
        this.id = id;    }

    public String getProduct_code() {
        return product_code;    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;    }

    public String getDescription() {
        return description;    }

    public void setDescription(String description) {
        this.description = description;    }

    public String getSerial_number() {
        return serial_number;    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;    }

    public String getLocation() {
        return location;    }

    public void setLocation(String location) {
        this.location = location;    }

    public String getComments() {
        return comments;    }

    public void setComments(String comments) {
        this.comments = comments;    }



}
