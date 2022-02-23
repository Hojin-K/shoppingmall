package com.shop.myapp.repository;


import com.shop.myapp.dto.ItemOption;
import com.shop.myapp.dto.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemOptionRepository {
    Optional<ItemOption> findByOptionCode(String optionCode);
    Optional<ItemOption> findOneByItemCode(@Param("optionCode") String optionCode);
    int insertItemOptions(List<ItemOption> itemOptions);
    int deleteItemOption(String optionCode);
    int deleteByItemCode(String itemCode);
    int modifyItemOption(List<ItemOption> itemOptions);
    int modifyItemOptionStockByOptionCode(OrderDetail orderDetail);
}
