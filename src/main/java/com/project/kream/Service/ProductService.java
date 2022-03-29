package com.project.kream.Service;


import com.project.kream.Model.Entity.*;
import com.project.kream.Model.Header;
import com.project.kream.Model.Pagination;
import com.project.kream.Model.enumclass.*;
import com.project.kream.Model.request.ProductApiRequest;
import com.project.kream.Model.response.*;
import com.project.kream.Repository.*;
import com.project.kream.Repository.Specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTagRepository productTagRepository;
    private final AddressRepository addressRepository;
    private final CardInfoRepository cardInfoRepository;
    private final AccountRepository accountRepository;
    private final SalesRepository salesRepository;
    private final ProimgService proimgService;
    private final ProductSpecification productSpecification;
    private final PurchaseRepository purchaseRepository;
    private final StyleimgRepository styleimgRepository;
    private final StyleReplyRepository styleReplyRepository;
    private final ProImgRepository proimgRepository;
    private final CartRepository cartRepository;
    private final TransactionRepository transactionRepository;
    private final StyleRepository styleRepository;



    public Header<Long> create(ProductApiRequest request, MultipartHttpServletRequest mutilRequest) {
        Product newproEntity = productRepository.save(request.toEntity());

        List<MultipartFile> fileList = mutilRequest.getFiles("files");
        String path = "C:\\Users\\jybeo\\Desktop\\final\\src\\main\\resources\\static\\lib\\product\\";

        for(MultipartFile mf : fileList){
            if(mf.getSize() >0){
                String originFileName = mf.getOriginalFilename();
                long fileSize = mf.getSize();
                String safeFile = path + originFileName;
                try{
                    mf.transferTo(new File(safeFile));
                    proimgService.create(originFileName, fileSize,  safeFile,  newproEntity.getId());
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return Header.OK(newproEntity.getId());
    }

    @Transactional
    public Header<Long> update(ProductApiRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new IllegalArgumentException("데이터가 없습니다."));
        product.update(request.getBrand(), request.getCollection(), request.getName(), request.getKorName(), request.getGender(), request.getRelease(), request.getReleasePrice(), request.getModelNumber(), request.getColor(), request.getCategory(), request.getSubCategory(), request.getPostStatus());
        return Header.OK(request.getId());
    }

    public Header<ProductApiResponse> upload(Long id, MultipartHttpServletRequest mutilRequest){
        List<MultipartFile> fileList = mutilRequest.getFiles("files");
        String path = "C:\\Users\\jybeo\\Desktop\\final\\src\\main\\resources\\static\\lib\\product\\";

        List<ProImg> proImgList = proimgRepository.findAllByProductId(id);
        proImgList.stream().map(proImg ->{
            File file = new File(proImg.getFilePath());
            if(file.exists()){
                file.delete();
            }
            proimgRepository.delete(proImg);
            return Header.OK();
        }).collect(Collectors.toList());

        for(MultipartFile mf : fileList){
            if(mf.getSize()>0) {
                String originFileName = mf.getOriginalFilename();
                long fileSize = mf.getSize();
                String safeFile = path + originFileName;
                try {
                    mf.transferTo(new File(safeFile));
                    proimgService.create(originFileName, fileSize, safeFile, id);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Header.OK();
    }

    // 관리자
    public Header<List<ProductAdminListApiResponse>> adminList(Pageable pageable){
        Page<Product> productList = productRepository.findAll(pageable);
        List<ProductAdminListApiResponse> productAdminListApiResponses = productList.stream()
                .map(ProductAdminListApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((productList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > productList.getTotalPages()) {
            endPage = productList.getTotalPages();
        }
        return Header.OK(productAdminListApiResponses, new Pagination(productList, startPage, endPage));
    }


    // 관리자 상세
    public Header<ProductAdminDetailApiResponse> adminDetail(Long id){
        Product product = productRepository.getById(id);
        List<ProImg> proImgList = product.getProImgList();
        List<ProimgPathApiResponse> proimgPathApiResponseList = proImgList.stream()
                .map(ProimgPathApiResponse::new).collect(Collectors.toList());
        return Header.OK(new ProductAdminDetailApiResponse(product, proimgPathApiResponseList));
    }

    // 사용자 리스트
    public Header<List<ProductUserListApiResponse>> userList(Header<ProductApiRequest> request){
        ProductApiRequest productApiRequest = request.getData();
        List<ProductApiResponse> productList = productRepository.ProductSearch(productApiRequest.getOrderFlag(), productApiRequest.getCategory(), productApiRequest.getSubCategory(), productApiRequest.getBrand(), productApiRequest.getBrand2(), productApiRequest.getBrand3(), productApiRequest.getBrand4(), productApiRequest.getBrand5(), productApiRequest.getBrand6(), productApiRequest.getBrand7(), productApiRequest.getBrand8(), productApiRequest.getBrand9(), productApiRequest.getBrand10(), productApiRequest.getBrand11(), productApiRequest.getBrand12(), productApiRequest.getBrand13(), productApiRequest.getBrand14(), productApiRequest.getBrand15(), productApiRequest.getBrand16(), productApiRequest.getBrand17(), productApiRequest.getBrand18(), productApiRequest.getBrand19(), productApiRequest.getBrand20(), productApiRequest.getGender(),  productApiRequest.getCollection(), productApiRequest.getCollection2(), productApiRequest.getCollection3(),productApiRequest.getCollection4(),productApiRequest.getCollection5(),productApiRequest.getCollection6(),productApiRequest.getCollection7(),productApiRequest.getCollection8(),productApiRequest.getCollection9(),productApiRequest.getCollection10(),
                productApiRequest.getSizeType(), productApiRequest.getSizeType2(), productApiRequest.getSizeType3(), productApiRequest.getSizeType4(), productApiRequest.getSizeType5(), productApiRequest.getSizeType6(), productApiRequest.getSizeType7(), productApiRequest.getSizeType8(), productApiRequest.getSizeType9(), productApiRequest.getSizeType10(), productApiRequest.getSizeType11(), productApiRequest.getPrice1(), productApiRequest.getPrice2(), productApiRequest.getPrice3(), productApiRequest.getPrice4(), productApiRequest.getPrice5(), productApiRequest.getPrice6());
        List<ProductUserListApiResponse> productApiResponseList = productList.stream()
                .map(product -> new ProductUserListApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());
        return Header.OK(productApiResponseList);
    }

    public Header<ProductUserListCartApiResponse> userListCart(Long ProductId, Long CustomerId){
        Product product = productRepository.getById(ProductId);
        List<ProSize> prosizeList = product.getProSizeList();
        List<ProductSizeApiResponse> productSizeApiResponseList = prosizeList.stream()
                .map(proSize -> new ProductSizeApiResponse(proSize.getSizeType(), cartRepository.countByProductIdAndSizeTypeAndCustomerId(ProductId, proSize.getSizeType(), CustomerId))).collect(Collectors.toList());
        return Header.OK(new ProductUserListCartApiResponse(product, productSizeApiResponseList));
    }

    public Header<ProductDetailApiResponse> userDetail(Long id){
        Product product = productRepository.getById(id);
        List<ProductSizeApiResponse> productSizeApiResponseList = product.getProSizeList().stream()
                .map(prosize -> new ProductSizeApiResponse(prosize.getSizeType(), salesRepository.findBySizeTypeAndProductId(prosize.getSizeType(), id))).collect(Collectors.toList());
        List<ProductImgApiResponse> productImgApiResponseList = product.getProImgList().stream()
                .map(ProductImgApiResponse::new).collect(Collectors.toList());

        List<ProductPurchaseSizeApiResponse> productPurchaseSizeApiResponseList = product.getPurchaseList().stream()
                .map(purchase -> new ProductPurchaseSizeApiResponse(purchaseRepository.findBySizeTypeAndPrice(purchase.getSizeType(), purchase.getPrice()), purchaseRepository.findByPriceAndSizeType(purchase.getPrice(), purchase.getSizeType()), purchaseRepository.countByPriceAndSizeType(purchase.getPrice(), purchase.getSizeType()))).collect(Collectors.toList());

        List<Transaction> transactionList = transactionRepository.findAllByProductIdOrderByRegdateAsc(id);
        List<ProductTransactionApiResponse> productTransactionApiResponseList = transactionList.stream()
                .map(ProductTransactionApiResponse::new).collect(Collectors.toList());

        List<Transaction> transactionListTop = transactionRepository.findTop2ByProductIdOrderByRegdateDesc(id);
        List<ProductTopTransactionApiResponse> productTopTransactionApiResponseList = transactionListTop.stream()
                .map(ProductTopTransactionApiResponse::new).collect(Collectors.toList());

        List<ProductSalesSizeApiResponse> productSalesSizeApiResponseList = product.getSalesList().stream()
                .map(sales -> new ProductSalesSizeApiResponse(salesRepository.findBySizeTypeAndPrice(sales.getSizeType(), sales.getPrice()), salesRepository.findByPriceAndSizeType(sales.getPrice(), sales.getSizeType()), salesRepository.countByPriceAndSizeType(sales.getPrice(), sales.getSizeType()))).collect(Collectors.toList());

        List<Product> productList = productRepository.findAllByCollectionAndPostStatus(product.getCollection(), PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList = productList.stream()
                .map(newproduct -> new ProductCollectionApiResponse(newproduct, salesRepository.findByProductId(newproduct.getId()))).collect(Collectors.toList());

        List<ProductTag> productTagList = productTagRepository.findAllByProductId(id);
        List<ProductStyleTagApiResponse> productStyleTagApiResponseList = productTagList.stream()
                .map(productTag -> {
                    List<ProductHashtagApiResponse> productHashtagApiResponses = productTag.getStyle().getStyleHashTagList().stream()
                            .map(hashTag -> new ProductHashtagApiResponse(hashTag.getHashTag().getTagName())).collect(Collectors.toList());
                    return new ProductStyleTagApiResponse(productTag.getStyle(), productTag.getStyle().getCustomer().getStyleCustomerList().get(0), productTag.getStyle().getCustomer(), styleimgRepository.countByStyleId(productTag.getStyle().getId()), styleReplyRepository.countByStyleId(productTag.getStyle().getId()), productHashtagApiResponses);
                }).collect(Collectors.toList());

        return Header.OK(new ProductDetailApiResponse(
                product,
                productSizeApiResponseList,
                productPurchaseSizeApiResponseList.stream().sorted(Comparator.comparingLong(ProductPurchaseSizeApiResponse::getPrice).reversed()).collect(Collectors.toList()),
                productSalesSizeApiResponseList.stream().sorted(Comparator.comparingLong(ProductSalesSizeApiResponse::getPrice)).collect(Collectors.toList()),
                productCollectionApiResponseList,
                productStyleTagApiResponseList,
                productTransactionApiResponseList,
                productImgApiResponseList,
                productTopTransactionApiResponseList
        ));
    }

    public Header<ProductBuyCheckApiResponse> buyCheck(Long id, String size){
        Product product = productRepository.getById(id);
        List<ProductBuySizeApiResponse> productBuySizeApiResponseList = product.getProSizeList().stream()
                .map(prosize -> new ProductBuySizeApiResponse(prosize.getSizeType(), salesRepository.findBySizeTypeAndProductId(prosize.getSizeType(), id))).collect(Collectors.toList());
        return Header.OK(new ProductBuyCheckApiResponse(product, size, productBuySizeApiResponseList));
    }

    public Header<ProductSellCheckApiResponse> sellCheck(Long id, String size){
        Product product = productRepository.getById(id);
        List<ProductSellSizeApiResponse> productSellSizeApiResponseList = product.getProSizeList().stream()
                .map(prosize -> new ProductSellSizeApiResponse(prosize.getSizeType(), purchaseRepository.findBySizeTypeAndProductId(prosize.getSizeType(), id))).collect(Collectors.toList());
        return Header.OK(new ProductSellCheckApiResponse(product, size, productSellSizeApiResponseList));
    }

    public Header<ProductBuyInfoApiResponse> buyInfo(Long id, String size){
        Product product = productRepository.getById(id);
        return Header.OK(new ProductBuyInfoApiResponse(product, size, salesRepository.findBySizeTypeAndProductId(size, id), purchaseRepository.findBySizeTypeAndProductId(size, id), salesRepository.findByProductIdAndSizeType(id, size)));
    }

    public Header<ProductSellInfoApiResponse> sellInfo(Long id, String size){
        Product product = productRepository.getById(id);
        return Header.OK(new ProductSellInfoApiResponse(product, size, salesRepository.findBySizeTypeAndProductId(size, id), purchaseRepository.findBySizeTypeAndProductId(size, id), purchaseRepository.findByProductIdAndSizeType(id, size)));
    }

    public Header<ProductBuyFinalApiResponse> buyFinal(Long productId, Long customerId, String size, Long price, Long date){
        Product product = productRepository.getById(productId);

        List<Address> addressList = addressRepository.findAllByCustomerId(customerId);
        List<ProductAddressApiResponse> productAddressApiResponseList = addressList.stream()
                .map(ProductAddressApiResponse::new).collect(Collectors.toList());

        List<CardInfo> cardInfoList = cardInfoRepository.findAllByCustomerId(customerId);
        List<ProductCardInfoApiResponse> productCardInfoApiResponses = cardInfoList.stream()
                .map(ProductCardInfoApiResponse::new).collect(Collectors.toList());

        return Header.OK(new ProductBuyFinalApiResponse(product, productId, size, price, productAddressApiResponseList, productCardInfoApiResponses, date));
    }

    public Header<ProductSellFinalResponse> sellFinal(Long productId, Long customerId, String size, Long price){
        Product product = productRepository.getById(productId);
        Optional<Account> account = accountRepository.findByCustomerId(customerId);
        Account newAccount = account.orElseGet(Account::new);

        List<Address> addressList = addressRepository.findAllByCustomerId(customerId);
        List<ProductAddressApiResponse> productAddressApiResponseList = addressList.stream()
                .map(ProductAddressApiResponse::new).collect(Collectors.toList());

        return Header.OK(new ProductSellFinalResponse(product, newAccount, size, price, productAddressApiResponseList));
    }

    public Header<ProductSellFinishApiResponse> sellFinish(Long productId){
        Product product = productRepository.getById(productId);

        ProductSellFinishApiResponse productSellFinishApiResponse = ProductSellFinishApiResponse.builder()
                .productOriginFileName(product.getProImgList().get(0).getOrigFileName())
                .build();
        return Header.OK(productSellFinishApiResponse);
    }

    public Header<ProductBuyFinishApiResponse> buyFinish(Long productId){
        Product product = productRepository.getById(productId);

        ProductBuyFinishApiResponse productBuyFinishApiResponse = ProductBuyFinishApiResponse.builder()
                .productOriginFileName(product.getProImgList().get(0).getOrigFileName())
                .build();
        return Header.OK(productBuyFinishApiResponse);
    }

    public Header delete(Long id){
        Optional<Product> productEntity = productRepository.findById(id);
        return productEntity.map(pro ->{
            productRepository.delete(pro);
            return Header.OK();
        }).orElseGet(() ->Header.ERROR("데이터없음"));
    }

    public Header<List<ProductSearchApiResponse>> dataList(Header<ProductApiRequest> request, Pageable pageable) throws ParseException {
        Page<Product> productList = productSpecification.searchProductList(request, pageable);

        List<ProductSearchApiResponse> productSearchApiResponseList = productList.stream()
                .map(ProductSearchApiResponse::new).collect(Collectors.toList());

        int countPage = 5;
        int startPage = ((productList.getNumber()) / countPage) * countPage + 1;
        int endPage = startPage + countPage - 1;
        if(endPage > productList.getTotalPages()) {
            endPage = productList.getTotalPages();
        }
        return Header.OK(productSearchApiResponseList, new Pagination(productList, startPage, endPage));
    }


    // 사용자 검색 큰 리스트
    // 수정필요
    public Header<List<ProductUserBigListApiResponse>> userSearchList(String keywords, Pageable pageable){
        Page<Product> productList = productRepository.findAllByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus.게시중,keywords, pageable);
        List<ProductUserBigListApiResponse> productUserBigListApiResponses = productList.stream()
                .map(product -> new ProductUserBigListApiResponse(product, salesRepository.min())).collect(Collectors.toList());
        return Header.OK(productUserBigListApiResponses, new Pagination(productList));
    }

    // 사용자 검색 리스트
    public Header<List<ProductSearchListApiResponse>> searchList(String keywords, Pageable pageable){
        Page<Product> productList = productRepository.findAllByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus.게시중, keywords, pageable);
        Long cnt = productRepository.countAllByByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus.게시중, keywords);
        List<ProductSearchListApiResponse> productSearchListApiResponseList = productList.stream()
                .map(ProductSearchListApiResponse::new).collect(Collectors.toList());

        return Header.OK(productSearchListApiResponseList, new Pagination(productList), cnt);
    }

    // 스타일 업로드 검색 리스트
    public Header<List<StyleSearchListApiResponse>> styleSearchList(String keywords, Pageable pageable){
        Page<Product> productList = productRepository.findAllByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus.게시중, keywords, pageable);
        Long cnt = productRepository.countAllByByBrandOrCollectionOrCategoryOrNameOrKorNameOrModelNumber(PostStatus.게시중, keywords);
        List<StyleSearchListApiResponse> styleSearchListApiResponseList = productList.stream()
                .map(product -> new StyleSearchListApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        return Header.OK(styleSearchListApiResponseList, new Pagination(productList), cnt);
    }

    public Header<ProductPostCntApiResponse> postCount(){
        return Header.OK(new ProductPostCntApiResponse(
                productRepository.ProductCnt(),
                productRepository.countByPostStatus(PostStatus.게시대기),
                productRepository.countByPostStatus(PostStatus.게시중),
                productRepository.countByPostStatus(PostStatus.게시중지),
                productRepository.countByPostStatus(PostStatus.게시종료)
        ));
    }

    public Header<MainApiResponse> main(){

        List<Product> droppedList =  productRepository.findTop12ByPostStatusOrderByRegdateDesc(PostStatus.게시중);
        List<ProductDroppedApiResponse> productDroppedApiResponseList = droppedList.stream()
                .map(product -> new ProductDroppedApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> UpcomingList =  productRepository.findTop12ByPostStatusOrderByReleaseAsc(PostStatus.게시대기);
        List<ProductUpcomingApiResponse> productUpcomingApiResponseList = UpcomingList.stream()
                .map(ProductUpcomingApiResponse::new).collect(Collectors.toList());

        List<ProductPopularApiResponse> productPopularApiResponseList = productRepository.Popular();

        List<Product> recommendList1 = productRepository.findTop12ByCollectionAndPostStatus("New Balance 993", PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList1 = recommendList1.stream()
                .map(product -> new ProductCollectionApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> recommendList2 = productRepository.findTop12ByCollectionAndPostStatus("Adidas Yeezy", PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList2 = recommendList2.stream()
                .map(product -> new ProductCollectionApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> recommendList3 = productRepository.findTop12ByCollectionAndPostStatus("Jordan1", PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList3 = recommendList3.stream()
                .map(product -> new ProductCollectionApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> recommendList4 = productRepository.findTop12ByCollectionAndPostStatus("Jordan3", PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList4 = recommendList4.stream()
                .map(product -> new ProductCollectionApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        // 럭셔리!
        List<Product> recommendList5 = productRepository.findTop12ByCollectionAndPostStatus("Nike Dunk", PostStatus.게시중);
        List<ProductCollectionApiResponse> productCollectionApiResponseList5 = recommendList5.stream()
                .map(product -> new ProductCollectionApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> brandList1 = productRepository.findTop12ByBrandAndPostStatus("Adidas", PostStatus.게시중);
        List<ProductBrandApiResponse> productBrandApiResponseList1 = brandList1.stream()
                .map(product -> new ProductBrandApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> brandList2 = productRepository.findTop12ByBrandAndPostStatus("Balenciaga", PostStatus.게시중);
        List<ProductBrandApiResponse> productBrandApiResponseList2 = brandList2.stream()
                .map(product -> new ProductBrandApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> brandList3 = productRepository.findTop12ByBrandAndPostStatus("Stone Island", PostStatus.게시중);
        List<ProductBrandApiResponse> productBrandApiResponseList3 = brandList3.stream()
                .map(product -> new ProductBrandApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());


        List<Style> stylePickList = styleRepository.findTop8ByOrderByHitDesc();
        List<StylePickApiResponse> stylePickApiResponseList = stylePickList.stream()
                .map(style -> new StylePickApiResponse(style, style.getCustomer().getStyleCustomerList().get(0), style.getCustomer())).collect(Collectors.toList());

        List<Style> hashTagList = styleRepository.HashTagList("shoeKREAM");
        List<StyleHashTagListApiResponse> styleHashTagListApiResponseList = hashTagList.stream()
                .map(style -> new StyleHashTagListApiResponse(style, style.getCustomer().getStyleCustomerList().get(0), style.getCustomer())).collect(Collectors.toList());

        List<Product> newLowers = productRepository.NewLowers(PurchaseStatus2.입찰중);
        List<ProductNewLowersApiResponse> productNewLowersApiResponseList = newLowers.stream()
                .map(product -> new ProductNewLowersApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        List<Product> newHighest = productRepository.NewHighest(SalesStatus2.입찰중);
        List<ProductNewHighestApiResponse> productNewHighestApiResponseList = newHighest.stream()
                .map(product -> new ProductNewHighestApiResponse(product, salesRepository.findByProductId(product.getId()))).collect(Collectors.toList());

        return Header.OK(new MainApiResponse(
                productDroppedApiResponseList,
                productUpcomingApiResponseList,
                productPopularApiResponseList,
                productCollectionApiResponseList1,
                productCollectionApiResponseList2,
                productCollectionApiResponseList3,
                productCollectionApiResponseList4,
                productCollectionApiResponseList5,
                productBrandApiResponseList1,
                productBrandApiResponseList2,
                productBrandApiResponseList3,
                stylePickApiResponseList,
                styleHashTagListApiResponseList,
                productNewLowersApiResponseList,
                productNewHighestApiResponseList
        ));
    }


}