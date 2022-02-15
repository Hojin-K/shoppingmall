package com.shop.myapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.ItemOption;

@Service
public class ItemOptionRepository {
    private final com.shop.myapp.repository.ItemOptionRepository itemOptionRepository;

    public ItemOptionRepository(@Autowired SqlSession sqlSession) {
        this.itemOptionRepository = sqlSession.getMapper(com.shop.myapp.repository.ItemOptionRepository.class);
    }

    public List<ItemOption> findByItemCode(String itemCode){
        return itemOptionRepository.findByItemCode(itemCode);
    }

    public Optional<ItemOption> findByOptionCode(String optionCode){
        return itemOptionRepository.findByOptionCode(optionCode);
    }

    public int insertItemOptions(List<ItemOption> options){
        return itemOptionRepository.insertItemOptions(options);
    }

    public int modifyItemOption(List<ItemOption> itemOptions){
        return itemOptionRepository.modifyItemOption(itemOptions);
    }

    public int deleteItemOption(List<String> optionCodes){
        return itemOptionRepository.deleteItemOption(optionCodes);
    }
}
