package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(
        name="seq_customer",
        sequenceName = "seq_customer",
        initialValue = 1,
        allocationSize = 1
)
@Entity
@DynamicUpdate
public class Customer extends DateEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    private Long id;
    private String email;
    private String userid;
    private String userpw;
    private String hp;
    private String shoesize;
    private String agreement;
    private String privacyPolicy;
    private String smsAllow;
    private String emailAllow;
    private String image;
    private Long point;
    private String message;
    @Enumerated(EnumType.STRING)
    private CustomerRank rank;
    @Enumerated(EnumType.STRING)
    private CustomerType type;
    @Enumerated(EnumType.STRING)
    private CustomerRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> cartList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CardInfo> cardInfoList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Sales> salesList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Style> styleList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerQna> customerQnaList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProductQna> productQnaList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followingList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followerList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<StyleLike> styleLikeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ReplyLike> replyLikeList;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private StyleCustomer styleCustomer;

}

