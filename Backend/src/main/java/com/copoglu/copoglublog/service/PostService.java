package com.copoglu.copoglublog.service;

import com.copoglu.copoglublog.entities.Post;
import com.copoglu.copoglublog.repository.PostJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PostService {

    @Autowired
    private PostJpaRepository postJpaRepository;

    public List<Post> GetAllPost() {
        return postJpaRepository.findAll();
    }

    public Post addNewPost(Post post) {
        return postJpaRepository.save(post);
    }

    public void deleteById(Long id) {
        postJpaRepository.deleteById(id);
    }

    public List<Post> findAllByOrderByCreationTimeDesc() {
        return postJpaRepository.findAllByOrderByCreationTimeDesc();
    }
}
