package com.hh.testproject;

import com.hh.testproject.dto.TransactionResponseDto;
import com.hh.testproject.dto.WalletDtoRequest;
import com.hh.testproject.dto.WalletDtoResponse;
import com.hh.testproject.dto.TransactionRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("api/v1/wallet/create")
    public ResponseEntity<Void> createWallet(@Valid @RequestBody WalletDtoRequest wallet) {
        walletService.createWallet(wallet);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("api/v1/wallet")
    public ResponseEntity<TransactionResponseDto> makeTransaction(@Valid @RequestBody TransactionRequestDto requestDto) {
        return new ResponseEntity<>(walletService.makeTransaction(requestDto), HttpStatus.OK);
    }

    @GetMapping("api/v1/wallets/{walletId}")
    public ResponseEntity<WalletDtoResponse> getWallet(@PathVariable Long walletId) {
        return new ResponseEntity<>(walletService.getWalletBalance(walletId), HttpStatus.OK);
    }
}
