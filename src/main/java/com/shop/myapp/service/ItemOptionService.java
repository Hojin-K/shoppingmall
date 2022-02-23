package com.shop.myapp.service;

import com.shop.myapp.dto.ItemOption;
import com.shop.myapp.dto.OrderDetail;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class ItemOptionService {
    private final com.shop.myapp.repository.ItemOptionRepository itemOptionRepository;

    public ItemOptionService(@Autowired SqlSession sqlSession) {
        this.itemOptionRepository = sqlSession.getMapper(com.shop.myapp.repository.ItemOptionRepository.class);
    }

    public Optional<ItemOption> findByItemCode(String optionCode){
        return itemOptionRepository.findOneByItemCode(optionCode);
    }

    public Optional<ItemOption> findByOptionCode(String optionCode){
        return itemOptionRepository.findByOptionCode(optionCode);
    }

    public int insertItemOptions(List<ItemOption> options,String itemCode){
        options.removeAll(Collections.singletonList(null));
        for (ItemOption itemOption : options){
            itemOption.setItemCode(itemCode);
        }
        return itemOptionRepository.insertItemOptions(options);
    }

    public int modifyItemOption(List<ItemOption> itemOptions,String itemCode){
        int result = deleteByItemCode(itemCode);
        return itemOptionRepository.insertItemOptions(itemOptions);
    }

    public int modifyItemOptionAfterPay(List<OrderDetail> orderDetails){
        int result = 0;
        for (OrderDetail orderDetail : orderDetails){
            result += itemOptionRepository.modifyItemOptionStockByOptionCode(orderDetail);
        }
        return result;
    }

    public int deleteItemOption(String optionCode){
        return itemOptionRepository.deleteItemOption(optionCode);
    }
    public int deleteByItemCode(String itemCode){
        return itemOptionRepository.deleteByItemCode(itemCode);
    }
}
