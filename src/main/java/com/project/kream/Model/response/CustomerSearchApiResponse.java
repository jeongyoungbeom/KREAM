package com.project.kream.Model.response;

import com.project.kream.Model.enumclass.CustomerRank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerSearchApiResponse {
    private Long id;
    private String email;
    private String userid;
    private CustomerRank rank;
    private LocalDateTime regdate;
    private String message;
}
