package com.shop.myapp.controller;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.ItemOption;
import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.service.FileService;
import com.shop.myapp.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private final FileService fileService;
    private final HttpSession session;

    public ItemController(ItemService itemService, FileService fileService, HttpSession session) {
        this.itemService = itemService;
        this.fileService = fileService;
        this.session = session;
    }

    @GetMapping("")
    public String getItems(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
        Pagination pagination = itemService.getPaginationByPage(page);
        List<Item> items = itemService.getItems(pagination);
        model.addAttribute("items", items);
        model.addAttribute("pagination", pagination);
        return "item/items";

    }

    @GetMapping("/{itemCode}")
    public String getItemDetail(@PathVariable String itemCode, Model model) {
        Item item = itemService.getItem(itemCode);
        model.addAttribute("item", item);
        return "item/item";
    }

    @GetMapping("/add")
    public String createItemForm() {

        return "/item/addItemForm";
    }

    @PostMapping("/add")
    public String createItem(Item item, MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request) throws IOException {
        String absolutePath = request.getServletContext().getRealPath("/resources/");
        Member member = (Member) request.getSession().getAttribute("member");
        item.setMemberId(member.getMemberId());
        Map<String, String> fileInfo = fileService.boardFileUpload(file, absolutePath);
        List<ItemOption> itemOptions = item.getItemOptions();
        for (ItemOption itemOption : itemOptions) {
            System.out.println(itemOption.getOptionName());
            System.out.println(itemOption.getOptionStock());
        }
        item.setItemImage(fileInfo.get("path"));
        int itemResult = itemService.createItem(item);
        if (itemResult != 0) {
            redirectAttributes.addAttribute("itemCode", item.getItemCode());
            return "redirect:/item/{itemCode}";
        } else {
            throw new IllegalStateException("아이템 등록 실패");
        }
    }

    @GetMapping("/{itemCode}/update")
    public String updateItemForm(@PathVariable String itemCode, Model model) {
        Member member = (Member) session.getAttribute("member");
        if (itemService.validateAccessToItem(itemCode, member)) {
            Item item = itemService.getItem(itemCode);
            model.addAttribute("item", item);
            return "/item/updateItemForm";
        }

        return "redirect:/item";
    }

    @PostMapping("/{itemCode}/update")
    public String updateItem(@PathVariable String itemCode, Item item, RedirectAttributes redirectAttributes) {
        Member member = (Member) session.getAttribute("member");
        if (itemService.validateAccessToItem(itemCode, member)) {
            itemService.updateItem(item);
            redirectAttributes.addAttribute("itemCode", itemCode);
            return "redirect:/item/{itemCode}";
        }
        return "redirect:/item";
    }

    @GetMapping("/{itemCode}/delete")
    public String deleteItem(@PathVariable String itemCode) {
        Member member = (Member) session.getAttribute("member");
        if (itemService.validateAccessToItem(itemCode, member)) {
            itemService.deleteItem(itemCode);
            return "redirect:/item";
        }
        return "redirect:/item";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam("q") String search, Model model) {
        Pagination pagination = itemService.getPaginationByPage(page);
        List<Item> items = itemService.search(search, pagination);
        model.addAttribute("pagination", pagination);
        model.addAttribute("items", items);
        return "item/items";
    }


}
