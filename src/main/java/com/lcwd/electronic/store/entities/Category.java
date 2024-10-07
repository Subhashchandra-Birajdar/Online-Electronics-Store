package com.lcwd.electronic.store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
public class Category {

    @Id
    @Column(name="Id")
    private String CategoryId;

    @Column(name="category_title",length=60,nullable = false)
    private String title;

    @Column(name = "category_desc",length = 500)
    private String description;

    private String coverImage;

    //other attribute if you have

    // here one category contain many products(one to many mapping)
   // suppose, if you large amount of data then you can use hashmap
   // here fetch type lazy means when we get category then product will not get,products will get on demand.
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<Product> products = new ArrayList<>();
}
