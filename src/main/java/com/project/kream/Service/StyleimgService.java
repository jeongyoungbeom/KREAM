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
public class StyleimgService {
    private final StyleRepository styleRepository;
    private final StyleimgRepository styleimgRepository;

    public Header<Long> create(String originFileName, Long fileSize, String safeFile, Long idx) {
        StyleimgApiRequest styleimgApiRequest = StyleimgApiRequest.builder()
                .origFileName(originFileName)
                .filePath(safeFile)
                .fileSize(fileSize)
                .styleId(idx)
                .build();
        StyleImg styleImg = styleimgRepository.save(styleimgApiRequest.toEntity(styleRepository.getById(idx)));
        return Header.OK(styleImg.getId());
    }


    public Header<List<StyleimgApiResponse>> list(){
        List<StyleImg> styleImgs = styleimgRepository.findAll();
        List<StyleimgApiResponse> styleimgApiResponseList = styleImgs.stream()
                .map(StyleimgApiResponse::new)
                .collect(Collectors.toList());

        return Header.OK(styleimgApiResponseList);
    }

    public int delete(Long id){
        Optional<StyleImg> styleimg = styleimgRepository.findById(id);
        if(styleimg.isPresent()){
            styleimgRepository.delete(styleimg.get());
            return 1;
        }
        return 0;
    }


}
