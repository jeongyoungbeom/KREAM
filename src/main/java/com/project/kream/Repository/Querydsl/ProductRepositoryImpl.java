package com.project.kream.Repository.Querydsl;

import com.project.kream.Model.Entity.Product;
import com.project.kream.Model.enumclass.*;
import com.project.kream.Model.response.ProductApiResponse;
import com.project.kream.Model.response.ProductPopularApiResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.project.kream.Model.Entity.QProImg.proImg;
import static com.project.kream.Model.Entity.QProSize.proSize;
import static com.project.kream.Model.Entity.QProduct.product;
import static com.project.kream.Model.Entity.QPurchase.purchase;
import static com.project.kream.Model.Entity.QSales.sales;
import static com.project.kream.Model.Entity.QTransaction.transaction;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductApiResponse> ProductSearch(String orderFlag, Category category, SubCategory subCategory, String brand1, String brand2, String brand3, String brand4, String brand5, String brand6, String brand7, String brand8, String brand9, String brand10, String brand11, String brand12, String brand13, String brand14, String brand15, String brand16, String brand17, String brand18, String brand19, String brand20, String gender, String collection1, String collection2,
                                                  String collection3, String collection4, String collection5, String collection6, String collection7, String collection8, String collection9, String collection10, String size1, String size2, String size3, String size4, String size5, String size6, String size7, String size8, String size9, String size10, String size11, Long price1, Long price2, Long price3, Long price4, Long price5, Long price6){

        NumberPath<Long> amount = Expressions.numberPath(Long.class, "amount"); // select id, (select count(*) from memeber) as amount from friend;
        NumberPath<Long> mini = Expressions.numberPath(Long.class, "mini");
        NumberPath<Long> maxx = Expressions.numberPath(Long.class, "maxx");
        NumberPath<Long> premium = Expressions.numberPath(Long.class, "premium");
        StringPath origFileName = Expressions.stringPath("origFileName");

        List<ProductApiResponse> result = null;

        if(orderFlag == null){
            orderFlag = "popular";
        }

        if(orderFlag.equals("popular")) {
            if (price1 == null && price2 == null && price3 == null && price4 == null && price5 == null && price6 == null){
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(amount.desc())
                        .fetch();
            }
            else{
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.id.in(JPAExpressions.select(sales.product.id).from(sales).groupBy(sales.product.id).having(priceLoe(price1), priceBetween(price2, price3), priceBetween(price4, price5), priceGoe(price6))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(amount.desc())
                        .fetch();
            }
        }else if(orderFlag.equals("lowest")){
            if (price1 == null && price2 == null && price3 == null && price4 == null && price5 == null && price6 == null) {
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(mini.asc())
                        .fetch();
            }else{
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.id.in(JPAExpressions.select(sales.product.id).from(sales).groupBy(sales.product.id).having(priceLoe(price1), priceBetween(price2, price3), priceBetween(price4, price5), priceGoe(price6))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(mini.asc())
                        .fetch();
            }
        }else if(orderFlag.equals("highest")){
            if (price1 == null && price2 == null && price3 == null && price4 == null && price5 == null && price6 == null) {
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(maxx.desc())
                        .fetch();
            }else{
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.id.in(JPAExpressions.select(sales.product.id).from(sales).groupBy(sales.product.id).having(priceLoe(price1), priceBetween(price2, price3), priceBetween(price4, price5), priceGoe(price6))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(maxx.desc())
                        .fetch();
            }

        }else if(orderFlag.equals("pricepremium")){
            if (price1 == null && price2 == null && price3 == null && price4 == null && price5 == null && price6 == null) {
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(premium.desc())
                        .fetch();
            }else{
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.id.in(JPAExpressions.select(sales.product.id).from(sales).groupBy(sales.product.id).having(priceLoe(price1), priceBetween(price2, price3), priceBetween(price4, price5), priceGoe(price6))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(premium.desc())
                        .fetch();
            }

        }else if(orderFlag.equals("date_released")){
            if (price1 == null && price2 == null && price3 == null && price4 == null && price5 == null && price6 == null) {
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(product.release.desc())
                        .fetch();
            }else{
                result = queryFactory.select(Projections.constructor(ProductApiResponse.class, product.id, product.brand, product.collection, product.category, product.subCategory, product.korName, product.name, product.gender, product.release, product.releasePrice, product.modelNumber, product.color, product.postStatus, product.regdate,
                                ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(transaction.product.id.eq(product.id)), amount),
                                ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                                ExpressionUtils.as(JPAExpressions.select(purchase.price.max()).from(purchase).where(purchase.product.id.eq(product.id)), maxx),
                                ExpressionUtils.as(JPAExpressions.select(transaction.price.subtract(product.releasePrice).divide(product.releasePrice).multiply(100)).from(transaction).where(transaction.regdate.eq(JPAExpressions.select(transaction.regdate.max()).from(transaction).where(transaction.product.id.eq(product.id))).and(transaction.product.id.eq(product.id))), premium),
                                ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName)))
                        .from(product)
                        .where(eqCategory(category), eqSubcategory(subCategory),
                                product.brand.isNotNull().andAnyOf(eqbrand(brand1), eqbrand(brand2), eqbrand(brand3), eqbrand(brand4), eqbrand(brand5), eqbrand(brand6), eqbrand(brand7), eqbrand(brand8), eqbrand(brand9), eqbrand(brand10), eqbrand(brand11), eqbrand(brand12), eqbrand(brand13), eqbrand(brand14), eqbrand(brand15), eqbrand(brand16), eqbrand(brand17), eqbrand(brand18), eqbrand(brand19), eqbrand(brand20)),
                                eqGender(gender),
                                product.collection.isNotNull().andAnyOf(eqCollection(collection1), eqCollection(collection2), eqCollection(collection3), eqCollection(collection4), eqCollection(collection5), eqCollection(collection6), eqCollection(collection7), eqCollection(collection8), eqCollection(collection9), eqCollection(collection10)),
                                product.id.in(JPAExpressions.selectDistinct(proSize.product.id).from(proSize).where(eqSize(size1), eqSize(size2), eqSize(size3), eqSize(size4), eqSize(size5), eqSize(size6), eqSize(size7), eqSize(size8), eqSize(size9), eqSize(size10), eqSize(size11))),
                                product.id.in(JPAExpressions.select(sales.product.id).from(sales).groupBy(sales.product.id).having(priceLoe(price1), priceBetween(price2, price3), priceBetween(price4, price5), priceGoe(price6))),
                                product.postStatus.eq(PostStatus.게시중))
                        .orderBy(product.release.desc())
                        .fetch();

            }
        }
        return result;
    }

    @Override
    public List<ProductPopularApiResponse> Popular() {
        NumberPath<Long> amount = Expressions.numberPath(Long.class, "amount");
        StringPath origFileName = Expressions.stringPath("origFileName");
        NumberPath<Long> mini = Expressions.numberPath(Long.class, "mini");

        List<ProductPopularApiResponse> popular = queryFactory.select(Projections.constructor(ProductPopularApiResponse.class, product.id,
                        ExpressionUtils.as(JPAExpressions.select(proImg.origFileName).from(proImg).where(proImg.id.eq(JPAExpressions.select(proImg.id.min()).from(proImg).where(proImg.product.id.eq(product.id)))), origFileName),
                        product.brand, product.name,
                        ExpressionUtils.as(JPAExpressions.select(sales.price.min()).from(sales).where(sales.product.id.eq(product.id)), mini),
                        ExpressionUtils.as(JPAExpressions.select(transaction.count()).from(transaction).where(product.id.eq(transaction.product.id)), amount)
                ))
                .from(product)
                .where(product.postStatus.eq(PostStatus.게시중))
                .orderBy(amount.desc())
                .limit(12)
                .fetch();
        return popular;
    }

    @Override
    public List<Product> NewLowers(PurchaseStatus2 purchaseStatus2) {
        List<Product> newLowers = queryFactory.selectFrom(product)
                .where(
                        product.id.in(JPAExpressions.select(purchase.product.id).from(purchase).where(purchase.status2.eq(purchaseStatus2)))
                ).orderBy(product.regdate.desc())
                .where(product.postStatus.eq(PostStatus.게시중))
                .limit(12)
                .fetch();
        return newLowers;
    }

    @Override
    public List<Product> NewHighest(SalesStatus2 salesStatus2) {
        List<Product> newHighest = queryFactory.selectFrom(product)
                .where(
                        product.id.in(JPAExpressions.select(sales.product.id).from(sales).where(sales.status2.eq(salesStatus2)))
                ).orderBy(product.regdate.desc())
                .where(product.postStatus.eq(PostStatus.게시중))
                .limit(12)
                .fetch();
        return newHighest;
    }

    private BooleanExpression eqCategory(Category category){
        if(ObjectUtils.isEmpty(category)){
            return null;
        }
        return product.category.eq(category);
    }

    private BooleanExpression eqSubcategory(SubCategory subCategory){
        if(ObjectUtils.isEmpty(subCategory)){
            return null;
        }
        return product.subCategory.eq(subCategory);
    }

    private BooleanExpression eqbrand(String brand){
        if(ObjectUtils.isEmpty(brand)){
            return null;
        }

        return product.brand.eq(brand);
    }

    private BooleanExpression eqGender(String gender){
        if(StringUtils.isEmpty(gender)){
            return null;
        }
        return product.gender.eq(gender);
    }

    private BooleanExpression eqCollection(String collection){
        if(StringUtils.isEmpty(collection)){
            return null;
        }
        return product.collection.eq(collection);
    }

    private BooleanExpression eqSize(String size){
        if (StringUtils.isEmpty(size)){

            return null;
        }
        System.out.println(size);
        return proSize.sizeType.eq(size);
    }

    private BooleanExpression priceLoe(Long price) {
        if(ObjectUtils.isEmpty(price)){
            return null;
        }
        return sales.price.min().loe(price);
    }

    private BooleanExpression priceBetween(Long price1, Long price2){
        if(ObjectUtils.isEmpty(price1) || ObjectUtils.isEmpty(price2)){
            return null;
        }
        return sales.price.min().between(price1, price2);
    }

    private BooleanExpression priceGoe(Long price){
        if(ObjectUtils.isEmpty(price)){
            return null;
        }
        return sales.price.min().goe(price);
    }
}
