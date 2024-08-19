package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;

public interface CategoryService {
    // loosely coupling technique

    // create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(String categoryId,CategoryDto categoryDto);

    //delete
    void deleteCategoryById(String categoryId);

    //getall
    PageableResponse<CategoryDto> getAllCategories(int pageNumber,int pageSize,String sortBy,String sortDir);

    // get single category detail
    CategoryDto getSingleCategory(String categoryId);

    //search category
}
