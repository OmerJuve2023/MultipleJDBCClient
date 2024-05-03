package com.javatest.multiplejdbcclient.subscriber;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class SubscriberService {

    private final JdbcClient dataSource;

    public SubscriberService(JdbcClient dataSource) {
        this.dataSource = dataSource;
    }

    public List<Subscriber> findALlSubscribers() {
        return dataSource.sql("SELECT * FROM subscriber")
                .query(Subscriber.class)
                .list();
    }
}
