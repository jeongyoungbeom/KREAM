package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.StyleReply;

import java.util.List;

public interface StyleReplyCustom {

    Long groupNumMin(Long pGroupId, Long pGroupNum, Long pDepth);

    Long groupNumMax(Long pGroupId);

    void updateGroupNum(Long pGroupId, Long pGroupNum);

    List<StyleReply> ReplyList(Long styleId);
}
