package com.forum.entities;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String post_title;

    private String post_content;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "post_title")
    public String getPostTitle() {
        return post_title;
    }

    public void setPostTitle(String post_title) {
        this.post_title = post_title;
    }

    @Column(name = "post_content")
    public String getPostContent() {
        return post_content;
    }

    public void setPostContent(String post_content) {
        this.post_content = post_content;
    }
}