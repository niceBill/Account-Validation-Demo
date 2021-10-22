package com.example.accountvalidate.feign;

import com.example.accountvalidate.modal.AccountValidateRequest;
import com.example.accountvalidate.modal.AccountValidateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ProviderService", url = "dummy.provider.baseUrl")
public interface AccountValidateFeignClient {
    /**
     * Illustrate Feign Client for calling backend Provider services
     */
    @GetMapping(value="dummy.provider.uri", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AccountValidateResponse> validateAccount(AccountValidateRequest request);
}
