package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.enumclass.BoardCategory;
import com.project.kream.Model.request.BoardApiRequest;
import com.project.kream.Model.response.BoardApiResponse;
import com.project.kream.Model.response.BoardFaqApiResponse;
import com.project.kream.Model.response.BoardSearchApiResponse;
import com.project.kream.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;

    // 글 등록
    @PostMapping("/api/board_register")
    public Long create(@RequestBody Header<BoardApiRequest> request) {
        return boardService.create(request);
    }

    // 글 수정
    @PutMapping("/api/board_update")
    public Long update(@RequestBody Header<BoardApiRequest> request) {
        return boardService.update(request);
    }

    // 글 리스트(조건)
    @GetMapping("/api/board_list/{category}")
    public Header<List<BoardApiResponse>> categoryList(@PathVariable BoardCategory category){
        return boardService.categoryList(category);
    }
    // 글 상세 페이지
    @GetMapping("/api/board_detail/{id}")
    public Header<BoardApiResponse> read(@PathVariable Long id){
        return boardService.read(id);
    }

    // 글 리스트
    @GetMapping("/api/board_list")
    public Header<List<BoardApiResponse>> List(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){ return boardService.List(pageable); }

    // 글 공지사항 리스트
    @GetMapping("/api/board_noticeList")
    public Header<List<BoardSearchApiResponse>> noticeList(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return boardService.noticeList(pageable);
    }

    // 글 자주묻는질문 리스트
    @GetMapping("/api/board_faqList")
    public Header<List<BoardFaqApiResponse>> faqList(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return boardService.faqList(pageable);
    }

    // 글 삭제
   @DeleteMapping("/api/board_delete/{id}")
    public int delete(@PathVariable Long id){
        return boardService.delete(id);
   }

    // 글 페이징
//    @GetMapping("/api/board_page")
//    public Header<List<BoardApiResponse>> findAll(@PageableDefault(sort={"idx"}, direction= Sort.Direction.DESC) Pageable pageable){
//        return boardService.paging(pageable);
//    }

    @PostMapping("/api/board_dataList")
    public Header<List<BoardSearchApiResponse>> dataList(@RequestBody Header<BoardApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return boardService.dataList(request, pageable);
    }
}
