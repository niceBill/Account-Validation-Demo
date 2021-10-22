package com.example.accountvalidate.service;

import com.example.accountvalidate.modal.AccountValidateRequest;
import com.example.accountvalidate.modal.AccountValidateResponse;
import com.example.accountvalidate.util.ServiceConstants;
import com.example.accountvalidate.util.ServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AccountValidateService {
    AccountValidateResponse response = null;

    @Value("${providers.url.provider1}")
    private String provider1_baseUrl;

    @Value("${providers.url.provider2}")
    private String provider2_baseUrl;

    public AccountValidateResponse validate(AccountValidateRequest request) throws Exception {
        /**
         * Illustrating retrieving Provider urls from properties rather than stored in the code
         */
        String provider1_url = provider1_baseUrl + ServiceConstants.PROVIDER1_URI;
        String provider2_url = provider2_baseUrl + ServiceConstants.PROVIDER2_URI;

        /**
         * Since backend provider services are not existing, for demonstration purposes this validate method simply returns hard-coded consolidated results back.
         * But in a real situation, this method needs to be re-factored to loop through the provider list and make asynchronous calls
         * to each Provider service than consolidate the results (by using @Async & CompletableFuture.supplyAsync()), especially when there are more than 2 providers.
         */
        AccountValidateResponse response = new AccountValidateResponse();
        List<String> providerList = request.getProviders();
        List<String> validProviderList = null;
        if (!CollectionUtils.isEmpty(providerList)) {
            validProviderList = providerList
                    .stream().filter(
                        item -> item.equalsIgnoreCase(ServiceConstants.PROVIDER1_NAME)
                            || item.equalsIgnoreCase(ServiceConstants.PROVIDER2_NAME
                    )
            ).collect(Collectors.toList());
        }
        if (CollectionUtils.isEmpty(validProviderList) || validProviderList.size() == 2) {
            response = ServiceUtil.jsonToJavaObject("account_validation_result.json", AccountValidateResponse.class);
        } else {
            if (validProviderList.get(0).equalsIgnoreCase(ServiceConstants.PROVIDER1_NAME)) {
                response = ServiceUtil.jsonToJavaObject("account_provider1_validation_result.json", AccountValidateResponse.class);
            } else if (validProviderList.get(0).equalsIgnoreCase(ServiceConstants.PROVIDER2_NAME)) {
                response = ServiceUtil.jsonToJavaObject("account_provider2_validation_result.json", AccountValidateResponse.class);
            }
        }

        log.info("Successfully validate the account");
        return response;
    }
}
