package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    
    @Autowired
     private CategoryRepository categoryRepository;
     //create category
    public void createCategory(Category category){
       this.categoryRepository.save(category);
    }
     //get all category
    public List<Category> categoryList(){
        return this.categoryRepository.findAll();
    }
 //updateing category
 
    public void editCategory(int categoryId,Category updatingCategory){
        Category category2=this.categoryRepository.getById(categoryId);
         category2.setCategoryName(updatingCategory.getCategoryName());
         category2.setDescription(updatingCategory.getDescription());
         category2.setImageUrl(updatingCategory.getImageUrl());
         this.categoryRepository.save(category2);
    }
 //finding if there is a category is or not

    public boolean findById(int categoryId) {
        return this.categoryRepository.findById(categoryId).isPresent();
    }
}
