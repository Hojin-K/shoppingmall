package com.shop.myapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.ItemOption;
import com.shop.myapp.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;

    public ItemService(@Autowired SqlSession sqlSession) {
        this.itemRepository = sqlSession.getMapper(ItemRepository.class);
        this.itemOptionRepository = sqlSession.getMapper(ItemOptionRepository.class);
    }

    public Item getItem(String itemCode) {
        // itemCode 로 상품 조회
        Optional<Item> itemOptional = itemRepository.findByItemCode(itemCode);
        // 상품 null 체크
        Item item = itemOptional.orElseThrow(() -> new IllegalStateException("Not Found Item"));
        // 상품의 옵션 조회
        List<ItemOption> options = itemOptionRepository.findByItemCode(itemCode);
        // 상품에 옵션 넣기
        item.setOptions(options);
        return item;
    }

    public List<Item> getItems() {
        List<Item> items = itemRepository.findAll();
        return items;
    }

    public int createItem(Item item) {
        itemRepository.insertItem(item);
        return itemOptionRepository.insertItemOptions(item.getOptions());
    }

    public int deleteItem(String itemCode) {
        return itemRepository.deleteItem(itemCode);
    }

    public int updateItem(Item item) {
        itemRepository.updateItem(item);
        return itemOptionRepository.modifyItemOption(item.getOptions());
    }
}
