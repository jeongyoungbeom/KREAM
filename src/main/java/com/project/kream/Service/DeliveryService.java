package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.DeliveryApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.DeliveryRepository;
import com.project.kream.Repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService extends BaseService<DeliveryApiRequest, DeliveryApiResponse, Delivery> {
    private final DeliveryRepository deliveryRepository;
    private final PurchaseRepository purchaseRepository;

    public Header<Long> create(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest deliveryApiRequest = request.getData();
        Long id = deliveryRepository.save(deliveryApiRequest.toEntity(purchaseRepository.getById(deliveryApiRequest.getPurchaseId()))).getId();
        return Header.OK(id);
    }

    @Transactional
    public Long update(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest deliveryApiRequest = request.getData();
        Delivery delivery = deliveryRepository.findById(deliveryApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        delivery.update(deliveryApiRequest.getDeliveryStatus(), deliveryApiRequest.getDevCompany(), deliveryApiRequest.getTrackNum());
        return deliveryApiRequest.getId();

    }

    public Header<List<DeliveryListApiResponse>> list(Header<DeliveryApiRequest> request, Pageable pageable){
        DeliveryApiRequest deliveryApiRequest = request.getData();
        Page<Delivery> deliveryList = deliveryRepository.SearchList(deliveryApiRequest.getId(), deliveryApiRequest.getPurchaseId(), deliveryApiRequest.getDeliveryStatus(), deliveryApiRequest.getDevCompany(), deliveryApiRequest.getTrackNum(), deliveryApiRequest.getRegdate1(), deliveryApiRequest.getRegdate2(), pageable);
        List<DeliveryListApiResponse> deliveryListApiResponseList = deliveryList.stream()
                .map(DeliveryListApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((deliveryList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > deliveryList.getTotalPages()) {
            endPage = deliveryList.getTotalPages();
        }

        return Header.OK(deliveryListApiResponseList, new Pagination(deliveryList, startPage, endPage));
    }

    public int delete(Long id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if(delivery.isPresent()) {
            deliveryRepository.delete(delivery.get());
            return 1;
        }
        return 0;
    }

    public Header<DeliveryPurchaseApiResponse> deliveryInfo(Long id){
        Delivery delivery = baseRepository.getById(id);
        return Header.OK(new DeliveryPurchaseApiResponse(new DeliveryApiResponse(delivery), new DeliveryProductApiResponse(delivery.getPurchase().getProduct()),
                new AddressApiResponse(delivery.getPurchase().getAddress()), delivery.getPurchase().getCustomer(), delivery.getPurchase()));
    }
}
