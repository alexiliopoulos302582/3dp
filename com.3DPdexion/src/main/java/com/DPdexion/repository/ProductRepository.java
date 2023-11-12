package com.DPdexion.repository;

import com.DPdexion.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    default List<Product> searchProducts(String productCode, String location, String serialNumber) {
        return null;
    }

    @Query("SELECT p FROM Product p WHERE p.product_code  LIKE %?1% " +
            "OR p.description LIKE %?1%" +
            "OR p.serial_number LIKE %?1% " +
            "OR p.location LIKE %?1%"+
            "OR p.comments LIKE %?1%")
            List<Product> findAll(String keyword);

    @Query("SELECT p FROM Product p WHERE p.product_code  LIKE %?1% " +
            "OR p.description LIKE %?1%" +
            "OR p.serial_number LIKE %?1% " +
            "OR p.location LIKE %?1%"+
            "OR p.comments LIKE %?1%  ORDER BY p.product_code ASC")
    List<Product> findAllSortedAsc(String keyword);


    @Query("SELECT p FROM Product p WHERE p.product_code  LIKE %?1% " +
            "OR p.description LIKE %?1%" +
            "OR p.serial_number LIKE %?1% " +
            "OR p.location LIKE %?1%"+
            "OR p.comments LIKE %?1%  ORDER BY p.product_code DESC")
    List<Product> findAllSortedDesc(String keyword);



}
