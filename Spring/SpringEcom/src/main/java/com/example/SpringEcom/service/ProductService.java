package com.example.SpringEcom.service;

import com.example.SpringEcom.model.Product;
import com.example.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public List<Product> getProducts() {
        return productRepo.findAll();
    }


    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(new Product(-1));
    }

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getName());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }

    public Product updateProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getName());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return productRepo.save(product);
    }


    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
