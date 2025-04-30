package com.example.product.service.ServiceImpl;

import com.example.product.dto.ProductDTO;
import com.example.product.exception.ProductAlreadytExistException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.mapping.ProductMapper;
import com.example.product.model.ProductModel;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Optional<ProductModel> found = this.productRepository.findByName(dto.getName());
        if(found.isPresent()){
            throw  new ProductAlreadytExistException(dto.getName());
        }

        ProductModel product = productMapper.toEntity(dto);
        ProductModel saved = this.productRepository.save(product);
        return productMapper.toDTO(saved);

    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductModel> product = this.productRepository.findAll();
        return product.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(int productId, ProductDTO dto) {
        Optional<ProductModel> exist = this.productRepository.findById(productId);
        if (productId == dto.getProductId() && exist.isPresent()){
            ProductModel p = exist.get();
            p.setName(dto.getName());
            p.setPrice(dto.getPrice());
            p.setQuantity(dto.getQuantity());

            return productMapper.toDTO(this.productRepository.save(p));
        }
        throw  new ProductNotFoundException(productId);
    }

    @Override
    public Boolean deleteProduct(int productId) {
        Optional<ProductModel> exist = this.productRepository.findById(productId);
        if (exist.isPresent()){
            ProductModel p = exist.get();
            this.productRepository.delete(p);
            return true;
        }
        throw new ProductNotFoundException(productId);

    }
}
