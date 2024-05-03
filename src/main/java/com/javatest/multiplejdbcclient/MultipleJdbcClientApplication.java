package com.javatest.multiplejdbcclient;

import com.javatest.multiplejdbcclient.post.PostService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MultipleJdbcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleJdbcClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PostService postService) {
        return args -> {
            var post = postService.findPostById("2");
            System.out.println(post);
        };
    }

  /*  @Bean
    CommandLineRunner dscommandLineRunner(@Qualifier("blogDataSource") DataSource dataSource) {
        return args -> {
            System.out.println(dataSource.getConnection().getMetaData().getURL());
        };
    }*/


}
