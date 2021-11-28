package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;

@Getter
@RequiredArgsConstructor
public enum BoardCategory {
    공지("CATE_NOTI", "NOTICE"),
    이벤트("CATE_EVEN", "EVENT"),
    쇼룸("CATE_SHOW", "SHOW"),
    스니커즈("CATE_sneak", "sneakers"),
    의류("CATE_clothing", "clothing"),
    패션잡화("CATE_fashion", "fashion"),
    테크("CATE_tech", "tech"),
    라이프("CATE_life", "life"),
    이용정책("CATE_USE", "USE"),
    공통("CATE_COMMON", "COMMON"),
    구매("CATE_PURCHASE", "PURCHASE"),
    판매("CATE_SALE", "SALE");

    private final String key;
    private final String title;
}
