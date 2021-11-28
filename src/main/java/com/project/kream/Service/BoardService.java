package com.project.kream.Service;

import com.project.kream.Model.Entity.Board;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.BoardCategory;
import com.project.kream.Model.request.BoardApiRequest;
import com.project.kream.Model.response.BoardApiResponse;
import com.project.kream.Model.response.BoardFaqApiResponse;
import com.project.kream.Model.response.BoardSearchApiResponse;
import com.project.kream.Repository.BoardRepository;
import com.project.kream.Repository.Specification.BoardSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService extends BaseService<BoardApiRequest, BoardApiResponse, Board> {
    private final BoardRepository boardRepository;
    private final BoardSpecification boardSpecification;

    public Header<BoardApiResponse> create(Header<BoardApiRequest> request) {
        BoardApiRequest boardApiRequest = request.getData();

        Board board = Board.builder()
                .category(boardApiRequest.getCategory())
                .title(boardApiRequest.getTitle())
                .content(boardApiRequest.getContent())
                .registrant(boardApiRequest.getRegistrant())
                .build();
        Board newboard = baseRepository.save(board);
        return Header.OK(response(newboard));
    }


    public Header<BoardApiResponse> read(Long id){
        return baseRepository.findById(id)
                .map(board -> response(board))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터없음")
                );
    }

    public Header<BoardApiResponse> update(Header<BoardApiRequest> request) {
        BoardApiRequest boardApiRequest = request.getData();
        Optional<Board> optionalBoard = baseRepository.findById(boardApiRequest.getId());
        return optionalBoard.map(board ->{
                    board.setCategory(boardApiRequest.getCategory());
                    board.setTitle(boardApiRequest.getTitle());
                    board.setContent(boardApiRequest.getContent());
                    board.setRegistrant(boardApiRequest.getRegistrant());
            return board;
        }).map(board -> baseRepository.save(board))
                .map(board -> response(board))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다."));

    }

    public Header<List<BoardApiResponse>> List(Pageable pageable){
        Page<Board> boardList = baseRepository.findAll(pageable);
        List<BoardApiResponse> boardApiResponseList = boardList.stream()
                .map(notice -> response(notice))
                .collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((boardList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > boardList.getTotalPages()) {
            endPage = boardList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(boardList.getTotalPages())
                .totalElements(boardList.getTotalElements())
                .currentPage(boardList.getNumber())
                .currentElements(boardList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();

        return Header.OK(boardApiResponseList, pagination);
    }
    public Header<List<BoardSearchApiResponse>> noticeList(Pageable pageable){
        Page<Board> boardList = boardRepository.notice(pageable);

        List<BoardSearchApiResponse> boardSearchApiResponseList = boardList.stream()
                .map(board -> {
                    BoardSearchApiResponse boardSearchApiResponse = BoardSearchApiResponse.builder()
                            .id(board.getId())
                            .boardCategory(board.getCategory())
                            .title(board.getTitle())
                            .regdate(board.getRegdate())
                            .build();
                    return boardSearchApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((boardList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > boardList.getTotalPages()) {
            endPage = boardList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(boardList.getTotalPages())
                .totalElements(boardList.getTotalElements())
                .currentPage(boardList.getNumber())
                .currentElements(boardList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();

        return Header.OK(boardSearchApiResponseList, pagination);
    }

    public Header<List<BoardFaqApiResponse>> faqList(Pageable pageable){
        Page<Board> boardList = boardRepository.faq(pageable);

        List<BoardFaqApiResponse> BoardFaqApiResponseList = boardList.stream()
                .map(board -> {
                    BoardFaqApiResponse boardFaqApiResponse = BoardFaqApiResponse.builder()
                            .id(board.getId())
                            .boardCategory(board.getCategory())
                            .title(board.getTitle())
                            .content(board.getContent())
                            .regdate(board.getRegdate())
                            .build();
                    return boardFaqApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((boardList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > boardList.getTotalPages()) {
            endPage = boardList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(boardList.getTotalPages())
                .totalElements(boardList.getTotalElements())
                .currentPage(boardList.getNumber())
                .currentElements(boardList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();

        return Header.OK(BoardFaqApiResponseList, pagination);
    }
    public Header<List<BoardSearchApiResponse>> dataList(Header<BoardApiRequest> request, Pageable pageable){
        Page<Board> boardList = boardSpecification.searchCustomerList(request, pageable);

        List<BoardSearchApiResponse> boardSearchApiResponseList = boardList.stream()
                .map(board -> {
                    BoardSearchApiResponse boardSearchApiResponse = BoardSearchApiResponse.builder()
                            .id(board.getId())
                            .boardCategory(board.getCategory())
                            .title(board.getTitle())
                            .regdate(board.getRegdate())
                            .build();
                    return boardSearchApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((boardList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > boardList.getTotalPages()) {
            endPage = boardList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(boardList.getTotalPages())
                .totalElements(boardList.getTotalElements())
                .currentPage(boardList.getNumber())
                .currentElements(boardList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(boardSearchApiResponseList, pagination);
    }

    private BoardApiResponse response(Board board){
        BoardApiResponse userApiResponse = BoardApiResponse.builder()
                .id(board.getId())
                .category(board.getCategory())
                .title(board.getTitle())
                .content(board.getContent())
                .registrant(board.getRegistrant())
                .regdate(board.getRegdate())
                .build();
        return userApiResponse;
    }

    public Header delete(Long id){
        Optional<Board> boardOptional = baseRepository.findById(id);
        return boardOptional.map(board ->{
            baseRepository.delete(board);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

//    public Header<List<BoardApiResponse>> paging(Pageable pageable){
//        Page<Board> board = baseRepository.findAll(pageable);
//        List<BoardApiResponse> boardApiResponseList = board.stream()
//                .map(users -> response(users))
//                .collect(Collectors.toList());
//        Pagination pagination = Pagination.builder()
//                .totalPages(board.getTotalPages())
//                .totalElements(board.getTotalElements())
//                .currentPage(board.getNumber())
//                .currentElements(board.getNumberOfElements())
//                .build();
//        return Header.OK(boardApiResponseList, pagination);
//    }

    public Header<List<BoardApiResponse>> categoryList(BoardCategory category){
        List<Board> boardList = boardRepository.findAllByCategory(category);
        List<BoardApiResponse> boardApiResponseList = boardList.stream()
                .map(board -> response(board))
                .collect(Collectors.toList());
        return Header.OK(boardApiResponseList);
    }


}
