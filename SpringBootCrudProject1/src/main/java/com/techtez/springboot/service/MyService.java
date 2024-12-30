package com.techtez.springboot.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // Import logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtez.springboot.entity.Product;
import com.techtez.springboot.repository.MyRepository;

@Service // It tells that this is business logic
public class MyService {
    private static final Logger logger = LogManager.getLogger(MyService.class); // Logger instance

    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    // CRUD Operations:
    public Product saveProduct(Product p) {
        logger.info("Saving product: {}", p); // Log product saving
        return myRepository.save(p); // save() is a method of JpaRepository used to insert data into table with the help of Hibernate.
    }

    // Read all rows from the product table.
    public List<Product> getAllProducts() {
        logger.info("Fetching all products"); // Log fetching operation
        return myRepository.findAll(); // findAll() method of JpaRepository used to read all rows from Table then convert into each row into each object and return List(of Products).
    }

    // Update a row(Product) with new product based on pno
    public Product updateProduct(int pno, Product newProduct) {
        logger.info("Updating product with pno: {}", pno); // Log update operation
        Optional<Product> existingProduct = myRepository.findById(pno);
        if (existingProduct.isPresent()) {    
            Product p = existingProduct.get();
            logger.info("Existing product found: {}", p); // Log existing product details
            p.setPname(newProduct.getPname());
            p.setPprice(newProduct.getPprice());
            p.setPqty(newProduct.getPqty());

            // Save a new product to the database using JPA's save() method
            Product updatedProduct = myRepository.save(p);
            logger.info("Updated product: {}", updatedProduct); // Log updated product details
            return updatedProduct;
        } else {
            logger.error("Product not available with pno: {}", pno); // Log error if product not found
            throw new RuntimeException("Product not available");
        }
    }

    // Delete a row based on pno
    public void deleteProduct(int pno) {
        logger.info("Deleting product with pno: {}", pno); // Log delete operation
        myRepository.deleteById(pno);
        logger.info("Product with pno: {} deleted successfully", pno); // Log successful deletion
    }
}
