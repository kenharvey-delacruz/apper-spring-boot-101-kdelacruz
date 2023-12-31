//package com.apper.theblogservice.controller;
//
//import com.apper.theblogservice.exceptions.NoSuchElementFoundException;
//import com.apper.theblogservice.model.Blog;
//import com.apper.theblogservice.model.Blogger;
//import com.apper.theblogservice.payload.*;
//import com.apper.theblogservice.service.BlogService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
////@RequestMapping("blog")
//public class BlogApi {
//
//    private final BlogService blogService;
//
//    public BlogApi(BlogService blogService) {
//        this.blogService = blogService;
//    }
//
//
//    @PostMapping("/blog")
//    @ResponseStatus(HttpStatus.CREATED)
//    public CreateBlogResponse createBlog(@RequestBody @Valid CreateBlogRequest request){
//        System.out.println(request);
//
//        Blog createdBlog = blogService.createBlog(request.getTitle(), request.getBody(), request.getBlogger_id());
//
//        CreateBlogResponse response = new CreateBlogResponse();
//        response.setId(createdBlog.getId());
//        response.setBlogger_id(createdBlog.getBlogger_id());
//        response.setCreated_at(createdBlog.getCreatedAt());
//        response.setLast_updated(createdBlog.getLastUpdate());
//
//        return response;
//    }
//
//    @GetMapping("/blog/{id}")
//    public BlogDetails getBlog(@PathVariable String id) throws NoSuchElementFoundException {
//
//        Blog blog = blogService.getBlog(id);
//
//        BlogDetails blogDetails = new BlogDetails();
//        blogDetails.setBlogger_id(blog.getId());
//        blogDetails.setTitle(blog.getTitle());
//        blogDetails.setBody(blog.getBody());
//        blogDetails.setCreatedAt(blog.getCreatedAt());
//        blogDetails.setLastUpdated(blog.getLastUpdate());
//
//        return blogDetails;
//    }
//
//    @GetMapping("/blog")
//    public List<BlogDetails> getAllBloggers() {
//        List<BlogDetails> blogList = new ArrayList<>();
//
//        for (Blog blog : blogService.getAllBlogs()) {
//            BlogDetails blogDetails = new BlogDetails();
//            blogDetails.setBlogger_id(blog.getId());
//            blogDetails.setTitle(blog.getTitle());
//            blogDetails.setBody(blog.getBody());
//            blogDetails.setCreatedAt(blog.getCreatedAt());
//            blogDetails.setLastUpdated(blog.getLastUpdate());
//            blogList.add(blogDetails);
//        }
//
//        return blogList;
//    }
//
//
//
//}
