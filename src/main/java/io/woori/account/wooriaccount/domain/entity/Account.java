package io.woori.account.wooriaccount.domain.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/*
* 계좌와 관련된 엔티티 클래스 입니다.
* @author : yeom hwiju
* */
@Getter
@Table(name = "accounts")
@Entity(name = "accounts")
@DynamicInsert
@DynamicUpdate
@Where(clause = "IS_DELETED = false")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId; // 계좌 id

    private String accountNumber; // 계좌 번호

    private BigDecimal accountBalance; // 계좌 잔고

    private BigDecimal accountLimit; // 계좌 한도

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; // 계좌 주인

    @OneToMany(mappedBy = "sender")
    private List<AbstractTxHistory> withdrawTxHistories = new ArrayList<>(); // 출금 기록

    @OneToMany(mappedBy = "receiver")
    private List<AbstractTxHistory> depositTxHistories = new ArrayList<>(); // 입금 기록

    public Account(String accountNumber, BigDecimal accountBalance, BigDecimal accountLimit, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountLimit = accountLimit;
        this.customer = customer;
    }

}
