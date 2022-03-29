package com.project.kream.Service;

import com.project.kream.Model.Entity.Follow;
import com.project.kream.Model.request.FollowApiRequest;
import com.project.kream.Model.response.FollowApiResponse;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final CustomerRepository customerRepository;
    private final FollowRepository followRepository;

    public boolean link(Long followingId, Long followerId){
        if(!linked(followingId, followerId)){
            FollowApiRequest followApiRequest = FollowApiRequest.builder()
                    .followerId(followerId)
                    .followingId(followingId)
                    .build();
            followRepository.save(followApiRequest.toEntity(customerRepository.getById(followerId), customerRepository.getById(followingId)));
        }else{
            followRepository.deleteByFollowingIdAndFollowerId(followingId, followerId);
        }
        return linked(followingId, followerId);
    }

    public boolean linked(Long followingId, Long followerId){
        if(!followRepository.existsByFollowingIdAndFollowerId(followingId, followerId)){
            return false;
        }
        return true;
    }
}
