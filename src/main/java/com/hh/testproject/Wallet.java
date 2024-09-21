package com.hh.testproject;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name="wallet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long balance;
}
