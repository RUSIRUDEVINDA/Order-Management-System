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

    public List<ProductDTO> getAllProducts(){
        List<Product> productList = productRepo.findAll();
        return modelMapper.map(productList, new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public ProductDTO getProductById(int id){
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product saved = productRepo.save(product);
        return modelMapper.map(saved, ProductDTO.class);
    }

    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existing.setProductName(productDTO.getProductName());
        Product updated = productRepo.save(existing);
        return modelMapper.map(updated, ProductDTO.class);
    }

    public void deleteProduct(int id){
        if(!productRepo.existsById(id)){
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepo.deleteById(id);
    }
}
