package com.javatest.multiplejdbcclient.post;

import java.time.LocalDate;

public record Post(
        String id,
        String title,
        String content,
        LocalDate date,
        int timeToRead,
        String tags
) {
}
