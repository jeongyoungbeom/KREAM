package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QnaStauts {
    처리("TYPE_PROCESS", "PROCESS"),
    미처리("TYPE_NOPROCESS", "NOPROCESS");

    private final String key;
    private final String title;
}
