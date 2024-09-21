package com.hh.testproject;

import com.hh.testproject.dto.WalletDtoRequest;
import com.hh.testproject.dto.WalletDtoResponse;
import com.hh.testproject.dto.TransactionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {

    void makeTransaction(TransactionRequestDto requestDto);


    Wallet createWallet(WalletDtoRequest request);

    WalletDtoResponse getWalletBalance(Long walletId);
}
