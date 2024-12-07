package com.project.shopapp.dtos.response;

import com.project.shopapp.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseDto {

    private Long id;

    private String name;

    public CategoryResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
