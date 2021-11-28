package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PurchaseStatus3 {

    구매("PURCHASE","PURCHASE"),
    일반("COMMON","COMMON");

    private final String status;
    private final String title;
}
