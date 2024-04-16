package com.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min = 4, message = "Post title should have at least 4 characters")
    private String title;

    @NotEmpty
    @Size(min = 4, message = " description should have at least 4 characters")
    private String description;

    @NotEmpty
    private String content;

}
