package com.example.SpringEcom.controller;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product.getId() > 0) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    // without Image
//    @PostMapping("/product")
//    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
//        productService.addProduct(product);
//        Product addedProduct = productService.getProductById(product.getId());
//        return new ResponseEntity<>(addedProduct, HttpStatus.ACCEPTED);
//    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId) {
        Product product = productService.getProductById(productId);
        if (product.getId() > 0) {
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product addedProduct = null;
        try {
            addedProduct = productService.addProduct(product, imageFile);
            return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        Product updatedProduct = null;
        try {
            updatedProduct = productService.updateProduct(product, imageFile);
            return new ResponseEntity<>("Updated", HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product.getId() > 0) {
            productService.deleteProductById(id);
            return ResponseEntity.ok().body("Deleted");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product with this Id Do not Exist");
    }

    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("searching with " + keyword);
        return ResponseEntity.ok().body(products);
    }

}
