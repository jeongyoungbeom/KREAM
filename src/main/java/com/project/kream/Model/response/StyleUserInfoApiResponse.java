package com.project.kream.Model.response;

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
}
