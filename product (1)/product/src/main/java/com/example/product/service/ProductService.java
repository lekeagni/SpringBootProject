package com.example.product.service;

import com.example.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public ProductDTO createProduct(ProductDTO dto);

    public List<ProductDTO>getAllProducts();

    public ProductDTO getProductById(int productId);

    public ProductDTO updateProduct( int productId, ProductDTO dto);

    public Boolean deleteProduct(int productId);
}
