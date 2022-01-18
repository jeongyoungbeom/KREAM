package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Withdrawal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WithdrawalApiRequest {
    private Long id;
    private String email;
    private String hp;
    private LocalDateTime regdate;
    private String startDatetime;
    private String endDatetime;

    public Withdrawal toEntity(){
        return Withdrawal.builder()
                .email(email)
                .hp(hp)
                .build();
    }
}
