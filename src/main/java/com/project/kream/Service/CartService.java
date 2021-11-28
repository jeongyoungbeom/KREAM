package com.project.kream.Service;

import com.project.kream.Model.Entity.Cart;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CartApiRequest;
import com.project.kream.Model.response.CartApiResponse;
import com.project.kream.Repository.CartRepository;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService extends BaseService<CartApiRequest, CartApiResponse, Cart>{
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public Header<CartApiResponse> create(Header<CartApiRequest> request) {
        CartApiRequest cartApiRequest = request.getData();
        System.out.println(cartApiRequest);

        Cart cart = Cart.builder()
                .customer(customerRepository.getById(cartApiRequest.getCustomerId()))
                .sizeType(cartApiRequest.getSizeType())
                .product(productRepository.getById(cartApiRequest.getProductId()))
                .build();
        Cart newcart = baseRepository.save(cart);
        return Header.OK(response(newcart));
    }

    public Header<CartApiResponse> update(Header<CartApiRequest> request) {
        return null;
    }

    public Header delete(Long id){
        Optional<Cart> cartOptional = baseRepository.findById(id);
        return cartOptional.map(cart -> {
            baseRepository.delete(cart);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("데이터 없음"));
    }

    public Header delete(Long productId, String sizeType){
        Cart cart = cartRepository.findByProductIdAndSizeType(productId, sizeType);

        cartRepository.delete(cart);

        return Header.OK();
    }

    public Header myDelete(Long cartId){
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        return cartOptional.map(cart -> {
            baseRepository.delete(cart);
            return Header.OK();
        }).orElseGet(()-> Header.ERROR("데이터 없음"));
    }


    public CartApiResponse response(Cart cart){
        CartApiResponse cartApiResponse = CartApiResponse.builder()
                .id(cart.getId())
                .customerId(cart.getCustomer().getId())
                .productId(cart.getProduct().getId())
                .build();
        return cartApiResponse;
    }
}
