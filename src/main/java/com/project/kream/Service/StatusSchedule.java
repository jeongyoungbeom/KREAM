package com.project.kream.Service;

import com.project.kream.Model.Entity.Purchase;
import com.project.kream.Model.Entity.Sales;
import com.project.kream.Model.enumclass.PurchaseStatus1;
import com.project.kream.Model.enumclass.SalesStatus1;
import com.project.kream.Repository.PurchaseRepository;
import com.project.kream.Repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatusSchedule {
    private final PurchaseRepository purchaseRepository;
    private final SalesRepository salesRepository;


    @Scheduled(cron = "0 0 0 * * *")    // 매일 0시 0분 0초에 실행
//    @Scheduled(fixedDelay=3000)
    public void cronJobSch(){
        List<Purchase> purchases = purchaseRepository.findByStatus1(PurchaseStatus1.구매입찰);
        List<Sales> sales = salesRepository.findByStatus1(SalesStatus1.판매입찰);
        LocalDate today = LocalDate.now();

        for(Purchase i : purchases) {
            LocalDate purchaseDate = i.getRegdate().toLocalDate();
            long days = ChronoUnit.DAYS.between(purchaseDate,today);
            if (i.getPeriod() < days) { //오늘 날짜-등록일 이 입찰기간보다 크면 마감일이 지난것이므로 상태 변경
                i.setStatus1(PurchaseStatus1.종료);
                purchaseRepository.save(i);
            }else{
                System.out.println("구매입찰 최신화 완료");
            }
        }

        for(Sales i : sales){
            LocalDate salesDate = i.getRegdate().toLocalDate();
            long days = ChronoUnit.DAYS.between(salesDate,today);
            if(i.getPeriod()<days){
                i.setStatus1(SalesStatus1.종료);
                salesRepository.save(i);
            }else{
                System.out.println("판매입찰 최신화 완료");
            }
        }


    }
}
