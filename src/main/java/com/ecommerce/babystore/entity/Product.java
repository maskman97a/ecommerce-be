package com.ecommerce.babystore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Lob
    @Column( columnDefinition = "longtext")
    private String description;
    private double rate_star;
    private String title1;
    private String title2;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<TypesOfProduct> typesOfProducts;
//    @ManyToOne
//    @JsonManagedReference
//    @JoinColumn(name="category_id")
//    private Category category;
//
//
//    @OneToMany(mappedBy = "product")
//    @JsonIgnore
//    private List<Rates> rates;
//
//    @ManyToOne
//    @JsonManagedReference
//    @JoinColumn(name="supplier_id")
//    private Supplier supplier;
//
//    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonManagedReference
//    private List<Image_Product> imageProducts;
//
//    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonManagedReference
//    private List<ImageClassifications1> imageClassifications1List;


    public Product(String name, String description, String title1, String title2) {
        this.name = name;
        this.description = description;
        this.title1 = title1;
        this.title2 = title2;
    }




}
