package com.product.service.repo;

import com.product.service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product WHERE productID = ?1", nativeQuery = true)
    Product getProductByProductID(int productID);
}
