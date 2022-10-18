package com.bookcatalog.bookcatalogv2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {


	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home (Model model) {
		model.addAttribute("name","Alliano");
		log.info("request form /home has trigered by user");
		return "home";
	}
}
