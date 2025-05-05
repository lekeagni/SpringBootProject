package com.example.product.service.ServiceImpl;

import com.example.product.dto.ProductDTO;
import com.example.product.exception.ProductAlreadytExistException;
import com.example.product.exception.ProductNotFoundException;
import com.example.product.mapping.ProductMapper;
import com.example.product.model.ProductModel;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final RestTemplate restTemplate;

    public ProductServiceImpl(ProductRepository productRepository, RestTemplate restTemplate) {

        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
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
    public ProductDTO getProductById(int productId) {
       ProductModel productModel = this.productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
       return productMapper.toDTO(productModel);
    }

    @Override
    public ProductDTO updateProduct(int productId, ProductDTO dto) {
        ProductModel exist = this.productRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException(productId));

            exist.setName(dto.getName());
            exist.setPrice(dto.getPrice());
            exist.setQuantity(dto.getQuantity());

            ProductModel saved = this.productRepository.save(exist);

            return productMapper.toDTO(saved);

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
