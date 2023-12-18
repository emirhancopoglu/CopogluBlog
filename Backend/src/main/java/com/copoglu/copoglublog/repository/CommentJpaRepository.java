package com.copoglu.copoglublog.repository;

import com.copoglu.copoglublog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentJpaRepository extends JpaRepository<Comment,Long> {
    List<Comment> findBypost_id(Long postId);
}
