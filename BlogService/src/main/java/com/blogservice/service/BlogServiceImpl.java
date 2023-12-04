package com.blogservice.service;

import com.blogservice.exception.ResourceNotFoundException;
import com.blogservice.model.Blog;
import com.blogservice.repository.BlogRepository;
import com.commonmodule.dto.BlogDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getById(Long id) {
        return blogRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Blog Not Found!!"));
    }

    @Override
    public Blog updated(Long id, Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public List<BlogDto> getAllByUserId(Long id) {
        return blogRepository.findAllByUserId(id).stream()
                .map(blog -> {
                    return mapper.map(blog, BlogDto.class);
                })
                .collect(Collectors.toList());
    }
}
