package com.authservice.model.dto;

import com.commonmodule.dto.BlogDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBlogDto {

    private Long id;
    private String username;
    private List<BlogDto> blog = new ArrayList<>();
}
