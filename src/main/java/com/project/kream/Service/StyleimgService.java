package com.project.kream.Service;


import com.project.kream.Model.Entity.StyleImg;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleimgApiRequest;
import com.project.kream.Model.response.StyleimgApiResponse;
import com.project.kream.Repository.StyleRepository;
import com.project.kream.Repository.StyleimgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StyleimgService extends BaseService<StyleimgApiRequest, StyleimgApiResponse, StyleImg> {
    private final StyleRepository styleRepository;
    private final StyleimgRepository styleimgRepository;

    public Header<StyleimgApiResponse> create(String originFileName, Long fileSize, String safeFile, Long idx) {
        StyleImg styleimg = StyleImg.builder()
                .origFileName(originFileName)
                .filePath(safeFile)
                .fileSize(fileSize)
                .style(styleRepository.getById(idx))
                .build();

        StyleImg newstyleimg = baseRepository.save(styleimg);

        return Header.OK(response(newstyleimg));
    }

    public Header<StyleimgApiResponse> update(Header<StyleimgApiRequest> request) {
       StyleimgApiRequest styleimgApiRequest = request.getData();
        Optional<StyleImg> styleimg = styleimgRepository.findById(styleimgApiRequest.getId());
        return styleimg.map(stimg ->{
            stimg.setStyle(styleRepository.getById(styleimgApiRequest.getStyleId()));
            stimg.setFilePath(styleimgApiRequest.getFilePath());

            return stimg;
        }).map(stimg -> baseRepository.save(stimg))
                .map(stimg -> response(stimg))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가없습니다"));

    }

    public Header<List<StyleimgApiResponse>> list(){
        List<StyleImg> styleImgs = baseRepository.findAll();
        List<StyleimgApiResponse> styleimgApiResponseList = styleImgs.stream()
                .map(stimg -> response(stimg))
                .collect(Collectors.toList());

        return Header.OK(styleimgApiResponseList);
    }

    public Header delete(Long id){
        Optional<StyleImg> styleimg = styleimgRepository.findById(id);
        return styleimg.map(stimg ->{
            baseRepository.delete(stimg);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터없음"));
    }

    public StyleimgApiResponse response(StyleImg styleimg){
        StyleimgApiResponse styleimgApiResponse = StyleimgApiResponse.builder()
                .id(styleimg.getId())
                .styleId(styleimg.getStyle().getId())
                .filePath(styleimg.getFilePath())
                .build();
        return styleimgApiResponse;

    }

}
