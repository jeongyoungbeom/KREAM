package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AddressFlag {
    ON("FLAG_ON", "ON"),
    OFF("FLAG_OFF", "OFF");

    private final String key;
    private final String title;
}
