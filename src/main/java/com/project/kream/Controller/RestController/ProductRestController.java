package com.project.kream.Controller.RestController;

import com.project.kream.Model.Header;
import com.project.kream.Model.request.ProductApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;

    //상품 등록
    @PostMapping("/api/pro_register")
    public Header<Long> create(@RequestPart(value = "data") ProductApiRequest request, MultipartHttpServletRequest mutilRequest) throws Exception{
        return productService.create(request, mutilRequest);
    }

    //상품 업데이트
    @PutMapping("/api/pro_update")
    public Header<Long> update(@RequestPart(value = "data") ProductApiRequest request) {
        return productService.update(request);
    }

    // 상품 이미지저장
    @PostMapping("/api/pro_imgUpload/{id}")
    public Header<ProductApiResponse> imgUpload(@PathVariable Long id, MultipartHttpServletRequest mutilRequest) throws Exception{
        return productService.upload(id, mutilRequest);
    }

    //사용자 상품 전체 리스트
    @PostMapping("/api/pro_userlist")
    public Header<List<ProductUserListApiResponse>> userList(@RequestBody Header<ProductApiRequest> request){
        return productService.userList(request);
    }

    // 관리자 상품 전체 리스트
    @GetMapping("/api/pro_adminlist")
    public Header<List<ProductAdminListApiResponse>> adminList(@PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return productService.adminList(pageable);
    }
    // 상품 상세
    @GetMapping("/api/pro_detail/{id}")
    public Header<ProductDetailApiResponse> detail(@PathVariable Long id){
        return productService.userDetail(id);
    }

    // 관리자 상세
    @GetMapping("/api/pro_ad_detail/{id}")
    public Header<ProductAdminDetailApiResponse> addetail(@PathVariable Long id){
        return productService.adminDetail(id);
    }

    // 바이체크 부분
    @GetMapping("/api/pro_buy_check/{id}/{size}")
    public Header<ProductBuyCheckApiResponse> buyCheck(@PathVariable Long id, @PathVariable String size){
        return productService.buyCheck(id, size);
    }

    // 셀체크 부분
    @GetMapping("/api/pro_sell_check/{id}/{size}")
    public Header<ProductSellCheckApiResponse> sellCheck(@PathVariable Long id, @PathVariable String size){
        return productService.sellCheck(id, size);
    }

    // buy choice
    @GetMapping("/api/pro_buy_info/{id}/{size}")
    public Header<ProductBuyInfoApiResponse> buyInfo(@PathVariable Long id, @PathVariable String size){
        return productService.buyInfo(id, size);
    }

    // sell choice
    @GetMapping("/api/pro_sell_info/{id}/{size}")
    public Header<ProductSellInfoApiResponse> sellInfo(@PathVariable Long id, @PathVariable String size){
        return productService.sellInfo(id, size);
    }

    // buy빠이너루
    @GetMapping("/api/pro_buyfinal/{productId}/{customerId}/{size}/{price}/{date}")
    public Header<ProductBuyFinalApiResponse> buyFinal(@PathVariable Long productId, @PathVariable Long customerId, @PathVariable String size, @PathVariable Long price, @PathVariable Long date){
        return productService.buyFinal(productId, customerId, size, price, date);
    }

    // sell빠이너루
    @GetMapping("/api/pro_sellfinal/{productId}/{customerId}/{size}/{price}")
    public Header<ProductSellFinalResponse> sellFinal(@PathVariable Long productId, @PathVariable Long customerId, @PathVariable String size, @PathVariable Long price){
        return productService.sellFinal(productId, customerId, size, price);
    }

    // buyFinish
    @GetMapping("/api/pro_buyfinish/{productId}")
    public Header<ProductBuyFinishApiResponse> buyFinish(@PathVariable Long productId){
        return productService.buyFinish(productId);
    }

    // sellFinish
    @GetMapping("/api/pro_sellfinish/{productId}")
    public Header<ProductSellFinishApiResponse> sellFinish(@PathVariable Long productId){
        return productService.sellFinish(productId);
    }

    // 상품 id로 데이터 삭제
    @DeleteMapping("/api/pro_delete/{id}")
    public Header<ProductApiResponse> delete(@PathVariable Long id){
        return productService.delete(id);
    }

    // 조건 검색
    @PostMapping("/api/pro_dataList")
    public Header<List<ProductSearchApiResponse>> dataList(@RequestBody Header<ProductApiRequest> request, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable) throws ParseException {
        return productService.dataList(request, pageable);
    }

    // 사용자 검색 큰 리스트
    @PostMapping("/api/pro_usersearchlist/{keywords}")
    public Header<List<ProductUserBigListApiResponse>> userSearchList(@PathVariable String keywords, @PageableDefault(size = 40, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return productService.userSearchList(keywords, pageable);
    }

    // 사용자 검색 리스트
    @PostMapping("/api/pro_searchlist/{keywords}")
    public Header<List<ProductSearchListApiResponse>> searchList(@PathVariable String keywords, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return productService.searchList(keywords, pageable);
    }

    // 스타일 업로드 검색 리스트
    @PostMapping("/api/pro_style_searchlist/{keywords}")
    public Header<List<StyleSearchListApiResponse>> styleSearchList(@PathVariable String keywords, @PageableDefault(size = 10, sort={"id"}, direction= Sort.Direction.DESC) Pageable pageable){
        return productService.styleSearchList(keywords, pageable);
    }

    // 사용자 관심상품
    @GetMapping("/api/pro_userlist_cart/{productId}/{customerId}")
    public Header<ProductUserListCartApiResponse> userListCart(@PathVariable Long productId, @PathVariable Long customerId){
        return productService.userListCart(productId, customerId);
    }

    // PostStatus 개수
    @GetMapping("/api/pro_post_count")
    public Header<ProductPostCntApiResponse> postCount(){
        return productService.postCount();
    }

    // 메인
    @GetMapping("/api/main")
    public Header<MainApiResponse> main(){
        return productService.main();
    }

}
