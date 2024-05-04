package com.javatest.multiplejdbcclient;

import com.javatest.multiplejdbcclient.post.PostService;
import com.javatest.multiplejdbcclient.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

@SpringBootApplication
public class MultipleJdbcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleJdbcClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PostService postService, SubscriberService subscriberService) {
        return args -> {
            var post = postService.getPosts();
            System.out.println(post);
            var subscriber = subscriberService.findALlSubscribers();
            System.out.println(subscriber);
        };
    }

    @Bean
    JdbcClient blogJdbcClient(@Qualifier("blogDataSource") DataSource blogDataSource) {
        return JdbcClient.create(blogDataSource);
    }

    @Bean
    JdbcClient subscriberJdbcClient(@Qualifier("subscriberDataSource") DataSource subscriberDataSource) {
        return JdbcClient.create(subscriberDataSource);
    }

    @Bean
    CommandLineRunner dsCommandRunner(@Qualifier("blogDataSource") DataSource blogJdbcClient,
                                      @Qualifier("subscriberDataSource") DataSource subscriberJdbcClient) {
        return args -> {
            System.out.println(blogJdbcClient.getConnection().getMetaData().getURL());
            System.out.println(subscriberJdbcClient.getConnection().getMetaData().getURL());
        };
    }
}
