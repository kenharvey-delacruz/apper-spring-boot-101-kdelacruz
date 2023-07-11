package com.apper.theblogservice.service;

import com.apper.theblogservice.exceptions.EmailAlreadyRegisteredException;
import com.apper.theblogservice.model.Blog;
import com.apper.theblogservice.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog createBlog(String title, String body, String blogger_id) {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setBody(body);
        blog.setBlogger_id(blogger_id);

        return blogRepository.save(blog);
    }

    public Blog getBlog(String id) throws EmailAlreadyRegisteredException {
        Optional<Blog> blogResult = blogRepository.findById(id);

        return blogResult.get();
    }

    public List<Blog> getAllBlogs() {

        return (List<Blog>)blogRepository.findAll();
    }

    public Blog updateBlog(String blog_id, String title, String body){

        Optional<Blog> blogResult = blogRepository.findById(blog_id);

        Blog blog = blogResult.get();

        blog.setTitle(title);
        blog.setBody(body);
        blog.setLastUpdate(LocalDateTime.now());

        return blogRepository.save(blog);
    }

}
