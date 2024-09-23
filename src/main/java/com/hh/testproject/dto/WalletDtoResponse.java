package com.hh.testproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletDtoResponse {

    @NotNull(message = "balance field cannot be empty")
    @PositiveOrZero(message = "balance amount have to be positive or 0")
    public Long balance;
}
