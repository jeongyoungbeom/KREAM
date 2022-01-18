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

    public Header<Long> create(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();
        Address address = addressRepository.save(addressApiRequest.toEntity(customerRepository.getById(addressApiRequest.getCustomerId())));
        return Header.OK(address.getId());

    }

    // 주소 수정
    public Long update(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();
        Address address = addressRepository.findById(addressApiRequest.getId()).orElseThrow(() -> new IllegalArgumentException("해당 주소 없음"));

        address.update(addressApiRequest.getName(), addressApiRequest.getHp(), addressApiRequest.getZipcode(), addressApiRequest.getDetail1(), addressApiRequest.getDetail2(), addressApiRequest.getFlag());
        return addressApiRequest.getId();
    }

    // 기본 배송지 수정(Service)
    public Long flagUpdate(Header<AddressApiRequest> request) {
        AddressApiRequest addressApiRequest = request.getData();
        Address address = addressRepository.getById(addressApiRequest.getId());
        address.update(addressApiRequest.getId(), addressApiRequest.getFlag());
        return address.getId();
    }

    public Header<AddressApiResponse> read(Long id){
        Address address = addressRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 주소 없음"));
        return Header.OK(new AddressApiResponse(address));

    }

    public Header<List<AddressApiResponse>> List(Long id){
        List<Address> addressList = addressRepository.findAllByCustomerId(id);
        List<AddressApiResponse> addressApiResponseList = addressList.stream()
                .map(AddressApiResponse::new)
                .collect(Collectors.toList());
        return Header.OK(addressApiResponseList);
    }

    public int delete(Long id) {
        Optional<Address> optionalAddress = baseRepository.findById(id);
        if(optionalAddress.isPresent()){
            addressRepository.delete(optionalAddress.get());
            return 1;
        }
        return 0;
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
