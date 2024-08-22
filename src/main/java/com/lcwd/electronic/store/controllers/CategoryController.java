package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.CategoryDto;
import com.lcwd.electronic.store.dtos.PageableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.payloads.ApiResponseMessage;
import com.lcwd.electronic.store.payloads.ImageResponse;
import com.lcwd.electronic.store.services.CategoryService;
import com.lcwd.electronic.store.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String imageUploadPath; // dynamic

    // create Category API
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        // call the service to save object
        CategoryDto category = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // update Category API
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable String categoryId,
            @Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

    // delete Category API
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(
            @PathVariable String categoryId){
        categoryService.deleteCategoryById(categoryId);
        ApiResponseMessage message = ApiResponseMessage.builder().message("Category is deleted successfully").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    // Get All Category API
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sorBy",defaultValue = "title",required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir){
        return new ResponseEntity<>(categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    // Get Single Category API
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String categoryId){
        return new ResponseEntity<>(categoryService.getSingleCategory(categoryId),HttpStatus.OK);
    }

    // search user
    @GetMapping("/search-by-title/{keywords}")
    public ResponseEntity<List<CategoryDto>> searchUser(@PathVariable("keywords")String keywords){
        return new ResponseEntity<>(categoryService.searchCategory(keywords),HttpStatus.OK);
    }

    //upload category image
    // http://localhost:9090/categories/image/categoryId-
    @PostMapping("/image/{categoryId}")
    public  ResponseEntity<ImageResponse> uploadUserImage(
            @RequestParam("categoryImage") MultipartFile image,
            @PathVariable String categoryId) throws IOException {
        String imageName = fileService.uploadFile(image, imageUploadPath);
        CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
        categoryDto.setCoverImage(imageName);
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryId,categoryDto);
        ImageResponse imageResponse = ImageResponse.builder()
                .message("Category image is updated !!")
                .imageName(imageName).success(true).status(HttpStatus.CREATED).build();
        return  new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
    }

    //serve category image
    //userid we will get in path
    @GetMapping("/image/{categoryId}")
    public void serveUserImage(@PathVariable String categoryId,
                               HttpServletResponse response) throws IOException {

        CategoryDto categoryDto = categoryService.getSingleCategory(categoryId);
        InputStream resource = fileService.getResource(imageUploadPath, categoryDto.getCoverImage());// in resource image came
        // now that image we will use as image
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        // resource we will convert into our class (resource - > class response -> streamUtils
        StreamUtils.copy(resource,response.getOutputStream());

    }



}
