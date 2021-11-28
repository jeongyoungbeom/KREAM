package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayStatus {

        결제완료("com_payment","com_payment"),
        결제대기("waiting_payment","waiting_payment");


        private final String status;
        private final String title;
    }

