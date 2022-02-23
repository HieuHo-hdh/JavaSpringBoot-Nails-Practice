package com.landingis.api.storage.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@Entity
@Table(name=TablePrefix.PREFIX_TABLE + "product")
public class Product extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Double price;
    private String image;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "short_description", columnDefinition = "    TEXT")
    private String shortDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Product parentProduct;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "parentProduct",
            cascade =  CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<Product> productList;

    @Column(name="has_child")
    private Boolean hasChild;

    @Column(name="label_color")
    private String labelColor;

    @Min(0)
    @Max(100)
    @Column(name="sale_off")
    private Integer saleOff;

}
