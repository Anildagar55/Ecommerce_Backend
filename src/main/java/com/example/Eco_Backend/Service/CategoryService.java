package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.CategoryRequest;
import com.example.Eco_Backend.DTO.CategoryResponse;
import com.example.Eco_Backend.Entity.Categaries;
import com.example.Eco_Backend.Repository.CategoryRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request){
        Categaries parent=null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found: " + request.getParentId()));
        }
    Categaries categaries=Categaries.builder()
            .name(request.getName())
            .parent(parent)
            .build();
        return mapToResponse(categoryRepository.save(categaries));
    }
    public CategoryResponse mapToResponse(Categaries categaries){
        return CategoryResponse.builder()
                .id(categaries.getId())
                .name(categaries.getName())
                .parentId(categaries.getParent() !=null ? categaries.getParent().getId() :null)
                .parentName(categaries.getParent() != null ?categaries.getParent().getName():null)
                .build();
    }
}
