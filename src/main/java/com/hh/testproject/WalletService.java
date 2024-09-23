package com.hh.testproject;

import com.hh.testproject.dto.TransactionResponseDto;
import com.hh.testproject.dto.WalletDtoRequest;
import com.hh.testproject.dto.WalletDtoResponse;
import com.hh.testproject.dto.TransactionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

    TransactionResponseDto makeTransaction(TransactionRequestDto requestDto);

    WalletDtoResponse createWallet(WalletDtoRequest request);

    WalletDtoResponse getWalletBalance(Long walletId);
}
