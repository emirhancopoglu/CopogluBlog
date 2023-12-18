package com.copoglu.copoglublog.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    private String title;
    private String text;
    private String post_url;
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @PrePersist
    protected void onCreate() {
        creationTime = LocalDateTime.now();
    }

    @PostPersist
    private void generatePostUrl(){
        if(post_url == null) {
            post_url = String.valueOf(id);
        }
    }


    public void setuser_id(Long id) {

    }

}
