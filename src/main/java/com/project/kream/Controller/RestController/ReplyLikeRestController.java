package com.project.kream.Controller.RestController;

import com.project.kream.Service.ReplyLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyLikeRestController {
    private final ReplyLikeService replyLikeService;

    @GetMapping("/api/replyLike_like/{customerId}/{replyId}")
    private Long like(@PathVariable Long customerId, @PathVariable Long replyId){
        return replyLikeService.like(customerId, replyId);
    }

    @GetMapping("/api/replyLike_liked/{customerId}/{replyId}")
    private boolean liked(@PathVariable Long customerId, @PathVariable Long replyId){
        return replyLikeService.liked(customerId, replyId);
    }
}
