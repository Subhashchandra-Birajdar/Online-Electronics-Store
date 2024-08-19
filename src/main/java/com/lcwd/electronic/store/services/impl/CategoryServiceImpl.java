package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.Category;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exceptions.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.HelperPageResponse;
import com.lcwd.electronic.store.repositories.CategoryRepository;
import com.lcwd.electronic.store.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${project.image}")
    private  String imagePath;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        return modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(String categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());

        Category updateCategory = categoryRepository.save(category);
        return modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()
                -> new ResourceNotFoundException("Category not found for this id"));

        //delete category cover image
        //images/abc.png
        String fullPath = imagePath + category.getCoverImage();

        // if category dont have image then it will throw exception
        try {
            //files through delete or java 17 through
            Path path = Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException ex){
            ex.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        categoryRepository.delete(category);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategories(int pageNumber, int pageSize, String sortBy, String sortDir) {
        //Sort  sort = Sort.by(sortBy);// here we can use ternary operator
        Sort  sort = (sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());

        //pageNumber default start from 0
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort); // PageRequest is implementation of Pageable

        Page<Category> page = categoryRepository.findAll(pageable);

        PageableResponse<CategoryDto> response = HelperPageResponse.getPageableResponse(page, CategoryDto.class);

        return response;
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id"));
        return modelMapper.map(category,CategoryDto.class);
    }

    //search
    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        List<Category> categories = categoryRepository.findByTitleContaining(keyword);
        List<CategoryDto> categoryDtoList = categories.stream().map(category -> modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }



}
