package com.tanuja.Product_Service.Service;

import com.tanuja.Product_Service.Model.Product;
import com.tanuja.Product_Service.Repository.ProductRepository;
import com.tanuja.Product_Service.dto.ProductRequest;
import com.tanuja.Product_Service.dto.ProductResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class ProductService {


    private  final ProductRepository productRepository;

//    public ProductService(ProductRepository productRepository){
//        this.productRepository=productRepository;
//    }

    public void createProduct(ProductRequest productRequest){
        Product product=Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        //add logs
        //log.info("product " + product.getId()+ "is saved");
        //log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();

    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
