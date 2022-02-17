package com.shop.myapp.repository;


import com.shop.myapp.dto.ItemOption;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemOptionRepository {
    Optional<ItemOption> findByOptionCode(String optionCode);
    Optional<ItemOption> findOneByItemCode(String itemCode);
    int insertItemOptions(List<ItemOption> itemOptions);
    int deleteItemOption(List<String> itemCode);
    int modifyItemOption(List<ItemOption> itemOptions);
}
