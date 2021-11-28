package com.project.kream.Model.enumclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubCategory {
    아우터("cloth_outer", "cloth"),
    상의("cloth_top", "cloth"),
    하의("cloth_pants", "cloth"),
    기타("etc", "etc"),
    모자("fashion_cab", "fashion"),
    가방("fashion_bag", "fashion"),
    지갑및카드홀더("fashion_wallet", "fashion"),
    그래픽카드("tech_graphic", "tech"),
    게임기("tech_game", "tech"),
    라이프("life_life","life"),
    스니커즈("sneakers","sneakers");


    private final String key;
    private final String title;

}
