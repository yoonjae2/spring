package com.office.library.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.HomeController;

@Controller
@RequestMapping("/user")
public class UserHomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping({"","/"})
	public String home() {
		logger.info("user home");
		return "redirect:/";
	}
}
