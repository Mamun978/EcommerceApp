package com.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
     
    @Autowired
     ProductService productService;

     @Autowired
     CategoryRepository categoryRepository;
    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){

       Optional<Category> categoryOptional=categoryRepository.findById(productDto.getCategory_id());
         if(!categoryOptional.isPresent()){
             return new ResponseEntity<>(new ApiResponse(false,"Category Does Not Found"),HttpStatus.BAD_REQUEST);
         }

         this.productService.createProduct(productDto,categoryOptional.get());
         
         return new ResponseEntity<>(new ApiResponse(true,"Category has been added successfully"),HttpStatus.CREATED);
        
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        List<ProductDto> products=productService.getAllProducts();
        
        return new ResponseEntity<>(products,HttpStatus.OK);  
        
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception{

         Optional<Category> optionalCategory= this.categoryRepository.findById(productDto.getCategory_id());



         if(!optionalCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Category Does Not Found"),HttpStatus.BAD_REQUEST);
         }

         this.productService.updateProduct(productId,productDto);
         return new ResponseEntity<>(new ApiResponse(true,"Product has been updated successfully"),HttpStatus.CREATED);
    }

}
