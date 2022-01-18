package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.ProImg;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProimgApiRequest;
import com.project.kream.Model.response.ProimgApiResponse;
import com.project.kream.Service.ProimgService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProimgRestController extends CrudController<ProimgApiRequest, ProimgApiResponse, ProImg> {
    private final ProimgService proimgService;
}
