package com.example.accountvalidate.modal;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class AccountValidateResponse extends BaseAccountAPIResponse {
    private static final long serialVersionUID = 3348535699312771431L;

    private List<ProviderValidateResult> result;
}
