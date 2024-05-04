package com.javatest.multiplejdbcclient.post;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final JdbcClient jdbcClient;


    public PostService(@Qualifier("blogJdbcClient") JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Post> getPosts() {
        return jdbcClient.sql("SELECT * FROM post")
                .query(Post.class)
                .list();
    }

    public Optional<Post> findPostById(String id) {
        return jdbcClient.sql("SELECT * FROM post WHERE id = :id")
                .param("id", id)
                .query(Post.class)
                .optional();
    }

    public void createPost(Post post) {
        jdbcClient.sql("INSERT INTO post (id, title, content, date, time_to_read, tags) " +
                       "VALUES (:id, :title, :content, :date, :timeToRead, :tags)")
                .params(post)
                .update();
    }

    public void updatePost(Post post) {
        jdbcClient.sql("UPDATE post SET title = :title, " +
                       "content = :content," +
                       " date = :date, " +
                       "time_to_read = :timeToRead," +
                       " tags = :tags " +
                       "WHERE id = :id")
                .params(post)
                .update();
    }

    public int deletePost(String id) {
        return jdbcClient.sql("DELETE FROM post WHERE id = :id")
                .param("id", id)
                .update();
    }
}
