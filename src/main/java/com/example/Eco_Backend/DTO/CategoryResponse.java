package com.example.Eco_Backend.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private Long parentId;
    private String parentName;
}
