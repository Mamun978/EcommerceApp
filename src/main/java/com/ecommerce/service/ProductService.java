package com.ecommerce.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exceptions.ProductNotExistsException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

     @Autowired
     ProductRepository productRepository;

    public void createProduct(ProductDto productDto,Category category){
            
      Product product=new Product();

      product.setName(productDto.getName());
      product.setId(productDto.getId());
      product.setDescription(productDto.getDescription());
      product.setImageURL(productDto.getImageURL());
      product.setPrice(productDto.getPrice());
      product.setCategory(category);

      productRepository.save(product);
    
      
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto=new ProductDto();

        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        productDto.setCategory_id(product.getCategory().getId());


        return  productDto;
    }

    public List<ProductDto> getAllProducts(){

        List<Product> allProduct=this.productRepository.findAll();

        List<ProductDto> productDtos=new ArrayList<>();

        for(Product product: allProduct){
            productDtos.add(getProductDto(product));
        }

        return productDtos;
          
        
    }

    public void updateProduct(Integer productId, ProductDto productDto) throws Exception {

        Optional<Product> OptionalProduct=productRepository.findById(productId);

        if(!OptionalProduct.isPresent()){
            throw new Exception("Product id does not exist");
        }

        Product product=OptionalProduct.get();

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());

        this.productRepository.save(product);

    }

    public Product findById(Integer productId) throws ProductNotExistsException {

        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw  new ProductNotExistsException("Product id is invalid "+productId);
        }
        return  optionalProduct.get();
    }
}
