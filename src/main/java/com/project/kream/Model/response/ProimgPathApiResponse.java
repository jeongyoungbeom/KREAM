package com.project.kream.Model.response;

import com.project.kream.Model.Entity.ProImg;
import lombok.*;

@Getter
public class ProimgPathApiResponse {
    private String origFileName;

    public ProimgPathApiResponse(ProImg proImg) {
        this.origFileName = origFileName;
    }
}
