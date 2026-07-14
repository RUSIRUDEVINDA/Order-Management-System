package com.product.service.controller;

import com.product.service.dto.ProductDTO;
import com.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productID}")
    public ProductDTO getProduct(@PathVariable Integer productID){
        return productService.getProductById(productID);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{productID}")
    public ProductDTO updateProduct(@PathVariable Integer productID, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(productID, productDTO);
    }

    @DeleteMapping("/{productID}")
    public void deleteProduct(@PathVariable Integer productID) {
        productService.deleteProduct(productID);
    }
}
