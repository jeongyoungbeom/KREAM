package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerType {
    일반("TYPE_COMMON", "COMMON"),
    블랙("TYPE_BLACK", "BLACK"),
    탈퇴("TYPE_withdraw", "withdraw");

    private final String key;
    private final String title;
}
