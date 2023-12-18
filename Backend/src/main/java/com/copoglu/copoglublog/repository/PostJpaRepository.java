package com.copoglu.copoglublog.repository;

import com.copoglu.copoglublog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreationTimeDesc();
}
