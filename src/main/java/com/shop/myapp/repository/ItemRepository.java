package com.shop.myapp.repository;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Pagination;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemRepository {
    List<Item> findAll(Pagination pagination);
    Optional<Item> findByItemCode(String itemCode);
    int insertItem(Item item);
    int deleteItem(String itemCode);
    int updateItem(Item item);
    int getItemListCnt();
}
