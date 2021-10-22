package com.example.accountvalidate.controller;

import com.example.accountvalidate.modal.AccountValidateRequest;
import com.example.accountvalidate.modal.AccountValidateResponse;
import com.example.accountvalidate.service.AccountValidateService;
import com.example.accountvalidate.util.ServiceConstants;
import com.example.accountvalidate.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/v1/account")
@Slf4j
public class AccountValidateController {
    @Autowired
    private AccountValidateService accountValidateService;

    @PostMapping(value = ServiceConstants.VALIDATE_URI)
    public AccountValidateResponse validate(@RequestBody @Valid AccountValidateRequest request) throws Exception {
        Instant startTime = Instant.now();
        AccountValidateResponse response;
        try {
            response = accountValidateService.validate(request);
            ServiceUtil.prepareResponse(startTime, ServiceConstants.ACCOUNT_VALIDATE_SERVICE, response);
        } catch (Exception ex) {
            log.error("Problem happened when calling backend Validate service", ex);
            response = new AccountValidateResponse();
            ServiceUtil.buildErrorResponse(response, startTime, ServiceConstants.ACCOUNT_VALIDATE_SERVICE, ex);
        }

        return response;
    }
}
