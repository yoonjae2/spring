package com.office.library;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookVo;
import com.office.library.book.admin.AdminBookService;
import com.office.library.book.user.UserBookService;
import com.office.library.user.UserHomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	UserHomeService userHomeService;
	
	@Autowired
	UserBookService bookService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@GetMapping(value = {"","/","home"})
	public String home(Locale locale, Model model, @RequestParam(name="pageNum",defaultValue="1") int pageNum) {
		logger.info("Welcome home! The client locale is {}.", locale);		
			
			List<BookVo> bookVo = userHomeService.home((pageNum -1) * 10);
			
			model.addAttribute("bookVos", bookVo);
			
		     int pageSize = 7;
			
		    
			 int totalBookCount = userHomeService.totalBookCount(); 
			  
			 if(totalBookCount % pageSize != 0) { 
				 totalBookCount = totalBookCount / pageSize +1; 
			  }else {
			  totalBookCount = totalBookCount / pageSize; 
			  }
			  
			
			 model.addAttribute("bookVos", bookVo); 
			 model.addAttribute("totalBookCount",totalBookCount); 
			 model.addAttribute("currentPage", pageNum);
			 
			
		
		
		return "/user/home";
	}
	
}
