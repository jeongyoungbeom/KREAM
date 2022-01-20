package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleUserInfoApiResponse {
    private String userid;
    private String name;
    private String intro;
    private String userImg;
    private Long boardCnt;
    private List<FollowingListApiResponse> followingListApiResponseList;
    private Long followerCnt;
    private List<FollowerListApiResponse> followerListApiResponseList;
    private Long followingCnt;
    private boolean followBoolean;
    private List<StyleUserListApiResponse> styleUserListApiResponseList;

    public StyleUserInfoApiResponse(Customer customer, String intro, Long boardCnt,List<FollowingListApiResponse> followingListApiResponseList, Long followerCnt, List<FollowerListApiResponse> followerListApiResponseList,Long followingCnt, boolean followBoolean, List<StyleUserListApiResponse> styleUserListApiResponseList) {
        this.userid = customer.getStyleCustomerList().get(0).getProfileName();
        this.name = customer.getStyleCustomerList().get(0).getName();
        this.intro = intro;
        this.userImg = customer.getImage();
        this.boardCnt = boardCnt;
        this.followingListApiResponseList = followingListApiResponseList;
        this.followerCnt = followerCnt;
        this.followerListApiResponseList = followerListApiResponseList;
        this.followingCnt = followingCnt;
        this.followBoolean = followBoolean;
        this.styleUserListApiResponseList = styleUserListApiResponseList;
    }
}

