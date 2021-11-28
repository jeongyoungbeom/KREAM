package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerRank {
    BRONZE("RANK_BRONZE", "BRONZE"),
    SILVER("RANK_SILVER", "SILVER"),
    GOLD("RANK_GOLD", "GOLD"),
    PLATINUM("RANK_PLATINUM", "PLATINUM"),
    DIAMOND("RANK_DIAMOND", "DIAMOND");

    private final String key;
    private final String title;
}
