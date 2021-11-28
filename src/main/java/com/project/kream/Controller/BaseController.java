package com.project.kream.Controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@AllArgsConstructor
@Controller
public class BaseController {


    // 공지사항 리스트
    @GetMapping("/pages/notice/postmange")
    public String ad_notice_postmange() {
        return "pages/ad_notice_postmange.html";
    }

    //대시보드
    @GetMapping("/pages")
    public String ad_dashboard() {
        return "pages/ad_dashboard.html";
    }

    //미결제확인
    @GetMapping("/pages/order/paycheck")
    public String ad_order_paycheck() {
        return "pages/ad_order_paycheck.html";
    }

    //구매
    @GetMapping("/pages/order/search")
    public String ad_order_search() {
        return "pages/ad_order_search.html";
    }

    //상품문의
    @GetMapping("/pages/inquire/product")
    public String ad_inquire_product() {
        return "pages/ad_inquire_product.html";
    }

    //고객문의
    @GetMapping("/pages/inquire/customer")
    public String ad_inquire_customer() {
        return "pages/ad_inquire_customer.html";
    }

    //블랙리스트
    @GetMapping("/pages/member/leave")
    public String ad_member_leave() {
        return "pages/ad_member_leave.html";
    }

    //상품등록
    @GetMapping("/pages/product/regist")
    public String ad_product_regist() {
        return "pages/ad_product_regist.html";
    }

    //상품조회수정
    @GetMapping("/pages/product/check")
    public String ad_product_check() {
        return "pages/ad_product_check.html";
    }

    //상품등록 디테일
    @GetMapping("/pages/product/detail/{id}")
    public String ad_product_detail(@PathVariable("id") Long id){
        return "pages/ad_product_detail.html";
    }

    //전체회원
    @GetMapping("/pages/member/all")
    public String ad_member_all() {
        return "pages/ad_member_all.html";
    }

    //탈퇴회원
    @GetMapping("/pages/member/withdrawal")
    public String ad_member_withdrawal() {
        return "pages/ad_member_withdrawal.html";
    }

    //배송현황
    @GetMapping("/pages/order/delivery")
    public String ad_order_delivery() {
        return "pages/ad_order_delivery.html";
    }

    //판매
    @GetMapping("/pages/order/sales")
    public String ad_order_sales() {
        return "pages/ad_order_sales.html";
    }

    //글생성
    @GetMapping("/pages/content/manage")
    public String ad_content_manage() {
        return "pages/ad_content_manage.html";
    }



    //고객문의상세
    @GetMapping("/pages/customer_info/{id}")
    public String customer_info(@PathVariable("id") Long id){
        return "pages/ad_inquire_customer_info.html";
    }

    //상품문의상세
    @GetMapping("/pages/product_info/{id}")
    public String product_info(@PathVariable("id") Long id){
        return "pages/ad_inqurie_product_info.html";
    }

    //고객문의답변
    @GetMapping("/pages/customer_edit/{id}")
    public String customer_edit(@PathVariable("id") Long id){
        return "pages/ad_inquire_customer_edit.html";
    }

    //상품문의답변
    @GetMapping("/pages/product_edit/{id}")
    public String product_edit(@PathVariable("id") Long id){
        return "pages/ad_inqurie_product_edit.html";
    }

    //게시글수정
    @GetMapping("/pages/postm_info/{idx}")
    public String postm_info(@PathVariable("idx") Long idx){
        return "pages/ad_notice_postmange_info.html";
    }

    //게시글 상세 추가
    @GetMapping("/pages/postm_detail/{idx}")
    public String postmange_detail(@PathVariable("idx") Long idx){ return "pages/ad_notice_postmange_detail.html"; }

    //탈퇴회원정보
    @GetMapping("/pages/black_info/{id}")
    public String black_info(@PathVariable("id") Long id){
        return "pages/ad_member_black_info.html";
    }

    //구매정보
    @GetMapping("/pages/search_info/{id}")
    public String search_info(@PathVariable("id") Long id){
        return "pages/ad_order_search_info.html";
    }

    //판매정보
    @GetMapping("/pages/sales_info/{id}")
    public String sales_info(@PathVariable("id") Long id){ return "pages/ad_order_sales_info.html"; }

    //배송정보
    @GetMapping("/pages/delivery_info/{id}")
    public String delivery_info(@PathVariable("id") Long id){
        return "pages/ad_order_delivery_info.html";
    }

    //상품수정
    @GetMapping("/pages/product/edit/{id}")
    public String edit(@PathVariable("id") Long id){
        return "pages/ad_product_edit.html";
    }

    //회원정보
    @GetMapping("/pages/info/{id}")
    public String info(@PathVariable("id") Long id){
        return "pages/ad_member_info.html";
    }

    //블랙리스트 등록
    @GetMapping("/pages/black/{id}")
    public String black(@PathVariable("id") Long id){ return "pages/ad_member_black.html"; }

    //등급수정
    @GetMapping("/pages/rank/{id}")
    public String rank(@PathVariable("id") Long id){ return "pages/ad_member_rank.html"; }





    // 메인 페이지
    @GetMapping("/")
    public String mainpage(){
        return "/board/mainpage.html";
    }

    // 어바웃 페이지
    @GetMapping("/about")
    public String aboutpage() { return "/board/about.html"; }

    // 로그인 페이지
    @GetMapping("/login")
    public String login(){
        return "/board/loginpage.html";
    }

    // 회원가입 페이지
    @GetMapping("/join")
    public String userRegister(){
        return "/board/joinpage.html";
    }

    // 아이디 찾기 페이지
    @GetMapping("/login/find_email")
    public String findEmail(){
        return "/board/login_find_email.html";
    }

    // 비밀번호 찾기 페이지
    @GetMapping("/login/find_password")
    public String findPassword(){
        return "/board/login_find_password.html";
    }

    // 마이페이지
    @GetMapping("/my")
    public String mypage() { return "/board/mypage.html"; }

    // 마이페이지 구매 내역
    @GetMapping("/my/buying")
    public String myBuying() { return "/board/mypage_buying.html"; }

    // 마이페이지 구매 내역 상세페이지(품목의 id 값 넘김)
    @RequestMapping(value = "/my/buying/{no}", method = {RequestMethod.GET})
    public String myBuyingDetail(@PathVariable("no")Long no, Model model){
        model.addAttribute("id", no);
        return "board/mypage_buying_detail.html";
    }

    // 마이페이지 판매 내역
    @GetMapping("/my/selling")
    public String mySelling() { return "/board/mypage_selling.html"; }

    // 마이페이지 구매 내역 상세페이지
    @RequestMapping(value = "/my/selling/{no}", method = {RequestMethod.GET})
    public String mySellingDetail(@PathVariable("no")Long no, Model model){
        model.addAttribute("id", no);
        return "board/mypage_selling_detail.html";
    }

    // 마이페이지 관심상품
    @GetMapping("/my/wish")
    public String myWish() { return "/board/mypage_wish.html"; }

    // 마이페이지 프로필 수정
    @GetMapping("/my/profile")
    public String myProfile() { return "/board/mypage_profile.html"; }

    // 마이페이지 탈퇴 페이지
    @GetMapping("/my/withdrawal")
    public String withdrawalpage() { return "/board/mypage_withdrawal.html"; }

    // 마이페이지 주소록
    @GetMapping("/my/address")
    public String myAddress() { return "/board/mypage_address.html"; }

    // 마이페이지 결제 정보
    @GetMapping("/my/payment")
    public String myPayment() { return "/board/mypage_payment.html"; }

    // 마이페이지 판매 정산 계좌
    @GetMapping("/my/account")
    public String myAccount() { return "/board/mypage_account.html"; }

    ///////////////////////////////////////////////////////////

    // 공지사항 리스트
    @GetMapping("/notice")
    public String NoticeList(){ return "board/notice.html"; }

    // 공지사항 디테일 페이지
    @GetMapping("/notice_24/{id}")
    public String Notice(@PathVariable("id") Long id){ return "board/notice_24.html"; }

    // 검수기준 리스트
    @GetMapping("/auth_polic")
    public String InspectionList(){ return "board/auth_polic.html"; }

    // 검수기준 의류
    @GetMapping("/auth_polic/str")
    public String streetwear(){return "board/auth_polic_streetwear.html";}

    // 검수기준 패션잡화
    @GetMapping("/auth_polic/acc")
    public String acc(){return "board/auth_polic_accessories.html";}

    // 검수기준 테크
    @GetMapping("/auth_polic/ele")
    public String elect() {return "board/auth_polic_electronics.html";}

    // 검수기준 라이프
    @GetMapping("/auth_polic/life")
    public String life() { return "board/auth_polic_life.html";}

    // 자주 묻는 질문
    @GetMapping("/faq")
    public String QnaList(){ return "board/faq.html";}

    // 자주 묻는 질문(이용정책)
    @GetMapping("/faq/policy")
    public String policy() { return "board/faq_policy.html";}

    // 자주 묻는 질문(공통)
    @GetMapping("/faq/common")
    public String common(){ return "board/faq_common.html";}

    // 자주 묻는 질문(구매)
    @GetMapping("/faq/buying")
    public String buying(){return "board/faq_buying.html";}

    // 자주 묻는 질문(판매)
    @GetMapping("/faq/selling")
    public String selling() {return "board/faq_selling.html";}

    ////////////////////////////////////////////////////////

    // 스타일 인기
    @GetMapping("/social/trending")
    public String style() {return "board/style.html";}

    // 스타일 hashtag
    @GetMapping("/social/tags/{tagName}")
    public String hashtag(@PathVariable("tagName") String tagName) {return "board/hashtag.html";}

    // 스타일 popular
    @GetMapping("/social/popular")
    public String popular() {return "board/popular.html";}

    // 스타일 following_empty
    @GetMapping("/social/following/empty")
    public String f_empty() {return "board/following_empty.html";}

    // 스타일 following
    @GetMapping("/social/following")
    public String following() {return "board/following.html";}

    // 스타일 프로필 (my/empty)
    @GetMapping("/social/myprofile/empty")
    public String m_empty() {return "board/myprofile_empty.html";}

    // 스타일 프로필 (my)
    @GetMapping("/social/myprofile")
    public String myprofile() {return "board/myprofile.html";}

    // 스타일 프로필 (my) 수정하기
    @GetMapping("/social/myprofile/edit")
    public String styleProfileEdit() { return "board/style_profile_edit.html"; }

    // 스타일 프로필
    @GetMapping("/social/profile/{id}")
    public String profile(@PathVariable("id") Long id) {return "board/profile.html";}

    // 스타일 게시글 업로드
    @GetMapping("/social/upload")
    public String upload() {return "board/upload.html";}

    // 스타일 게시글 수정
    @GetMapping("/social/edit/{id}")
    public String styleEdit(@PathVariable("id") Long id) {return "board/edit.html";}

    // 스타일 업로드, 수정, 삭제..?
    @GetMapping("/social/upload_edit/delete")
    public String u_delete() {return "board/upload_edit_delete.html";}

    ///////////////////////////////////////////////////////

    // 샵 search 페이지
    @GetMapping("/search")
    public String adminList() { return "board/shop_search.html"; }

    // 샵 product 페이지
    @GetMapping("/product/{id}")
    public String adminRegist(@PathVariable("id") Long id) { return "board/shop_product.html"; }

    @GetMapping("/kream/buying/{id}/{size}")
    public String userBuying(@PathVariable("id") Long id, @PathVariable("size") String size) {
        return "board/Buying.html";
    }

    @GetMapping("/kream/selling/{id}/{size}")
    public String userSelling(@PathVariable("id") Long id, @PathVariable("size") String size) {
        return "board/Selling.html";
    }

    @GetMapping("/kream/buycheck/{id}/{size}")
    public String userBuyCheck(@PathVariable("id") Long id, @PathVariable("size") String size) {
        return "board/BuyCheck.html";
    }

    @GetMapping("/kream/sellcheck/{id}/{size}")
    public String userSellCheck(@PathVariable("id") Long id, @PathVariable("size") String size) {
        return "board/SellCheck.html";
    }

    @GetMapping("/kream/buyfinish/{id}/{price}/{date}")
    public String userBuynowFinish(@PathVariable("id") Long id,  @PathVariable("price") Long price, @PathVariable("date") Long date) {
        return "board/BuynowFinish.html";
    }

    @GetMapping("/kream/sellfinish/{productId}/{price}/{date}")
    public String userSellFinish(@PathVariable("productId") Long productId,@PathVariable("price") Long price,@PathVariable("date") Long date) {
        return "board/SellFinish.html";
    }

    @GetMapping("/kream/product_list")
    public String adminRegist() {
        return "board/shop_register.html";
    }

    @GetMapping("/kream/buyfinal/{id}/{size}/{price}/{date}/{checkId}")
    public String userBuyFinal(@PathVariable("id")Long id, @PathVariable("size")String size, @PathVariable("price") Long price, @PathVariable Long date,@PathVariable("checkId") Long checkId){
        return "board/BuyFinal.html";
    }

    @GetMapping("/kream/sellfinal/{productId}/{size}/{price}/{date}/{checkId}")
    public String userSellFinal(@PathVariable("productId") Long productId,@PathVariable("size") String size,@PathVariable("price") Long price,@PathVariable("date") Long date,@PathVariable("checkId") Long checkId) {
        return "board/SellFinal.html";
    }



}
