package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Style;

import java.util.List;

public interface StyleCustom {
    List<Style> HashTagList(String tagName);

}
