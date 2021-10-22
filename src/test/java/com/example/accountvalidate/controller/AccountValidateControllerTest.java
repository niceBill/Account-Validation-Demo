package com.example.accountvalidate.controller;

import com.example.accountvalidate.modal.AccountValidateRequest;
import com.example.accountvalidate.modal.AccountValidateResponse;
import com.example.accountvalidate.service.AccountValidateService;
import com.example.accountvalidate.util.ServiceConstants;
import com.example.accountvalidate.util.ServiceUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AccountValidateControllerTest {
    @Mock
    private AccountValidateService validateService;

    @InjectMocks
    private AccountValidateController validateController;

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_1_AND_Provider_2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider1", "provider2");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(2);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(true);
        assertThat(actualResponse.getResult().get(1).getProvider()).isEqualTo("provider2");
        assertThat(actualResponse.getResult().get(1).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_1() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider1");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_provider1_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(1);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(true);
    }

    @Test
    public void testValidate_SUCCESSFUL_from_Provider_2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("provider2");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_provider2_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(1);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider2");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_ZERO_Valid_Provider() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(2);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(true);
        assertThat(actualResponse.getResult().get(1).getProvider()).isEqualTo("provider2");
        assertThat(actualResponse.getResult().get(1).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_Invalid_Providers_AND_Provider1() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3", "provider1");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_provider1_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(1);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider1");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(true);
    }

    @Test
    public void testValidate_SUCCESSFUL_with_Invalid_Providers_AND_Provider2() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("1234567890");
        List<String> provides = Arrays.asList("invalidProvider1", "invalidProvider2", "invalidProvider3", "provider2");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = ServiceUtil.jsonToJavaObject("account_provider2_validation_result.json", AccountValidateResponse.class);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResult().size()).isEqualTo(1);
        assertThat(actualResponse.getResult().get(0).getProvider()).isEqualTo("provider2");
        assertThat(actualResponse.getResult().get(0).isValid()).isEqualTo(false);
    }

    @Test
    public void testValidate_Empty_Account_Number() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("");
        List<String> provides = Arrays.asList("provider1", "provider2");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = new AccountValidateResponse();
        mockResponse.setResponseCode(ServiceConstants.INVALID_INPUT);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResponseCode()).isEqualTo(ServiceConstants.INVALID_INPUT);
    }

    @Test
    public void testValidate_Invalid_Account_Number() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("ABC");
        List<String> provides = Arrays.asList("provider1", "provider2");
        request.setProviders(provides);

        AccountValidateResponse mockResponse = new AccountValidateResponse();
        mockResponse.setResponseCode(ServiceConstants.INVALID_INPUT);
        Mockito.when(validateService.validate(request)).thenReturn(mockResponse);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResponseCode()).isEqualTo(ServiceConstants.INVALID_INPUT);
    }

    @Test
    public void testValidate_EXCEPTION() throws Exception {
        AccountValidateRequest request = new AccountValidateRequest();
        request.setAccountNumber("ABC");
        List<String> provides = Arrays.asList("provider1", "provider2");
        request.setProviders(provides);

        Mockito.when(validateService.validate(request)).thenThrow(Exception.class);

        AccountValidateResponse actualResponse = validateController.validate(request);
        assertThat(actualResponse.getResponseCode()).isEqualTo(ServiceConstants.FAILURE);
    }

}