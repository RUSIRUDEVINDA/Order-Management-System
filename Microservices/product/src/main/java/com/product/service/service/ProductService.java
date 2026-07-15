package com.product.service.service;

import com.product.service.dto.ProductDTO;
import com.product.service.model.Product;
import com.product.service.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getAllProducts() {
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>() {}.getType());
    }

    public ProductDTO getProductById(Integer productID) {
        Product product = productRepo.getProductByProductID(productID);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public ProductDTO updateProduct(ProductDTO productDTO) {
        productRepo.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    public String deleteProductById(Integer id) {
        productRepo.deleteById(id);
        return "PRODUCT DELETED";
    }
}