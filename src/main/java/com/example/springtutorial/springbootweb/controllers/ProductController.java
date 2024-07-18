package com.example.springtutorial.springbootweb.controllers;

import com.example.springtutorial.springbootweb.entities.ProductEntity;
import com.example.springtutorial.springbootweb.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {


    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    ResponseEntity<List<ProductEntity>> getAllProduct(@RequestParam(defaultValue = "id") String sortBy){
        List<ProductEntity> allProducts = productService.getAllProducts(sortBy);
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/page")
    ResponseEntity<List<ProductEntity>> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "0") Integer pageNumber){
        List<ProductEntity> allProducts = productService.fetchAllProducts(sortBy,pageNumber);
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/sort")
    ResponseEntity<List<ProductEntity>> getProducts(@RequestParam(defaultValue = "id") String sortBy,
                                                       @RequestParam(defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(defaultValue = "") String title){
        List<ProductEntity> allProducts = productService.fetchProducts(sortBy,pageNumber,title);
        return ResponseEntity.ok(allProducts);
    }
}
