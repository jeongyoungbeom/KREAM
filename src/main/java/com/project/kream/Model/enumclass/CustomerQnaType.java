package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerQnaType {
    계정문의("TYPE_ID", "ID"),
    입찰문의("TYPE_BID", "BID"),
    이용문의("TYPE_USE", "USE");

    private final String key;
    private final String title;
}
