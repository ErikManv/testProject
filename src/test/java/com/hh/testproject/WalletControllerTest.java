package com.hh.testproject;

import com.hh.testproject.dto.TransactionRequestDto;
import com.hh.testproject.dto.TransactionResponseDto;
import com.hh.testproject.dto.WalletDtoResponse;
import com.hh.testproject.exceptions.NotFoundException;
import com.hh.testproject.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    @Test
    void getWallet_Test() {
        Wallet wallet = Wallet.builder()
            .id(1L)
            .balance(1000L)
            .build();

        WalletDtoResponse walletDtoResponse = WalletDtoResponse.builder()
            .balance(wallet.getBalance())
            .build();

        Mockito.doReturn(walletDtoResponse).when(this.walletService).getWalletBalance(wallet.getId());

        ResponseEntity<WalletDtoResponse> responseEntity = this.walletController.getWallet(wallet.getId());

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void getWallet_walletNotFound_Test() {

        when(this.walletService.getWalletBalance(anyLong())).thenThrow(new NotFoundException("wallet not found"));

        assertThrowsExactly(NotFoundException.class, () -> this.walletController.getWallet(2L));

    }

    @Test
    void makeTransaction_Test() {
        Wallet wallet = Wallet.builder()
            .id(1L)
            .balance(1000L)
            .build();

        TransactionRequestDto requestDto = TransactionRequestDto.builder()
            .walletId(wallet.getId())
            .operationType("DEPOSIT")
            .amount(5000)
            .build();

        TransactionResponseDto responseDto = TransactionResponseDto.builder()
            .balance(wallet.getBalance() + requestDto.getAmount())
            .operationType(OperationType.valueOf(requestDto.getOperationType()))
            .amount(requestDto.getAmount())
            .build();

        doReturn(responseDto).when(walletService).makeTransaction(requestDto);

        ResponseEntity<TransactionResponseDto> responseEntity = this.walletController.makeTransaction(requestDto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void makeTransaction_wrongOperationType_Test() {
        Wallet wallet = Wallet.builder()
            .id(1L)
            .balance(1000L)
            .build();

        TransactionRequestDto requestDto = TransactionRequestDto.builder()
            .walletId(wallet.getId())
            .operationType("WTHDR")
            .amount(5000)
            .build();

        when(walletService.makeTransaction(requestDto)).thenThrow(new IllegalArgumentException("wrong operation type"));

        assertThrowsExactly(IllegalArgumentException.class, () -> this.walletController.makeTransaction(requestDto));

    }

    @Test
    void makeTransaction_wrongAmount_Test() {
        Wallet wallet = Wallet.builder()
            .id(1L)
            .balance(1000L)
            .build();

        TransactionRequestDto requestDto = TransactionRequestDto.builder()
            .walletId(wallet.getId())
            .operationType("WITHDRAW")
            .amount(-500)
            .build();

        when(walletService.makeTransaction(requestDto)).thenThrow(new ValidationException("wrong amount"));

        assertThrowsExactly(ValidationException.class, () -> this.walletController.makeTransaction(requestDto));

    }

    @Test
    void makeTransaction_wrongWalletId_Test() {
        TransactionRequestDto requestDto = TransactionRequestDto.builder()
            .walletId(0L)
            .operationType("DEPOSIT")
            .amount(1000)
            .build();

        when(walletService.makeTransaction(requestDto)).thenThrow(new NotFoundException("wrong wallet id"));

        assertThrowsExactly(NotFoundException.class, () -> this.walletController.makeTransaction(requestDto));
    }
}