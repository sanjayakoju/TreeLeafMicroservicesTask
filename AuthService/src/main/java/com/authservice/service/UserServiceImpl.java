package com.authservice.service;

import com.authservice.client.BlogFeignClientService;
import com.authservice.model.dto.UserBlogDto;
import com.authservice.repository.UserRepository;
import com.authservice.utils.UserUtil;
import com.commonmodule.dto.BlogDto;
import com.commonmodule.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogFeignClientService blogFeignClientService;

    @Override
    public UserBlogDto getAllBlogByUser() {
        CustomUserDetails loggedInUser = UserUtil.getCurrentUser();
        List<BlogDto> blogDtoList = blogFeignClientService.getBlogByUser(loggedInUser.getId());
        return UserBlogDto.builder()
                .id(loggedInUser.getId())
                .username(loggedInUser.getUsername())
                .blog(blogDtoList)
                .build();
    }
}
