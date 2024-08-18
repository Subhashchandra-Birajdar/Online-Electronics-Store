package com.lcwd.electronic.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private String CategoryId;

    @Size(min = 4,message = "The title must be minimum 4 character")
    @NotBlank(message = "title not be blank")
    private String title;

    @NotBlank(message = "Description required")
    private String description;

    private String coverImage;


}
