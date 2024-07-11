package com.uninor.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter @Setter @NoArgsConstructor
public class ClientCuponDto {

    @NotBlank(message = "Client ID cannot be null")
    private String clientId;

    @Pattern(regexp = "^[A-Z0-9]{6}$", message = "Cupon Code must be 6 char long and consist of letters and numbers only")
    private String cuponCode;

}
