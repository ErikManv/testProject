package com.hh.testproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDtoResponse {

    @NotNull(message = "initBalance field cannot be empty")
    @Positive(message = "deposit amount have to be bigger than 0")
    public Long balance;
}
