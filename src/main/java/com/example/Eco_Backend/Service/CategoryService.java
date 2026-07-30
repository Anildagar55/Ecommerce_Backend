package com.example.Eco_Backend.Service;

import com.example.Eco_Backend.DTO.CategoryRequest;
import com.example.Eco_Backend.DTO.CategoryResponse;
import com.example.Eco_Backend.Entity.Category;
import com.example.Eco_Backend.ExceptionHandler.ResourceNotFoundException;
import com.example.Eco_Backend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest request){
        Category parent=null;
        if (request.getParentId() != null) {
            parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found: " + request.getParentId()));
        }
    Category category = Category.builder()
            .name(request.getName())
            .parent(parent)
            .build();
        return mapToResponse(categoryRepository.save(category));
    }
    public CategoryResponse mapToResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .parentId(category.getParent() !=null ? category.getParent().getId() :null)
                .parentName(category.getParent() != null ? category.getParent().getName():null)
                .build();
    }
}
