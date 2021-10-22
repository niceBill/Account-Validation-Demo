package com.example.accountvalidate.modal;

import com.example.accountvalidate.util.ServiceConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class AccountValidateRequest {
    @NotEmpty(message = "Account number can not be empty")
    @Pattern(regexp = ServiceConstants.NUMERIC_REGEX, message = "Please enter a valid Account Number")
    private String accountNumber;
    private List<String> providers;
}
