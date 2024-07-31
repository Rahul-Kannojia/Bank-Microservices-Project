package com.app.accounts.entities;

import com.app.accounts.enums.AccountsType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

    /**
     * For Account Number we are not using GeneratedValue because AccountNumber can
     * be any number, which can't be in Sequence
     */
    @Id
    @Column(name = "account_number", nullable = false)
    private Long accountNumber;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountsType accountType;
    @Column(name = "branch_address", nullable = false)
    private String branchAddress;
}
