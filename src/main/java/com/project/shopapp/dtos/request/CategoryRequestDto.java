package com.project.shopapp.dtos.request;

import com.project.shopapp.dtos.request.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto extends BaseDto {

    private Long id;

    private String name;
}
