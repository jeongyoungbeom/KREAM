package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Withdrawal;
import lombok.*;

import java.time.LocalDateTime;
@Getter
public class WithdrawalListApiResponse {
    private Long id;
    private String email;
    private String hp;
    private LocalDateTime regdate;

    public WithdrawalListApiResponse(Withdrawal withdrawal) {
        this.id = withdrawal.getId();
        this.email = withdrawal.getEmail();
        this.hp = withdrawal.getHp();
        this.regdate = withdrawal.getRegdate();
    }
}
