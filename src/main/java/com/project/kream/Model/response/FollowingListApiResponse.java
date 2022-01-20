package com.project.kream.Model.response;

import com.project.kream.Model.Entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FollowingListApiResponse {
    private Long id;
    private String profileName;
    private String name;
    private String originFileName;
    private boolean followingBoolean;

    public FollowingListApiResponse(Follow follow, boolean followingBoolean) {
        this.id = follow.getId();
        this.profileName = follow.getFollowing().getStyleCustomerList().get(0).getProfileName();
        this.name = follow.getFollowing().getStyleCustomerList().get(0).getName();
        this.originFileName = follow.getFollowing().getImage();
        this.followingBoolean = followingBoolean;
    }
}


