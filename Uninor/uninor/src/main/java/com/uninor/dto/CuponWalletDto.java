package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter @Setter @NoArgsConstructor
public class CuponWalletDto {

    @NotBlank(message = "Client Id cannot be null")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "Client Id should only contains digits and cannot be 0 or negative")
    private String clientId;

    @NotBlank(message = "Plan Id cannot be null")
    @Pattern(regexp = "^[1-9][0-9]*$", message = "Plan Id should only contains digits and cannot be 0 or negative")
    private String planId;

    @Pattern(regexp = "^([A-Z0-9]{6})?$", message = "Cupon Code must only consist of alphanumerical numbers")
    private String cuponCode;

    @Pattern(regexp = "^(\\d+(\\.\\d+)?)?$", message = "Wallet Amount must consist of decimals")
    private String enteredWalletAmount;
}
