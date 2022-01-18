package com.project.kream.Model.request;

import com.project.kream.Model.Entity.Customer;
import com.project.kream.Model.Entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowApiRequest {
    private Long followingId;
    private Long followerId;

    public Follow toEntity(Customer customer, Customer customer2) {
        return Follow.builder()
                .follower(customer)
                .following(customer2)
                .build();
    }

}
