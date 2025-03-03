package com.example.ecomApp.repository;

import com.example.ecomApp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product, String> {
    @Query("{'$or': [{'name': {$regex: ?0, $options: 'i'}}, {'description': {$regex: ?0, $options: 'i'}}, {'category': {$regex: ?0, $options: 'i'}}, {'brand': {$regex: ?0, $options: 'i'}}]}")
    List<Product> searchProduct(String keyword);

}
