package com.project.kream.Model.enumclass;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {

    배송중("delivery", "delivery"),
    배송완료("completed", "completed"),
    배송대기("Waiting ", "Waiting "),
    정보없음("no_information", "no_information");

    private final String key;
    private final String title;


}
