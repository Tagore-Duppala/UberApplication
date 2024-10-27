package com.codingshuttle.project.uber.uberApplication.advices;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private Date createdTimestamp;
}
