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

    public Header<Long> create(String originFileName, Long fileSize, String safeFile, Long idx){
        ProimgApiRequest proimgApiRequest = new ProimgApiRequest();
        ProImg newproimg = baseRepository.save(proimgApiRequest.toEntity(originFileName, fileSize, safeFile, productRepository.getById(idx)));
        return Header.OK(newproimg.getId());
    }
}
