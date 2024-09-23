package com.hh.testproject.dto;

import com.hh.testproject.OperationType;
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
public class TransactionResponseDto {

   @NotNull(message = "balance field cannot be empty")
   @Positive(message = "balance amount have to be bigger than 0")
   public Long balance;

   @NotNull(message = "operationType field cannot be empty")
   public OperationType operationType;

   @NotNull(message = "amount field cannot be empty")
   @Positive(message = "deposit amount have to be bigger than 0")
   public int amount;
}
