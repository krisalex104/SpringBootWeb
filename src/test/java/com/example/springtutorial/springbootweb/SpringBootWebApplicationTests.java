package com.example.springtutorial.springbootweb;

import com.example.springtutorial.springbootweb.entities.ProductEntity;
import com.example.springtutorial.springbootweb.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBootWebApplicationTests {

    @Autowired
    ProductRepository productRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void testRepository() {

        ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("nestle chocolate")
				.price(BigDecimal.valueOf(25.6))
				.quantity(12).build();
		ProductEntity save = productRepository.save(productEntity);
		System.out.println(save);
	}

	@Test
	void getRepository(){
		List<ProductEntity> entityList=productRepository.findByCreatedAtAfter(LocalDateTime.of(2024,1,1,0,0,0));
		System.out.println(entityList);

		List<ProductEntity> byQuantityAndPrice = productRepository.findByQuantityAndPrice(2, BigDecimal.valueOf(12));
		System.out.println(byQuantityAndPrice);

		List<ProductEntity> byQuantityGreaterThanOrPriceLessThan = productRepository.findByQuantityGreaterThanOrPriceLessThan(1, BigDecimal.valueOf(14));
		System.out.println(byQuantityGreaterThanOrPriceLessThan);

		List<ProductEntity> byTitleLike = productRepository.findByTitleLike("%parle%");
		System.out.println(byTitleLike);

		Optional<ProductEntity> nestleChocolate = productRepository.findByTitleAndPrice("nestle chocolate", BigDecimal.valueOf(25.6));
		nestleChocolate.ifPresent(System.out::println);

	}

}
