package com.javatest.multiplejdbcclient.post;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final JdbcClient jdbcClient;

    public PostService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> getPosts() {
        return jdbcClient.sql("SELECT * FROM post")
                .query(Post.class)
                .list();
    }
}
