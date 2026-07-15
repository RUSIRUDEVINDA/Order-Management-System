package com.product.service.controller;

import com.product.service.dto.ProductDTO;
import com.product.service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getProduct/{productID}")
    public ProductDTO getProductById(@PathVariable Integer productID) {
        return productService.getProductById(productID);
    }

    @PostMapping("/addProduct")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }

    @PutMapping("/updateProduct")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProductById(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }
}