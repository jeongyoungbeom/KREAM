package com.project.kream.Service;

import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.request.DeliveryApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryService extends BaseService<DeliveryApiRequest, DeliveryApiResponse, Delivery> {
    private final DeliveryRepository deliveryRepository;
    private final AddressService addressService;


    public Header<DeliveryApiResponse> create(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest deliveryApiRequest =request.getData();
        Delivery delivery = Delivery.builder()
                .deliveryStatus(deliveryApiRequest.getDeliveryStatus())
                .devCompany(deliveryApiRequest.getDevCompany())
                .trackNum(deliveryApiRequest.getTrackNum())
                .build();

        Delivery newDelivery = baseRepository.save(delivery);

        return Header.OK(response(newDelivery));
    }

    public Header<DeliveryApiResponse> update(Header<DeliveryApiRequest> request) {
        DeliveryApiRequest deliveryApiRequest = request.getData();
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryApiRequest.getId());
        return delivery.map(dev ->{
            dev.setDeliveryStatus(deliveryApiRequest.getDeliveryStatus());
            dev.setDevCompany(deliveryApiRequest.getDevCompany());
            dev.setTrackNum(deliveryApiRequest.getTrackNum());

            return dev;
        }).map(dev -> baseRepository.save(dev))
                .map(dev -> response(dev))
                .map(Header::OK)
                .orElseGet(()-> Header.ERROR("데이터가 없습니다."));

    }

    public Header<List<DeliveryListApiResponse>> list(Header<DeliveryApiRequest> request, Pageable pageable){
        DeliveryApiRequest deliveryApiRequest = request.getData();
        Page<Delivery> deliveryList = deliveryRepository.SearchList(deliveryApiRequest.getId(), deliveryApiRequest.getPurchaseId(), deliveryApiRequest.getDeliveryStatus(), deliveryApiRequest.getDevCompany(), deliveryApiRequest.getTrackNum(), deliveryApiRequest.getRegdate1(), deliveryApiRequest.getRegdate2(), pageable);
        List<DeliveryListApiResponse> deliveryListApiResponseList = deliveryList.stream()
                .map(delivery -> {
                    DeliveryListApiResponse deliveryListApiResponse = DeliveryListApiResponse.builder()
                            .deliveryId(delivery.getId())
                            .purchaseId(delivery.getPurchase().getId())
                            .deliveryStatus(delivery.getDeliveryStatus())
                            .devCompany(delivery.getDevCompany())
                            .trackNum(delivery.getTrackNum())
                            .regdate(delivery.getRegdate())
                            .build();
                    return deliveryListApiResponse;
                }).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((deliveryList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > deliveryList.getTotalPages()) {
            endPage = deliveryList.getTotalPages();
        }

        Pagination pagination = Pagination.builder()
                .totalPages(deliveryList.getTotalPages())
                .totalElements(deliveryList.getTotalElements())
                .currentPage(deliveryList.getNumber())
                .currentElements(deliveryList.getNumberOfElements())
                .startPage(startPage)
                .endPage(endPage)
                .build();
        return Header.OK(deliveryListApiResponseList, pagination);
    }


    public Header<DeliveryApiResponse> read(Long id){
        return deliveryRepository.findById(id)
                .map(dev -> response(dev))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    public DeliveryApiResponse response(Delivery delivery){
        DeliveryApiResponse deliveryApiResponse = DeliveryApiResponse.builder()
                .id(delivery.getId())
                .deliveryStatus(delivery.getDeliveryStatus())
                .devCompany(delivery.getDevCompany())
                .trackNum(delivery.getTrackNum())
                .purchaseId(delivery.getPurchase().getId())
                .regdate(delivery.getRegdate())
                .build();

        return  deliveryApiResponse;
    }


    public Header delete(Long id){
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        return delivery.map(dev ->{
            baseRepository.delete(dev);
            return Header.OK();
        }).orElseGet(() ->Header.ERROR("데이터없음"));
    }


    public Header<DeliveryPurchaseApiResponse> deliveryInfo(Long id){
        Delivery delivery = baseRepository.getById(id);
        DeliveryApiResponse deliveryApiResponse = response(delivery);

        Purchase purchase = delivery.getPurchase();
        Customer customer = purchase.getCustomer();

        Product product = purchase.getProduct();
        ProductApiResponse productApiResponse = ProductApiResponse.builder()
                .brand(product.getBrand())
                .id(product.getId())
                .collection(product.getCollection())
                .category(product.getCategory())
                .name(product.getName())
                .korName(product.getKorName())
                .gender(product.getGender())
                .release(product.getRelease())
                .releasePrice(product.getReleasePrice())
                .modelNumber(product.getModelNumber())
                .color(product.getColor())
                .build();
        Address address = purchase.getAddress();
        AddressApiResponse addressApiResponse = addressService.response(address);

        DeliveryPurchaseApiResponse deliveryPurchaseApiResponse = DeliveryPurchaseApiResponse.builder()
                .deliveryApiResponse(deliveryApiResponse)
                .productApiResponse(productApiResponse)
                .addressApiResponse(addressApiResponse)
                .email(customer.getEmail())
                .hp(customer.getHp())
                .size(purchase.getSizeType())
                .build();
        return Header.OK(deliveryPurchaseApiResponse);

    }
}
