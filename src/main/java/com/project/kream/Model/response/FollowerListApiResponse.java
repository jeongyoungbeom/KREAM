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
public class FollowerListApiResponse {
    private Long id;
    private String profileName;
    private String name;
    private String originFileName;
    private boolean followerBoolean;

    public FollowerListApiResponse(Follow follow, boolean followerBoolean) {
        this.id = follow.getId();
        this.profileName = follow.getFollower().getStyleCustomerList().get(0).getProfileName();
        this.name = follow.getFollower().getStyleCustomerList().get(0).getName();
        this.originFileName = follow.getFollower().getImage();
        this.followerBoolean = followerBoolean;
    }
}

