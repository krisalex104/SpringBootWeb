package com.example.springtutorial.springbootweb.repositories;

import com.example.springtutorial.springbootweb.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    List<ProductEntity> findByTitle(String title);

    List<ProductEntity> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<ProductEntity> findByQuantityAndPrice(Integer quantity, BigDecimal price);

    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(Integer quantity,BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

//    Optional<ProductEntity> findByTitleAndPrice(String title,BigDecimal price);

    @Query("select e from ProductEntity e where e.title=?1 and e.price=?2")
    Optional<ProductEntity> findByTitleAndPrice(String title,BigDecimal price);

    @Query("select e from ProductEntity e where e.title=:title and e.quantity=:quantity")
    Optional<ProductEntity> findByTitleAAndQuantity(String title,Integer quantity);

}
