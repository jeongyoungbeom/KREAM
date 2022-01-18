package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.HashTag;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.HashTagApiRequest;
import com.project.kream.Model.response.HashTagApiResponse;
import com.project.kream.Service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HashTagRestController extends CrudController<HashTagApiRequest, HashTagApiResponse, HashTag> {
    private final HashTagService hashTagService;

    //전체 해쉬태그들 리스트
    @GetMapping("/api/hashtag_list")
    public Header<List<HashTagApiResponse>> list(){
        return hashTagService.list();
    }





}
