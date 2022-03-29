package com.project.kream.Controller.RestController;

import com.project.kream.Service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FollowRestController {
    private final FollowService followService;

    @GetMapping("/api/follow_link/{followingId}/{followerId}")
    public boolean link(@PathVariable Long followingId, @PathVariable Long followerId) {
        return followService.link(followingId, followerId);
    }

    @GetMapping("/api/follow_linked/{followingId}/{followerId}")
    public boolean linked(@PathVariable Long followingId, @PathVariable Long followerId) {
        return followService.linked(followingId, followerId);
    }
}