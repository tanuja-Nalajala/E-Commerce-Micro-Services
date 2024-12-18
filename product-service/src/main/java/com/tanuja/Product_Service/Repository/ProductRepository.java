package com.tanuja.Product_Service.Repository;

import com.tanuja.Product_Service.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String > {
}
