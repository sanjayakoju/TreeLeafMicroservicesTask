package com.blogservice.controller;

import com.blogservice.model.Blog;
import com.blogservice.service.BlogService;
import com.commonmodule.dto.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.updated(id, blog), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER') || hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        blogService.deleteById(id);
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(blogService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<BlogDto>> getAllByUser(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.getAllByUserId(id), HttpStatus.OK);
    }
}
