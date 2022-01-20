package com.project.kream.Controller.RestController;


import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Style;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Service.StyleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StyleRestController extends CrudController<StyleApiRequest, StyleApiResponse, Style> {
    private final StyleService styleService;

    @PostMapping("/api/style_register")
    public Long create(@RequestPart(value = "data") Header<StyleApiRequest> request, MultipartHttpServletRequest multipart){
        return styleService.create(request, multipart);
    }

    ///////////////////
    @GetMapping("/api/style_hit_list/{sessionId}")
    public Header<List<StyleHitListApiResponse>> styleHitList(@PathVariable Long sessionId){
        return styleService.styleHitList(sessionId);
    }

    @GetMapping("/api/style_hit_list/")
    public Header<List<StyleHitListApiResponse>> noStyleHitList(){
        return styleService.noStyleHitList();
    }
    ///////////////////

    @GetMapping("/api/style_user_list/{customerId}/{sessionId}")
    public Header<StyleUserInfoApiResponse> styleUserList(@PathVariable Long customerId, @PathVariable Long sessionId){
        return styleService.styleUserList(customerId, sessionId);
    }

    @GetMapping("/api/style_user_list/{customerId}")
    public Header<StyleUserInfoApiResponse> noStyleUserList(@PathVariable Long customerId){
        return styleService.noStyleUserList(customerId);
    }
    ////////////////
    @GetMapping("/api/style_hash_list/{tagName}/{sessionId}")
    public Header<List<StyleHashListApiResponse>> styleHashList(@PathVariable String tagName, @PathVariable Long sessionId){
        return styleService.styleHashList(tagName, sessionId);
    }

    @GetMapping("/api/style_hash_list/{tagName}")
    public Header<List<StyleHashListApiResponse>> noStyleHashList(@PathVariable String tagName){
        return styleService.noStyleHashList(tagName);
    }
    //////////////////////
    @PostMapping("/api/style_imgUpload/{id}")
    public Header<StyleApiResponse> imgUpload(@PathVariable Long id, MultipartHttpServletRequest multiRequest){
        return styleService.upload(id, multiRequest);
    }
    /////////////
    @GetMapping("/api/style_detailList/{sessionId}")
    public Header<List<StyleDetailListApiResponse>> detailList(@PathVariable Long sessionId){
        return styleService.detailList(sessionId);
    }

    @GetMapping("/api/style_detailList/")
    public Header<List<StyleDetailListApiResponse>> noDetailList(){
        return styleService.noDetailList();
    }
    //////////////
    @GetMapping("/api/style_detail_following_List/{followerId}")
    public Header<List<StyleDetailListApiResponse>> detailFollowingList(@PathVariable Long followerId){
        return styleService.detailFollowingList(followerId);
    }

    ///////////////
    @GetMapping("/api/style_detail_user_List/{customerId}/{sessionId}")
    public Header<List<StyleDetailListApiResponse>> detailUserList(@PathVariable Long customerId, @PathVariable Long sessionId){
        return styleService.detailUserList(customerId, sessionId);
    }

    @GetMapping("/api/style_detail_user_List/{customerId}")
    public Header<List<StyleDetailListApiResponse>> noDetailUserList(@PathVariable Long customerId){
        return styleService.noDetailUserList(customerId);
    }
    ///////////////
    @PutMapping("/api/style_update")
    public Long update(@RequestPart(value = "data") Header<StyleApiRequest> request) {
        return styleService.update(request);
    }

    // 페이징
    @GetMapping("/api/style_search")
    public Header<List<StyleApiResponse>> findAll(@PageableDefault(sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return styleService.search(pageable);
    }

    // 스타일 상세
    @GetMapping("/api/style_detail/{styleId}")
    public Header<StyleDetailApiResponse> detail(@PathVariable Long styleId){
        return styleService.detail(styleId);
    }

    //스타일 삭제
    @DeleteMapping("/api/style_delete/{id}")
    public int delete(@PathVariable Long id){
        return styleService.delete(id);
    }

}
