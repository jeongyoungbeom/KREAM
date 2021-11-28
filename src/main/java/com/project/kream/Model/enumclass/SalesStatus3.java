package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SalesStatus3 {

    판매("SALES","SALES"),
    일반("COMMON","COMMON");

    private final String status;
    private final String title;
}
