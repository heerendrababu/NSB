package com.techtez.springboot.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // Import logger
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtez.springboot.entity.Product;

@Repository  // Marks this as a repository for database operations
public interface MyRepository extends JpaRepository<Product, Integer> {
    static final Logger logger = LogManager.getLogger(MyRepository.class); // Logger instance

    // CRUD operations are provided by JpaRepository, we don't need custom methods unless needed
}
