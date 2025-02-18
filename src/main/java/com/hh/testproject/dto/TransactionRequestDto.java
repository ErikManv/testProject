package com.hh.testproject.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

   @NotNull(message = "walletId field cannot be empty")
   public Long walletId;

   @NotNull(message = "operationType field cannot be empty")
   public String operationType;

   @NotNull(message = "amount field cannot be empty")
   @Positive(message = "deposit amount have to be bigger than 0")
   public int amount;
}
