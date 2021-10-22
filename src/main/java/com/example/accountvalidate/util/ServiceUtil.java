package com.example.accountvalidate.util;

import com.example.accountvalidate.modal.BaseAccountAPIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.Provider;
import java.time.Instant;

public class ServiceUtil {
    public static<T> T jsonToJavaObject(String jsonFileName, Class<T> clazz) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(ServiceUtil.class.getClassLoader().getResourceAsStream(jsonFileName), clazz);
    }

    public static void prepareResponse(Instant startTime, String serviceName, BaseAccountAPIResponse baseResponse) {
        long reqDateTime = startTime.toEpochMilli();
        long resDateTime = Instant.now().toEpochMilli();
        baseResponse.setRequestTime(reqDateTime);
        baseResponse.setResponseTime(resDateTime);
        baseResponse.setTimeTaken(resDateTime - reqDateTime);
        baseResponse.setServiceName(serviceName);
        baseResponse.setResponseMessage(ServiceConstants.SUCCESS_MESSAGE);
    }

    public static void buildErrorResponse(BaseAccountAPIResponse baseResponse, Instant startTime, String serviceName, Exception ex) {
        prepareResponse(startTime, serviceName, baseResponse);
        baseResponse.setResponseCode(ServiceConstants.FAILURE);
        baseResponse.setResponseMessage(ex.getMessage());
    }
}
