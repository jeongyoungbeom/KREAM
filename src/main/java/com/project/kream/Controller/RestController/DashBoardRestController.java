package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.response.DashBoardApiResponse;
import com.project.kream.Service.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashBoardRestController {
    private final DashBoardService dashBoardService;

    @GetMapping("/api/dash_board")
    public Header<DashBoardApiResponse> index(){
        return dashBoardService.index();
    }
}
