package com.javatest.multiplejdbcclient.subscriber;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    private final JdbcClient dataSource;

    public SubscriberService(@Qualifier("subscriberJdbcClient") JdbcClient dataSource) {
        this.dataSource = dataSource;
    }

    public List<Subscriber> findALlSubscribers() {
        return dataSource.sql("SELECT * FROM subscriber")
                .query(Subscriber.class)
                .list();
    }
}
