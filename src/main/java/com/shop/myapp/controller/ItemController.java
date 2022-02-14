package com.shop.myapp.controller;

import com.shop.myapp.dto.Item;
import com.shop.myapp.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping()
    public String getItemsForm(){
        return "";
    }

    @GetMapping("/getItem")
    @ResponseBody
    public ResponseEntity<Object> getItems(/* {페이징 처리},{검색 필터}*/){
        List<Item> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }
    @GetMapping("/{itemCode}")
    public String getItemDetail(@PathVariable String itemCode, Model model){
        Item item = itemService.getItem(itemCode);
        model.addAttribute("item",item);
        return "";
    }

    @GetMapping("/add")
    public String createItemForm(){
        return "";
    }

    @PostMapping("/add")
    public String createItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes){
        int itemResult = itemService.createItem(item);
        if (result != 0){
            redirectAttributes.addAttribute("itemCode",item.getItemCode());
            return "redirect:/item/{itemCode}";
        } else {
            throw new IllegalStateException("아이템 등록 실패");
        }
    }

    @GetMapping("/{itemCode}/update")
    public String updateItemForm(@PathVariable String itemCode,Model model){
        Item item = itemService.getItem(itemCode);
        model.addAttribute("item",item);
        return "";
    }

    @PostMapping("/{itemCode}/update")
    public String updateItem(@PathVariable String itemCode, Item item, RedirectAttributes redirectAttributes){

        itemService.updateItem(item);
        redirectAttributes.addAttribute("itemCode",itemCode);
        return "redirect:/item/{itemCode}";
    }

    @PostMapping("/{itemCode}/delete")
    public String deleteItem(@PathVariable String itemCode){
        itemService.deleteItem(itemCode);
        return "redirect:/item";
    }


}
