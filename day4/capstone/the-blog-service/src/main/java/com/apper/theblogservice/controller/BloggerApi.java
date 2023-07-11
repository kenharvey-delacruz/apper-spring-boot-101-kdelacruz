package com.apper.theblogservice.controller;

import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.payload.*;
import com.apper.theblogservice.service.BlogService;
import com.apper.theblogservice.service.BloggerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
//@RequestMapping("blogger")
public class BloggerApi {

    private final BloggerService bloggerService;
    private final BlogService blogService;

    public BloggerApi(BloggerService bloggerService, BlogService blogService) {
        this.bloggerService = bloggerService;
        this.blogService = blogService;
    }


    @PostMapping("/blogger")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBloggerResponse createBlogger(@RequestBody @Valid CreateBloggerRequest request) throws EmailAlreadyRegisteredException {
        System.out.println(request);

        if (bloggerService.isEmailRegistered(request.getEmail())) {
            throw new EmailAlreadyRegisteredException("Email already registered");
        }

        Blogger createdBlogger = bloggerService.createBlogger(request.getEmail(), request.getName(), request.getPassword());

        CreateBloggerResponse response = new CreateBloggerResponse();
        response.setId(createdBlogger.getId());
        response.setDateRegistration(createdBlogger.getCreatedAt());

        return response;
    }

    @GetMapping("/blogger/{id}")
    public BloggerDetails getBlogger(@PathVariable String id) {

        Blogger blogger = bloggerService.getBlogger(id);

        BloggerDetails bloggerDetails = new BloggerDetails();
        bloggerDetails.setId(blogger.getId());
        bloggerDetails.setName(blogger.getName());
        bloggerDetails.setEmail(blogger.getEmail());
        bloggerDetails.setDateRegistration(blogger.getCreatedAt());

        return bloggerDetails;
    }

    @GetMapping("/blogger")
    public List<BloggerDetails> getAllBloggers() {
        List<BloggerDetails> bloggerList = new ArrayList<>();

        for (Blogger blogger : bloggerService.getAllBloggers()) {
            BloggerDetails bloggerDetails = new BloggerDetails();
            bloggerDetails.setId(blogger.getId());
            bloggerDetails.setName(blogger.getName());
            bloggerDetails.setEmail(blogger.getEmail());
            bloggerDetails.setDateRegistration(blogger.getCreatedAt());
            bloggerList.add(bloggerDetails);
        }

        return bloggerList;
    }



    @PostMapping("/blog")
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

    @PutMapping("/blog/{blog_id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateBlogResponse updateBlog(@PathVariable String blog_id, @RequestBody UpdateBlogRequest request) {

        blogService.updateBlog(blog_id,
            request.getTitle(),
            request.getBody());

        Blog blog = blogService.getBlog(blog_id);
        UpdateBlogResponse response = new UpdateBlogResponse();
        response.setId(blog_id);
        response.setBlogger_id(blog.getBlogger_id());
        response.setCreated_at(blog.getCreatedAt());
        response.setLast_updated(LocalDateTime.now());

        return response;
    }

    @GetMapping("/blog/{id}")
    public BlogDetails getBlog(@PathVariable String id) {

        Blog blog = blogService.getBlog(id);

        BlogDetails blogDetails = new BlogDetails();
        blogDetails.setBlogger_id(blog.getBlogger_id());
        blogDetails.setTitle(blog.getTitle());
        blogDetails.setBody(blog.getBody());
        blogDetails.setCreatedAt(blog.getCreatedAt());
        blogDetails.setLastUpdated(blog.getLastUpdate());

        return blogDetails;
    }

    @GetMapping("/blog")
    public List<BlogDetails> getAllBlogs() {
        List<BlogDetails> blogList = new ArrayList<>();

        for (Blog blog : blogService.getAllBlogs()) {
            BlogDetails blogDetails = new BlogDetails();
            blogDetails.setBlogger_id(blog.getBlogger_id());
            blogDetails.setTitle(blog.getTitle());
            blogDetails.setBody(blog.getBody());
            blogDetails.setCreatedAt(blog.getCreatedAt());
            blogDetails.setLastUpdated(blog.getLastUpdate());
            blogList.add(blogDetails);
        }

        return blogList;
    }

    @GetMapping("/blog/blogger/{blogger_id}")
    public List<BlogDetails> getAllBlogsByBlogger(@PathVariable String blogger_id) {

        List<BlogDetails> blogList = new ArrayList<>();

        for (Blog blog : blogService.getAllBlogs()) {
            if (blog.getBlogger_id().equals(blogger_id)) {
                BlogDetails blogDetails = new BlogDetails();
                blogDetails.setBlogger_id(blogger_id);
                blogDetails.setTitle(blog.getTitle());
                blogDetails.setBody(blog.getBody());
                blogDetails.setCreatedAt(blog.getCreatedAt());
                blogDetails.setLastUpdated(blog.getLastUpdate());
                blogList.add(blogDetails);
            }
        }
        return blogList;
    }

}
