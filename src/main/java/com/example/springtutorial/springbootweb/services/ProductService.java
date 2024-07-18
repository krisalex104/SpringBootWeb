package com.example.springtutorial.springbootweb.services;

import com.example.springtutorial.springbootweb.entities.ProductEntity;
import com.example.springtutorial.springbootweb.repositories.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private static final Integer PAGE_SIZE=5;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProducts(String sortBy){
        //sortBy and order by example
        //List<ProductEntity> parle = productRepository.findBy(Sort.by(sortBy));
       // List<ProductEntity> parle = productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy));
        //List<ProductEntity> parle = productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price"));
       // List<ProductEntity> parle = productRepository.findBy(Sort.by(Sort.Order.desc(sortBy)));
        List<ProductEntity> parle = productRepository.findBy(Sort.by(Sort.Order.desc(sortBy),Sort.Order.asc("title")));
        return parle;
    }

    public List<ProductEntity> fetchAllProducts(String sortBy,Integer pageNumber){
        //pagination example

        Pageable pageable= PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy));
        List<ProductEntity> parle = productRepository.findAll(pageable).getContent();
        return parle;
    }

    public List<ProductEntity> fetchProducts(String sortBy,Integer pageNumber,String title){
        //pagination example

        Pageable pageable= PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy));
        List<ProductEntity> parle = productRepository.findByTitleContainingIgnoreCase(title,pageable);
        return parle;
    }
}
