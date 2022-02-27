package com.shop.myapp.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.myapp.dto.Item;
import com.shop.myapp.service.ItemService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private final ItemService itemService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	public HomeController(ItemService itemService) {
		this.itemService = itemService;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		List<Item> items = itemService.findNewItems();

		model.addAttribute("items",items);
		
		return "item/home";
	}
	
}
