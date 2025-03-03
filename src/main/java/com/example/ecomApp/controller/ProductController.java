package com.example.ecomApp.controller;

import com.example.ecomApp.model.Product;
import com.example.ecomApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    ProductService service;


    @PostMapping("/product")
    public ResponseEntity<?> addProducts(@RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        try{
            Product createdProduct = service.addProduct(product, imageFile);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductbyID(@PathVariable String id){
        Product product = service.getProductbyID(id);
        if(product!=null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte []> getImageById(@PathVariable String productId){
        Product product= service.getProductbyID(productId);
        return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
    }

    @GetMapping("products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name){
        return new ResponseEntity<>(service.searchProduct(name), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> addProducts(@PathVariable String id, @RequestPart Product product, @RequestPart MultipartFile imageFile) throws IOException {
        Product product1 = null;
        try{
            product1= service.updateProduct(id, product, imageFile);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(product1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("product/{product_Id}")
    public ResponseEntity<Product> deleteProductbyID(@PathVariable String product_Id){
        Product product = service.getProductbyID(product_Id);
        if(product!=null){
            service.deleteProduct(product_Id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
