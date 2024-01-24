package com.office.library.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminHomeController {
	@GetMapping("/admin")
	public String home() {
		System.out.println("[AdminHomeController] home()");

		String nextPage = "admin/home";

		return nextPage;

	}
}
