package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SequenceGenerator(
        name="seq_customer",
        sequenceName = "seq_customer",
        initialValue = 1,
        allocationSize = 1
)
@Entity
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> cartList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CardInfo> cardInfoList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Purchase> purchaseList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Sales> salesList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Style> styleList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerQna> customerQnaList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ProductQna> productQnaList;

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followingList;

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followerList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<StyleLike> styleLikeList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<ReplyLike> replyLikeList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accountList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<StyleCustomer> styleCustomerList;

    public void update(String email, String userid, String userpw, String hp, String shoesize, String agreement, String privacyPolicy, String smsAllow, String emailAllow, String image, Long point, String message) {
        this.email = email;
        this.userid = userid;
        this.userpw = userpw;
        this.hp = hp;
        this.shoesize = shoesize;
        this.agreement = agreement;
        this.privacyPolicy = privacyPolicy;
        this.smsAllow = smsAllow;
        this.emailAllow = emailAllow;
        this.image = image;
        this.point = point;
        this.message = message;

    }


}

