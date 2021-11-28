package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardFlag {
    ON("ON","ON"),
    OFF("OFF","OFF");

    private final String key;
    private final String title;
}
