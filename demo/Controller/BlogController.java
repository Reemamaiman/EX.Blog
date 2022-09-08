package com.example.demo.Controller;

import com.example.demo.dto.ApiDto;
import com.example.demo.model.Blog;
import com.example.demo.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController @RequiredArgsConstructor @RequestMapping("/api/v1/blog")
@CrossOrigin(origins = "*")
public class BlogController {
    private final BlogRepository blogRepository;

    @GetMapping
    public ResponseEntity getBlogs(){
        return ResponseEntity.status(200).body(blogRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog){
        blogRepository.save(blog);
        return ResponseEntity.status(201).body(new ApiDto("New blog added !",201));
    }
    @GetMapping("/{id}")
    public ResponseEntity getBlogById(@PathVariable Integer id){
        Blog blog=blogRepository.findById(id).get();
        return ResponseEntity.status(200).body(blog);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id){
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isEmpty()){
            return ResponseEntity.status(400).body("Invalid id");
        }
        blogRepository.delete(blog.get());
        return ResponseEntity.status(200).body(new ApiDto("Blog deleted",200));
    }
}


