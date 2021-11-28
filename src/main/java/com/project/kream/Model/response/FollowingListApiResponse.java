package com.project.kream.Model.response;

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
}
