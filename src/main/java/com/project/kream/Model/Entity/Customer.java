package com.project.kream.Model.Entity;

import com.project.kream.Model.enumclass.CustomerRank;
import com.project.kream.Model.enumclass.CustomerRole;
import com.project.kream.Model.enumclass.CustomerType;
import lombok.*;
import javax.persistence.*;
import java.util.List;

// 특정 필드에 어노테이션을 달아주면, 자동으로 접근자와 설정자를 생성해줍니다.
// 클래스에 어노테이션을 달아주면, 모든 필드에 접근자와 설정자를 생성해줍니다.
@Getter
@Setter
// 파라미터가 없는 생성자를 생성해줍니다.
@NoArgsConstructor
// 모든 필드 값을 파라미터로 받는 생성자를 생성해줍니다.
@AllArgsConstructor
@SequenceGenerator(
        name="seq_customer",
        sequenceName = "seq_customer",
        initialValue = 1,
        allocationSize = 1
)
// JPA를 사용해 테이블과 매핑할 클래스에 사용합니다. 이 어노테이션을 사용함으로써 JPA가 해당 클래스를 관리하게 됩니다.
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

    // java의 enum 형태로 되어 미리 정의되어 있는 코드 값이나 구분값을 데이터 타입으로 사용하고자 할때 사용됩니다.
    // ORDINAL : enum 객체에 정의된 순서가 컬럼의 값으로 사용됩니다.
    // STRING : enum의 문자열 자체가 컬럼의 값으로 사용됩니다.

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

    // 해당 클래스의 빌더 패턴 클래스를 생성해줍니다.
    @Builder
    public Customer(String email, String userid, String userpw, String hp, String shoesize, String agreement, String privacyPolicy, String smsAllow, String emailAllow, String image, Long point, String message, CustomerRank rank, CustomerType type, CustomerRole role) {
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
        this.rank = rank;
        this.type = type;
        this.role = role;
    }

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

