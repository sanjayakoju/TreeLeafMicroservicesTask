package com.authservice.client;

import com.commonmodule.dto.BlogDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BLOG-SERVICE")
public interface BlogFeignClientService {

    @GetMapping("/blogs/user/{id}")
    List<BlogDto> getBlogByUser(@PathVariable Long id);
}
