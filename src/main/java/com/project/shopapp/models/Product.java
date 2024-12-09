package com.project.shopapp.models;

import com.project.shopapp.models.base.BaseModel;
import com.project.shopapp.dtos.request.ProductRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 350)
    private String name;

    private Double price;

    @Column(length = 300)
    private String thumbnail;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;



    public Product(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.thumbnail = productRequestDto.getThumbnail();
        this.description = productRequestDto.getDescription();

    }

    public void update(ProductRequestDto productRequestDto) {
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.thumbnail = productRequestDto.getThumbnail();
        this.description = productRequestDto.getDescription();
    }
}
