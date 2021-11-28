package com.project.kream.Model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductUpcomingApiResponse {
    private Long id;
    private String oringinFileName;
    private String brand;
    private String name;
    private String regdate;
}
