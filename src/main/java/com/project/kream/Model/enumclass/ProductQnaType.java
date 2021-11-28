package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductQnaType {
    일반문의("TYPE_COMMON", "COMMON"),
    판매문의("TYPE_SALE", "SALE"),
    구매문의("TYPE_PURCHASE", "PURCHASE");

    private final String key;
    private final String title;
}
