package com.techtez.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // Import logger

@Entity  // Specifies that this class is a JPA entity representing a table
public class Product {
    private static final Logger logger = LogManager.getLogger(Product.class); // Logger instance

    @Id   // Marks this as the primary key column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Requesting Hibernate to generate automatic incremented values for the id column.
    private int pno;
    private String pname;
    private float pprice;
    private int pqty;

    public Product() {
        logger.debug("Creating a new Product instance"); // Log constructor call
    }

    public Product(int pno, String pname, float pprice, int pqty) {
        logger.debug("Creating Product with pno: {}, pname: {}, pprice: {}, pqty: {}", pno, pname, pprice, pqty); // Log parameterized constructor
        this.pno = pno;
        this.pname = pname;
        this.pprice = pprice;
        this.pqty = pqty;
    }

    // Getters and Setters with logging

    public int getPno() {
        logger.debug("Fetching pno: {}", pno); // Log getter method
        return pno;
    }

    public void setPno(int pno) {
        logger.debug("Setting pno to: {}", pno); // Log setter method
        this.pno = pno;
    }

    public String getPname() {
        logger.debug("Fetching pname: {}", pname); // Log getter method
        return pname;
    }

    public void setPname(String pname) {
        logger.debug("Setting pname to: {}", pname); // Log setter method
        this.pname = pname;
    }

    public float getPprice() {
        logger.debug("Fetching pprice: {}", pprice); // Log getter method
        return pprice;
    }

    public void setPprice(float pprice) {
        logger.debug("Setting pprice to: {}", pprice); // Log setter method
        this.pprice = pprice;
    }

    public int getPqty() {
        logger.debug("Fetching pqty: {}", pqty); // Log getter method
        return pqty;
    }

    public void setPqty(int pqty) {
        logger.debug("Setting pqty to: {}", pqty); // Log setter method
        this.pqty = pqty;
    }
}
