package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StyleHashTagListApiResponse {
    private Long id;
    private String profileName;
    private String customerOriginFileName;
    private String styleOriginFileName;
}
