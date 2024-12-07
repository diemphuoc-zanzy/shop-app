package com.project.shopapp.models;

import com.project.shopapp.models.base.BaseModel;
import com.project.shopapp.dtos.request.CategoryRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Category(CategoryRequestDto categoryRequestDto) {
        this.name = categoryRequestDto.getName();
    }

    public void update(CategoryRequestDto categoryRequestDto) {
        this.name = categoryRequestDto.getName();
    }
}
