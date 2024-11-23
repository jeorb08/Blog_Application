package com.blogapplication.EchoWrite.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;
    private String image;
    private Date date;
    private int likecount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}