package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    게시대기("waiting", "waiting"),
    게시중("posting","posting"),
    게시종료("closed","closed"),
    게시중지("stop","stop");

    private final String status;
    private final String title;

}
