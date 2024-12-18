package com.tanuja.Product_Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanuja.Product_Service.Model.Product;
import com.tanuja.Product_Service.Repository.ProductRepository;
import com.tanuja.Product_Service.dto.ProductRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static  MongoDBContainer mongoDBContainer=new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"))
			.waitingFor(Wait.forListeningPort())
			.withStartupTimeout(java.time.Duration.ofSeconds(60));
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;  //helps pojo to/from POJO objects

	@Autowired
	private ProductRepository productRepository;

	@DynamicPropertySource
	public static void  setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest= getProductRequest();
		String productRequestString= objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON) //request body
				.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1,productRepository.findAll().size());

	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("iphone")
				.description("Iphone-13")
				.price(BigDecimal.valueOf(800))
				.build();
	}

}
