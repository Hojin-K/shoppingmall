package com.shop.myapp.controller;

import com.shop.myapp.dto.Item;
import com.shop.myapp.dto.Member;
import com.shop.myapp.dto.Pagination;
import com.shop.myapp.service.ItemService;
import com.shop.myapp.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{memberId}/update")
    public String updateSellerInfoForm(@PathVariable String memberId,Model model){
        Member seller = memberService.getMember(memberId);
        model.addAttribute("seller",seller);
        return "/modal/sellerModal";
    }

    @PostMapping("/{memberId}/update")
    public String updateSellerInfo(@PathVariable String memberId, Member member, RedirectAttributes redirectAttributes){
        member.setMemberId(memberId);
        int i = memberService.updateSellerInfo(member);
        redirectAttributes.addAttribute("memberId",memberId);
        return "redirect:/seller/{memberId}";
    }
}
