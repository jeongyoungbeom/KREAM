package com.project.kream.Service;

import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.AddressApiRequest;
import com.project.kream.Model.response.AddressApiResponse;
import com.project.kream.Repository.AddressRepository;
import com.project.kream.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService extends BaseService<AddressApiRequest, AddressApiResponse, Address>{
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    public Header<AddressApiResponse> create(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();

        Address address = Address.builder()
                .name(addressApiRequest.getName())
                .hp(addressApiRequest.getHp())
                .zipcode(addressApiRequest.getZipcode())
                .detail1(addressApiRequest.getDetail1())
                .detail2(addressApiRequest.getDetail2())
                .flag(addressApiRequest.getFlag())
                .customer(customerRepository.getById(addressApiRequest.getCustomerId()))
                .build();
        Address newAddress = baseRepository.save(address);
        return Header.OK(response(newAddress));

    }

    public Header<AddressApiResponse> update(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();
        Optional<Address> optionalAddress = baseRepository.findById(addressApiRequest.getId());
        return optionalAddress.map(address ->{
            address.setName(addressApiRequest.getName());
            address.setHp(addressApiRequest.getHp());
            address.setZipcode(addressApiRequest.getZipcode());
            address.setDetail1(addressApiRequest.getDetail1());
            address.setDetail2(addressApiRequest.getDetail2());
            address.setFlag(addressApiRequest.getFlag());
            return address;
        }).map(address -> baseRepository.save(address))
                .map(address -> response(address))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    // 기본 배송지 수정(Service)
    public Header<AddressApiResponse> flagUpdate(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();
        Optional<Address> optionalAddress = baseRepository.findById(addressApiRequest.getId());
        return optionalAddress.map(address ->{
                    address.setId(addressApiRequest.getId());
                    address.setFlag(addressApiRequest.getFlag());
                    return address;
                }).map(address -> baseRepository.save(address))
                .map(address -> response(address))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터가 없습니다"));
    }

    public Header<AddressApiResponse> read(Long id){
        return baseRepository.findById(id)
                .map(address -> response(address))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    public Header<List<AddressApiResponse>> List(Long id){
        List<Address> addressList = addressRepository.findAllByCustomerId(id);
        List<AddressApiResponse> addressApiResponseList = addressList.stream()
                .map(newaddress -> response(newaddress))
                .collect(Collectors.toList());
        return Header.OK(addressApiResponseList);
    }

    public Header delete(Long id) {
        Optional<Address> optionalAddress = baseRepository.findById(id);

        return optionalAddress.map(address -> {
            baseRepository.delete(address);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    public AddressApiResponse response(Address address){
        AddressApiResponse addressApiResponse = AddressApiResponse.builder()
                .id(address.getId())
                .name(address.getName())
                .hp(address.getHp())
                .zipcode(address.getZipcode())
                .detail1(address.getDetail1())
                .detail2(address.getDetail2())
                .flag(address.getFlag())
                .customerId(address.getCustomer().getId())
                .regdate(address.getRegdate())
                .build();
        return addressApiResponse;
    }
}
