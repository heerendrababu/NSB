package com.techtez.springboot.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;  // Import for logger
import org.apache.logging.log4j.Logger;     // Import for logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtez.springboot.entity.Product;
import com.techtez.springboot.service.MyService;

//url: http://localhost:8082/api/v1/Product
@RestController  // Indicates this class handles REST API requests
@RequestMapping("/api")   // Base URL for all endpoints in this controller
public class MyController 
{
   private static final Logger logger = LogManager.getLogger(MyController.class); // Logger initialization
   
   private final MyService myService;
   
   @Autowired
   public MyController(MyService myService)
   {
       this.myService = myService;
   }
   
   // let us insert a row into product table from controller
   // data getting from browser so we use Post Request
   // Insert a new product into the database
   @PostMapping("/product")
   public Product createProduct(@RequestBody Product p)
   {
       logger.info("Creating a new product with details: {}", p);  // Logging the product data
       return myService.saveProduct(p);
   }

   // Read data....
   @GetMapping("/read")
   public List<Product> getAllProductsData()
   {
       logger.info("Fetching all product data."); // Logging fetch operation
       return myService.getAllProducts();
   }
   
   // Updating....
   @PutMapping("/update/{pno}")
   public ResponseEntity<Product> updateProductData(@PathVariable int pno, @RequestBody Product p) {
       logger.info("Updating product with ID: {}", pno);  // Logging the product ID being updated
       try {
           Product updatedProduct = myService.updateProduct(pno, p);
           logger.info("Product updated successfully: {}", updatedProduct);  // Logging successful update
           return ResponseEntity.ok(updatedProduct);
       } catch (Exception e) {
           logger.error("Error occurred while updating product with ID: {}", pno, e);  // Logging the error
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
   }    

   // Deleting....
   @DeleteMapping("/delete/{id}")
   public void deleteProductData(@PathVariable int id)
   {
       logger.info("Deleting product with ID: {}", id);  // Logging the product ID being deleted
       myService.deleteProduct(id);
   }
}
