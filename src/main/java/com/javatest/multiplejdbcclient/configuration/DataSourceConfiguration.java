package com.javatest.multiplejdbcclient.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.List;

@Configuration(proxyBeanMethods = true)
public class DataSourceConfiguration {
    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.blog")
    public DataSourceProperties blogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public HikariDataSource blogDataSource(DataSourceProperties blogDataSourceProperties) {
        return blogDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean
    DataSourceScriptDatabaseInitializer blogDataSourceInitializer(@Qualifier("blogDataSource") DataSource blogDataSource) {
        var setting = new DatabaseInitializationSettings();
        setting.setSchemaLocations(List.of("classpath:blog-schema.sql"));
        setting.setMode(DatabaseInitializationMode.EMBEDDED);
        return new DataSourceScriptDatabaseInitializer(blogDataSource, setting);
    }


}
