package com.blogservice.service;

import com.blogservice.model.Blog;
import com.commonmodule.dto.BlogDto;

import java.util.List;

public interface BlogService {

    Blog save(Blog blog);

    Blog getById(Long id);

    Blog updated(Long id, Blog blog);

    void deleteById(Long id);

    List<Blog> getAll();
    List<BlogDto> getAllByUserId(Long id);
}
