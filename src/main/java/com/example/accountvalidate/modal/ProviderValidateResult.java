package com.example.accountvalidate.modal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class ProviderValidateResult {
    private String provider;

    @JsonProperty(value="isValid")
    private boolean isValid;
}
