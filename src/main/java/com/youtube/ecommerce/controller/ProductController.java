package com.youtube.ecommerce.controller;

import com.youtube.ecommerce.dao.ProductDao;
import com.youtube.ecommerce.entity.ImageModel;
import com.youtube.ecommerce.entity.Product;
import com.youtube.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value = {"/addNewProduct"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart(name = "product") Product product,@RequestPart(name = "imageFile") MultipartFile[] file){

//      return  productService.addNewProduct(product);
        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
            
        }

    }
    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels=new HashSet<>();
        for(MultipartFile file:multipartFiles){
            ImageModel imageModel=new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);


        }
        return imageModels;
    }
   // @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts(){
        return productService.getAllProducts();

    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getProductDetailsById/{productId}"})
    public Product getProductDetailsById(@PathVariable("productId") Integer productId){
          return productService.getProductDetailsById(productId);
    }
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails(@PathVariable("productId") Integer productId){
        productService.deleteProductDetails(productId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProductDetails/{isSingleProductCheckout}/{productId}"})
    public List<Product> getProductDetails(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
                                           @PathVariable(name = "productId") Integer productId){

       return productService.getProductDetails(isSingleProductCheckout,productId);

    }



}