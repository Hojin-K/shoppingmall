package com.shop.myapp.service;

<<<<<<< HEAD:src/main/java/com/shop/myapp/service/ItemOptionRepository.java
import java.util.List;
import java.util.Optional;

=======
import com.shop.myapp.dto.ItemOption;
>>>>>>> 5d9f3017b5e3e2e88859e30c7395c2edb6ff4124:src/main/java/com/shop/myapp/service/ItemOptionService.java
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.myapp.dto.ItemOption;

@Service
public class ItemOptionService {
    private final com.shop.myapp.repository.ItemOptionRepository itemOptionRepository;

    public ItemOptionService(@Autowired SqlSession sqlSession) {
        this.itemOptionRepository = sqlSession.getMapper(com.shop.myapp.repository.ItemOptionRepository.class);
    }

    public Optional<ItemOption> findByItemCode(String itemCode){
        return itemOptionRepository.findOneByItemCode(itemCode);
    }

    public Optional<ItemOption> findByOptionCode(String optionCode){
        return itemOptionRepository.findByOptionCode(optionCode);
    }

    public int insertItemOptions(List<ItemOption> options,String itemCode){
        for (ItemOption itemOption : options){
            itemOption.setItemCode(itemCode);
        }
        return itemOptionRepository.insertItemOptions(options);
    }

    public int modifyItemOption(List<ItemOption> itemOptions){
        return itemOptionRepository.modifyItemOption(itemOptions);
    }

    public int deleteItemOption(List<String> optionCodes){
        return itemOptionRepository.deleteItemOption(optionCodes);
    }
}
