package com.project.kream.Service;

import com.project.kream.Model.Entity.ProImg;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProimgApiRequest;
import com.project.kream.Model.response.ProimgApiResponse;
import com.project.kream.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProimgService extends BaseService<ProimgApiRequest, ProimgApiResponse, ProImg> {
    private final ProductRepository productRepository;

    public Header<ProimgApiResponse> create(String originFileName, Long fileSize, String safeFile, Long idx){
        ProImg proimg = ProImg.builder()
                .origFileName(originFileName)
                .filePath(safeFile)
                .fileSize(fileSize)
                .product(productRepository.getById(idx))
                .build();
        ProImg newproimg = baseRepository.save(proimg);

        return Header.OK(response(newproimg));
    }

    public Header<ProimgApiResponse> update(Header<ProimgApiRequest> request) {
        return null;
    }


    public ProimgApiResponse response(ProImg proimg){
        ProimgApiResponse proimgApiResponse = ProimgApiResponse.builder()
                .id(proimg.getId())
                .origFileName(proimg.getOrigFileName())
                .fileSize(proimg.getFileSize())
                .filePath(proimg.getFilePath())
                .build();
        return proimgApiResponse;
    }

}
