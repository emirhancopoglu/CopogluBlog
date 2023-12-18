package com.copoglu.copoglublog.controller;

import com.copoglu.copoglublog.entities.Comment;
import com.copoglu.copoglublog.entities.Post;
import com.copoglu.copoglublog.entities.User;
import com.copoglu.copoglublog.service.CommentService;
import com.copoglu.copoglublog.service.PostService;
import com.copoglu.copoglublog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("getAllComment")
    public List<Comment> GetAllComment () {
        return commentService.GetAllComment();
    }


    @GetMapping("comment/{post_id}")
    public List<Comment> findBypost_id(@PathVariable Long post_id){

        return commentService.findBypost_id(post_id);
    }

    @PostMapping("/addcomment")
    public Comment addComment(@RequestBody Comment comment){

        return commentService.addComment(comment);
    }
    @DeleteMapping("delete/comment/{id}")
    public void deleteById (@PathVariable Long id ) {
         commentService.deleteById(id);
    }


}
