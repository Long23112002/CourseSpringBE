package com.example.coursel_be.service.impl;

import com.example.coursel_be.entity.Blog;
import com.example.coursel_be.entity.User;
import com.example.coursel_be.exceptions.AppException;
import com.example.coursel_be.exceptions.ErrorCode;
import com.example.coursel_be.repository.BlogRepository;
import com.example.coursel_be.repository.UserRepository;
import com.example.coursel_be.request.blog.BlogRequest;
import com.example.coursel_be.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    @Override
    public String saveBlog(BlogRequest blogRequest) {
        try {
            Optional<User> user = Optional.ofNullable(userRepository.findById(blogRequest.getIdUser()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));

            if (user.isEmpty()) {
                throw new AppException(ErrorCode.USER_NOT_FOUND);
            }
            Blog blog = new Blog();
            blog.setTitle(blogRequest.getTitle());
            blog.setContent(blogRequest.getContent());
            blog.setCreateBy(user.get().getFullName());
            blog.setCover(blogRequest.getCover());
            blog.setDeleted(false);
            blog.setUser(user.get());
            blogRepository.save(blog);
            return "Blog saved successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
