package com.shop.myapp.repository;

import com.shop.myapp.dto.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemRepository {
    List<Item> findAll(/* {페이징 처리 DTO}, {검색 필터 DTO}*/);
    Optional<Item> findByItemCode(String itemCode);
    int insertItem(Item item);
    int deleteItem(String itemCode);
    int updateItem(Item item);
}
