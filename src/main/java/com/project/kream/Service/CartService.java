package com.project.kream.Service;

import com.project.kream.Model.Entity.Cart;
import com.project.kream.Model.Header;
import com.project.kream.Model.request.CartApiRequest;
import com.project.kream.Model.response.CartApiResponse;
import com.project.kream.Repository.CartRepository;
import com.project.kream.Repository.CustomerRepository;
import com.project.kream.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public Long create(Header<CartApiRequest> request) {
        CartApiRequest cartApiRequest = request.getData();
        Cart cart = cartRepository.save(cartApiRequest.toEntity(productRepository.getById(cartApiRequest.getProductId()),customerRepository.getById(cartApiRequest.getCustomerId()) ));
        return cart.getId();
    }

    public int delete(Long id){
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if(cartOptional.isPresent()){
            cartRepository.delete(cartOptional.get());
            return 1;
        }
        return 0;
    }

    public Header delete(Long productId, String sizeType){
        Cart cart = cartRepository.findByProductIdAndSizeType(productId, sizeType);
        cartRepository.delete(cart);
        return Header.OK();
    }

    public int myDelete(Long cartId){
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isPresent()){
            cartRepository.delete(cartOptional.get());
            return 1;
        }
        return 0;
    }
}
