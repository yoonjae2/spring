package com.office.library.book.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/book/user")
public class UserBookController {

	@Autowired
	UserBookService userBookService;

	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(Model model, @RequestParam("b_name") String b_name) {

		List<BookVo> bookVos = userBookService.searchBookConfirm(b_name);
		model.addAttribute("bookVos", bookVos);

		return "/user/book/search_book";
	}

	// 검색된 도서 상세보기
	@GetMapping("/bookDetail")
	public String bookDetail(Model model, @RequestParam("b_no") String b_no) {

		BookVo bookVo = userBookService.bookDetail(b_no);
		model.addAttribute("bookVo", bookVo);

		return "/user/book/book_detail";
	}

	   // 도서 대출하기
	   @GetMapping("/rentalBookConfirm")
	   public String rentalBookConfirm(HttpSession session,
	         @RequestParam("b_no") int b_no) {
	      int result = userBookService.rentalBookConfirm(b_no);
	      if (result <=0)
	         return "/user/book/rental_book_ng";
	      
	      UserMemberVo vo 
	      	= (UserMemberVo) session.getAttribute("loginedUserMemberVo");
	      
	      result = userBookService.insertRentalBook(b_no,vo.getU_m_no());
	      if(result <=0)
	         return "/user/book/rental_book_ng";
	      return "/user/book/rental_book_ok";
	   }


	// 도서반납
	@GetMapping("/rentalEnd")
	public String rentalBan(String b_no) {
		String nextPage = "/user/book/rental_book_ok";
		
		int result = userBookService.rentalEnd(b_no);

		if (result <= 0) {
			nextPage = "/user/book/rental_book_ng";
		}

		return nextPage;
	}
	
	//나의 책장
	@GetMapping("/enterBookshelf")
	public String enterBookshelf(Model model, HttpSession session) {
		
		UserMemberVo vo 
			= (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<Map<String, String>> rentalBookVos = 
				userBookService.enterBookshelf(vo.getU_m_no());
		
		model.addAttribute("rentalBookVos",rentalBookVos);
		
		
		return "/user/book/bookshelf";
	}
	
	// 전체 대출 이력
	@GetMapping("/listupRentalBookHistory")
	public String listupRentalBookHistory (Model model, HttpSession session) {
		
		UserMemberVo vo 
		= (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<Map<String, String>> rentalBookVos 
			= userBookService.listupRentalBookHistory(vo.getU_m_no());
		
		model.addAttribute("rentalBookVos",rentalBookVos);
		
		return "/user/book/rental_book_history";
	}
	
	// 희망도서 요청
	@GetMapping("/requestHopeBookForm")
	public String requestHopeBookForm() {
		return "/user/book/request_hope_book_form";
	}
	
	@PostMapping("/requestHopeBookForm")
	public String requestHopeBookForm(HttpSession session, HopeBookVo hopeBookVo) {
		String nextPage = "/user/book/request_hope_book_ok";
		int u_m_no = ((UserMemberVo)session.getAttribute("loginedUserMemberVo")).getU_m_no();
		hopeBookVo.setU_m_no(u_m_no);
		int result = userBookService.requestHopeBookForm(hopeBookVo);
		if(result <= 0)
			nextPage = "/user/book/request_hope_book_ng";
		return nextPage;
	}
	
	/*
	 * 희망 도서 요청 목록
	 */
//	@RequestMapping(value = "/listupRequestHopeBook", method = RequestMethod.GET)
	@GetMapping("/listupRequestHopeBook")
	public String listupRequestHopeBook(HttpSession session, Model model) {
		System.out.println("[UserBookController] listupRequestHopeBook()");
		
		String nextPage = "user/book/list_hope_book";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<HopeBookVo> hopeBookVos = 
				userBookService.listupRequestHopeBook(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
		
	}


}
