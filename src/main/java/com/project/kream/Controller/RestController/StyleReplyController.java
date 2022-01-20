package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.StyleReply;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleReplyApiRequest;
import com.project.kream.Model.response.StyleApiResponse;
import com.project.kream.Model.response.StyleReplyApiResponse;
import com.project.kream.Model.response.StyleReplyDetailApiResponse;
import com.project.kream.Model.response.StyleReplyPopApiResponse;
import com.project.kream.Service.StyleReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StyleReplyController extends CrudController<StyleReplyApiRequest, StyleReplyApiResponse, StyleReply> {
    private final StyleReplyService styleReplyService;

    @PostMapping("/api/st_reply_register")
    public Long create(@RequestBody Header<StyleReplyApiRequest> request) {
        return styleReplyService.create(request);
    }

    @PutMapping("/api/st_reply_update")
    public Long update(@RequestBody Header<StyleReplyApiRequest> request) {
        return styleReplyService.update(request);
    }

    @GetMapping("/api/st_reply_pop/{styleId}/{customerId}")
    public Header<StyleReplyPopApiResponse> replyPop(@PathVariable Long styleId, @PathVariable Long customerId){
        return styleReplyService.replyPop(styleId, customerId);
    }

    @DeleteMapping("/api/st_reply_delete/{id}")
    public void delete(@PathVariable Long id){
        styleReplyService.delete(id);
    }



}
