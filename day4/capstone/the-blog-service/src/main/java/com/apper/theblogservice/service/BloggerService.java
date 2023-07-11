package com.apper.theblogservice.service;

import com.apper.theblogservice.NoSuchElementFoundException;
import com.apper.theblogservice.model.Blogger;
import com.apper.theblogservice.repository.BloggerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloggerService {

    private final BloggerRepository bloggerRepository;

    public BloggerService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    public Blogger createBlogger(String email, String name, String password) {
        Blogger blogger = new Blogger();
        blogger.setEmail(email);
        blogger.setName(name);
        blogger.setPassword(password);

        return bloggerRepository.save(blogger);
    }

    public Blogger getBlogger(String id) throws NoSuchElementFoundException {
        Optional<Blogger> bloggerResult = bloggerRepository.findById(id);

        return bloggerResult.get();
    }

    public List<Blogger> getAllBloggers() {

        return (List<Blogger>)bloggerRepository.findAll();
    }
}