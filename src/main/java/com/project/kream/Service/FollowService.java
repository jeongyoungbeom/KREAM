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
public class FollowService extends BaseService<FollowApiRequest, FollowApiResponse, Follow> {
    private final CustomerRepository customerRepository;
    private final FollowRepository followRepository;

    public boolean link(Long followingId, Long followerId){
        if(!linked(followingId, followerId)){
            Follow follow = Follow.builder()
                    .follower(customerRepository.getById(followerId))
                    .following(customerRepository.getById(followingId))
                    .build();
            baseRepository.save(follow);
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
