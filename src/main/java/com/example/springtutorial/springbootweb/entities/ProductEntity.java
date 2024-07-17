package com.example.springtutorial.springbootweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "sku",columnNames = {"sku"}),
                @UniqueConstraint(name = "title_price_unique",columnNames = {"title","price"})
        },
        indexes = {
                @Index(name = "sku_index",columnList = "sku")
        }
)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 20)
    private String sku;

    @Column(name = "title")
    private String title;

    private BigDecimal price;
    private Integer quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
