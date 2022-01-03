package com.project.kream.Controller.RestController;

import com.project.kream.Controller.CrudController;
import com.project.kream.Model.Entity.Cart;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CartApiRequest;
import com.project.kream.Model.response.CartApiResponse;
import com.project.kream.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartRestController extends CrudController<CartApiRequest, CartApiResponse, Cart> {
    private final CartService cartService;

    // 관심상품 등록
    @PostMapping("/api/cart_register")
    public Long create(@RequestBody Header<CartApiRequest> request) {
        return cartService.create(request);
    }

    // 마이페이지
    @DeleteMapping("/api/cart_delete/{cartId}")
    public int myDelete(@PathVariable Long cartId){
        return cartService.myDelete(cartId);
    }


    // 프로독투 관심상품 삭제
    @DeleteMapping("/api/cart_delete/{productId}/{sizeType}")
    public Header<CartApiResponse> delete(@PathVariable Long productId, @PathVariable String sizeType){
        return cartService.delete(productId, sizeType);
    }
}
