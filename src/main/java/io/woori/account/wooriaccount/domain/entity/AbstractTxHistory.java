package io.woori.account.wooriaccount.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractTxHistory extends BaseTimeEntity {

    @Id
    @Column(name = "tx_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long txId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal balanceAfterTx;

    private String description;

    public AbstractTxHistory(Account sender, Account receiver, BigDecimal amount, BigDecimal balanceAfterTx, String description) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.balanceAfterTx = balanceAfterTx;
        this.description = description;
    }

}
