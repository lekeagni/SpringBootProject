package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "test product", description = "test APIs products with Swagger")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    @Operation(summary = "save product", description = "save product in the database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "product save successful"),
            @ApiResponse(responseCode = "500", description = "erreur du serveur")
    })
    public ResponseEntity<ProductDTO> create( @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.createProduct(dto));
    }

    @Operation(summary = "list all products", description = "lister all products with exist in database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "product get all successful"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    @GetMapping()
    public ResponseEntity<List<ProductDTO>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(this.productService.getAllProducts());
    }

    @PutMapping("/update/{productId}")
    @Operation(summary = "update product", description = "update product with exist in database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "product updated successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<ProductDTO> update(@PathVariable int productId, @RequestBody ProductDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.productService.updateProduct(productId,dto));
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(summary = "delete product", description = "delete product with exist in database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "product deleted successful"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "500", description = "error server")
    })
    public ResponseEntity<Boolean>delete(@PathVariable int productId) {
        boolean exist = this.productService.deleteProduct(productId);
        if (exist){
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);

    }
}
