package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBlogRequest {

    @JsonProperty("title")
    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("body")
    @NotBlank(message = "body is required")
    private String body;

    @JsonProperty("blogger_id")
    @NotBlank(message = "blogger_id is required")
    private String blogger_id;


}