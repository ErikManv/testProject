package com.hh.testproject;

import com.hh.testproject.dto.TransactionResponseDto;
import com.hh.testproject.dto.WalletDtoRequest;
import com.hh.testproject.dto.WalletDtoResponse;
import com.hh.testproject.exceptions.NotFoundException;
import com.hh.testproject.dto.TransactionRequestDto;
import com.hh.testproject.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public WalletDtoResponse createWallet(WalletDtoRequest walletDto) {
        Wallet newWallet = new Wallet();
        newWallet.setBalance(walletDto.initBalance);

        walletRepository.save(newWallet);
        return WalletDtoResponse.builder()
            .balance(newWallet.getBalance())
            .build();
    }

    @Override
    public WalletDtoResponse getWalletBalance(Long walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new NotFoundException("wallet not found"));

        return WalletDtoResponse.builder()
            .balance(wallet.getBalance())
            .build();
    }

    @Override
    public TransactionResponseDto makeTransaction(TransactionRequestDto requestDto) {
        Wallet wallet = walletRepository.findById(requestDto.walletId).orElseThrow(() -> new NotFoundException("wallet not found"));
        if(wallet.getBalance() < requestDto.amount) {
            throw new ValidationException("insufficient balance. Change amount");
        }
        if (OperationType.valueOf(requestDto.operationType) == OperationType.DEPOSIT) {
            wallet.setBalance(wallet.getBalance() + requestDto.amount);
        } else if (OperationType.valueOf(requestDto.operationType) == OperationType.WITHDRAW) {
            wallet.setBalance(wallet.getBalance() - requestDto.amount);
        }
        walletRepository.save(wallet);
        return TransactionResponseDto.builder()
            .balance(wallet.getBalance())
            .operationType(OperationType.valueOf(requestDto.operationType))
            .amount(requestDto.amount)
            .build();
    }
}
