package com.shop.myapp.controller;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.service.ItemService;
import com.shop.myapp.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    private final HttpSession session;
    private final MemberService memberService;
    private final ItemService itemService;

    public SellerController(HttpSession session, MemberService memberService, ItemService itemService) {
        this.session = session;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    @GetMapping("/{memberId}")
    public String getSellerInfo(@PathVariable String memberId,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(value = "q",required = false) String search,
                                Model model){
        Member seller = memberService.getMember(memberId);
        Pagination pagination = itemService.getPaginationByPage(page);
        List<Item> items = memberService.getSellerItems(memberId,pagination,search);
        model.addAttribute("seller",seller);
        model.addAttribute("items",items);
        model.addAttribute("pagination",pagination);
        return "/seller/sellerView";
    }
}
