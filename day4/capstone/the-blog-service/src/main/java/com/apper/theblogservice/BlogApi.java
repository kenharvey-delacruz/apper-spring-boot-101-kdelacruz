package com.apper.theblogservice;

import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("blog")
public class BlogApi {

    private final BlogService blogService;

    public BlogApi(BlogService blogService) {
        this.blogService = blogService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request){
        System.out.println(request);

        Blog createdBlog = blogService.createBlog(request.getTitle(), request.getBody(), request.getBlogger_id());

        CreateBlogResponse response = new CreateBlogResponse();
        response.setId(createdBlog.getId());
        response.setBlogger_id(createdBlog.getBlogger_id());
        response.setCreated_at(createdBlog.getCreatedAt());
        response.setLast_updated(createdBlog.getLastUpdate());

        return response;
    }




}
