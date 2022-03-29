package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.request.StyleCustomerApiRequest;
import com.project.kream.Model.response.StyleCustomerApiResponse;
import com.project.kream.Service.StyleCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StyleCustomerRestController {
    private final StyleCustomerService styleCustomerService;

    @GetMapping("/api/style_customer_detail/{customerId}")
    public Header<StyleCustomerApiResponse> detail(@PathVariable Long customerId){
        return styleCustomerService.detail(customerId);
    }

    @PutMapping("/api/style_customer_update")
    public Header<Long> update(@RequestBody Header<StyleCustomerApiRequest> request){
        return styleCustomerService.update(request);
    }
}
