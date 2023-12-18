package com.copoglu.copoglublog.service;

import com.copoglu.copoglublog.entities.Comment;
import com.copoglu.copoglublog.repository.CommentJpaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class CommentService {
    @Autowired
    private CommentJpaRepository commentJpaRepository;


    public List<Comment> findBypost_id(Long post_id) {
        return commentJpaRepository.findBypost_id(post_id);
    }

    public Comment addComment(Comment comment) {
        return commentJpaRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentJpaRepository.deleteById(id);
    }

    public List<Comment> GetAllComment() {
        return commentJpaRepository.findAll();
    }
}
