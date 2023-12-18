package com.copoglu.copoglublog.controller;

import com.copoglu.copoglublog.configuration.JwtTokenProvider;
import com.copoglu.copoglublog.entities.Post;
import com.copoglu.copoglublog.entities.User;

import com.copoglu.copoglublog.request.PostRequest;
import com.copoglu.copoglublog.service.PostService;
import com.copoglu.copoglublog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @GetMapping("AllPost")
    public List<Post> findAllByOrderByCreationTimeDesc () {
        return postService.findAllByOrderByCreationTimeDesc();
    }

    @PostMapping("add")
    public Post addNewPost(@RequestBody PostRequest postRequest, @RequestHeader("Authorization") String token) {
        String username = jwtTokenProvider.getUsernameFromToken(token.substring(7)); // "Bearer " sonrasını al
        Long userId = jwtTokenProvider.getUserIdFromToken(token.substring(7));
        // Kullanıcının kimliğini belirle

        User user = userService.findByUsername(username);
        if (user == null) {
            // Kullanıcı bulunamadı, hata mesajı ver ve işlemi sonlandır.

            throw new RuntimeException("Kullanıcı bulunamadı: " + username);
        }

        // Post bilgilerini set et ve kaydet
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        post.setUser(user);
        post.setuser_id(user.getId());

        return postService.addNewPost(post);

    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {

       postService.deleteById(id);
    }






}
