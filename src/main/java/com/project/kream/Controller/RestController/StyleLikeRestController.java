package com.project.kream.Controller.RestController;

import com.project.kream.Service.StyleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StyleLikeRestController {
    private final StyleLikeService styleLikeService;

    @GetMapping("/api/styleLike_like/{customerId}/{styleId}")
    public Long like(@PathVariable Long customerId, @PathVariable Long styleId) {
        return styleLikeService.like(customerId, styleId);
    }

    @GetMapping("/api/styleLike_liked/{customerId}/{styleId}")
    public boolean liked(@PathVariable Long customerId, @PathVariable Long styleId){
        return styleLikeService.liked(customerId, styleId);
    }
}
