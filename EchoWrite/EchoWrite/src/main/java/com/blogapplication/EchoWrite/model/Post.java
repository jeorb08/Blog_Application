package com.blogapplication.EchoWrite.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postTitle;
    private String postDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
