package com.example.accountvalidate.modal;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class BaseAccountAPIResponse<T> implements Serializable {
    private static final long serialVersionUID = 295099575191910764L;

    private String serviceName;
    private long requestTime;
    private long responseTime;
    private long timeTaken;
    private int responseCode;
    private String responseMessage;
}
