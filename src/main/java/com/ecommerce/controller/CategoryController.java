package com.ecommerce.controller;

import java.util.List;

import javax.swing.event.CaretEvent;

import com.ecommerce.model.Category;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        this.categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "new category created"),HttpStatus.CREATED);

    }

    @GetMapping("/categoryList")
     public List<Category> categoryList(){
         return this.categoryService.categoryList();
     }

     @PostMapping("/updateCategory/{categoryId}")
     public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId")int categoryId,
     @RequestBody Category category){
     //checking category is preswent or not
     if(!this.categoryService.findById(categoryId)){
        return new ResponseEntity<>(new ApiResponse(true, "category id not found"),HttpStatus.OK);
     }
        this.categoryService.editCategory(categoryId,category);
       
            return new ResponseEntity<>(new ApiResponse(true, "category updated"),HttpStatus.OK);
     }

    
}
