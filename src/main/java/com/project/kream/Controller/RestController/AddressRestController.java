package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Address;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.AddressApiRequest;
import com.project.kream.Model.response.AddressApiResponse;
import com.project.kream.Service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressRestController extends CrudController<AddressApiRequest, AddressApiResponse, Address> {
    private final AddressService addressService;

    // 주소 등록
    @PostMapping("/api/Address_register")
    public Header<AddressApiResponse> create(@RequestBody Header<AddressApiRequest> request) {
        return addressService.create(request);
    }

    @PutMapping("/api/Address_flag_update")
    public Header<AddressApiResponse> flagUpdate(@RequestBody Header<AddressApiRequest> request) {
        return addressService.flagUpdate(request);
    }

    // 주소 수정
    @PutMapping("/api/Address_update")
    public Header<AddressApiResponse> update(@RequestBody Header<AddressApiRequest> request) {
        return addressService.update(request);
    }

    // 주소 리스트
    @GetMapping("/api/Address_list/{id}")
    public Header<List<AddressApiResponse>> List(@PathVariable Long id){
        return addressService.List(id);
    }

    // 주소 삭제
    @DeleteMapping("/api/Address_delete/{id}")
    public Header<AddressApiResponse> delete(@PathVariable Long id){
        return addressService.delete(id);
    }

    // 주소 상세
    @GetMapping("/api/Address_detail/{id}")
    public Header<AddressApiResponse> read(@PathVariable Long id){
        return addressService.read(id);
    }
}
