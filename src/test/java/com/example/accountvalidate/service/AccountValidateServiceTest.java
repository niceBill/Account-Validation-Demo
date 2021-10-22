package com.example.accountvalidate.service;

import com.example.accountvalidate.controller.AccountValidateController;
import com.example.accountvalidate.modal.AccountValidateRequest;
import com.example.accountvalidate.modal.AccountValidateResponse;
import com.example.accountvalidate.util.ServiceConstants;
import com.example.accountvalidate.util.ServiceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountValidateServiceTest {
    @InjectMocks
    private AccountValidateService validateService;

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_1_AND_Provider_2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider1", "provider2");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(2);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(true);
        assertThat(response.getResult().get(1).getProvider()).isEqualTo("provider2");
        assertThat(response.getResult().get(1).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_1() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider1");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(1);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(true);
    }

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider2");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(1);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider2");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_ZERO_Valid_Provider() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(2);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(true);
        assertThat(response.getResult().get(1).getProvider()).isEqualTo("provider2");
        assertThat(response.getResult().get(1).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_Invalid_Providers_AND_Provider1() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3", "provider1");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(1);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(true);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_Invalid_Providers_AND_Provider2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3", "provider2");
        request.setProviders(provides);

        AccountValidateResponse response = validateService.validate(request);
        assertThat(response.getResult().size()).isEqualTo(1);
        assertThat(response.getResult().get(0).getProvider()).isEqualTo("provider2");
        assertThat(response.getResult().get(0).isValid()).isEqualTo(false);
    }
}