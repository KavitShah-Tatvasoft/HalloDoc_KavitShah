package com.uninor.dto;

import com.microsoft.schemas.office.office.STInsetMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter @NoArgsConstructor
public class ClientWalletDto {
    @NotBlank(message = "Client Id cannot be null")
    String clientId;

    @NotBlank(message = "Amount cannot be null")
    @Pattern(regexp = "^\\d+\\.\\d+$", message = "Amount must contain only numbers")
    String amount;
}
