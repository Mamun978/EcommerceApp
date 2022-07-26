package com.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="wishlist")
public class WishList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = User.class,fetch =FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="Created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product; //one product can be part of many wishlist

    public WishList(Integer id, User user, Date createdDate, Product product) {
        this.id = id;
        this.user = user;
        this.createdDate = createdDate;
        this.product = product;
    }

    public WishList(User user2, Product product2) {
        this.user=user2;
        this.product=product2;
        this.createdDate=new Date();
	}

    

	public WishList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    


}
