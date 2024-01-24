package com.office.library.book.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.UploadFileService;
import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController {
	private static final Logger logger = LoggerFactory.getLogger(AdminBookController.class);

	@Autowired
	AdminBookService bookService;

	@Autowired
	UploadFileService uploadFileService;

	// 도서등록 폼 화면
	@GetMapping("/registerBookForm")
	public String registerBookForm(Model model) {
		model.addAttribute("bookVo", new BookVo());
		return "/admin/book/register_book_form";
	}

	// 도서등록 프로세스
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo
					,@RequestParam("file") MultipartFile file
					, @RequestParam(name = "hb_no") String hb_no) {

		String nextPage = "admin/book/register_book_ok";
		// SAVE FILE
		String savedFileName = uploadFileService.upload(file);

		if (savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);

			if (result <= 0)
				nextPage = "admin/book/register_book_ng";

		} else {
			nextPage = "admin/book/register_book_ng";

		}
		
		// 입고요청도서 업데이트
		try {
			int hb_no_int = Integer.valueOf(hb_no);
			if(hb_no_int >0) {
				int result = bookService.updateRegister(hb_no_int);	
				if(result <= 0)
					nextPage = "admin/book/register_book_ng";	
			}			
		} catch (NumberFormatException e) {}
					
		return nextPage;
	}

	// 도서검색 프로세스
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(Model model, @RequestParam("b_name") String b_name) {

		List<BookVo> bookVos = bookService.searchBookConfirm(b_name);
		model.addAttribute("bookVos", bookVos);
		return "admin/book/search_book";
	}

	// 도서 상세정보 bookVo로 객체를 만들어서 넘겨줌
	@GetMapping("/bookDetail")
	public String bookDetail(Model model, @RequestParam("b_no") int b_no) {
		BookVo vo = bookService.bookDetail(b_no);
		model.addAttribute("bookVo", vo);
		return "admin/book/book_detail";
	}

	// 도서 상세정보에서 도서 삭제
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no) {
		String nextPage = "/admin/book/delete_book_ok";
		int result = bookService.deleteBookConfirm(b_no);
		if (result <= 0)
			nextPage = "/admin/book/delete_book_ng";
		return nextPage;
	}

	// 도서 상세 정보 수정 화면로드
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(Model model, @RequestParam("b_no") int b_no) {
		// pk값인 b_no를 이용해서 DB 조회를 통해 bookVo객체로 리턴 받는다
		BookVo bookVo = bookService.modifyBookForm(b_no);
		model.addAttribute("bookVo", bookVo);
		return "admin/book/modify_book_form";
	}

	// 도서 성제 정보 수정
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo, @RequestParam("file") MultipartFile file) {
		String nextPage = "/admin/book/modify_book_ok";

		// 기존에 파일이 있고 사용자가 업데이트할때 파일 선택안하면
		// 현재는 파일 없이 올라감
		// 기존 파일명을 셋팅해서 업데이트 해야 함

		// 기존 파일명을 사용하기 위해서 멀티파트는 선택하지 않으면 정보를 주지 않기때문에
		// db조회를 통해 기존파일 명을 가져온다

		String fileName = bookService.getFilename(bookVo);
		// 그런데 사용자가 새롭게 추가하면 업데이트함
		if (file.getSize() != 0)
			fileName = uploadFileService.upload(file);

		bookVo.setB_thumbnail(fileName);

		int result = bookService.modifyBookConfirm(bookVo);
		if (result <= 0)
			nextPage = "/admin/book/modify_book_ng";
		return nextPage;
	}

	// 관리자에서 대출도서목록
	@GetMapping("/getRentalBooks")
	public String getRentalBooks(Model model) {
		String nextPage = "admin/book/rental_books";
		
		
		List<RentalBooksVo> rentalBookVos = bookService.getRentalBooks();
		model.addAttribute("rentalBookVos", rentalBookVos);
		 
		
		return nextPage;
	}
	
	// 대출목록에서 반납버튼 클릭시
	@GetMapping("/returnBookConfirm")
	public String returnBookConfirm(RentalBooksVo rentalBooksVo) {
		String nextPage = "/admin/book/return_book_ok";
		int result = bookService.returnBookConfirm(rentalBooksVo);
		
		if(result <= 0)
			nextPage = "/admin/book/return_book_ng";
		
		return nextPage;
	}
	
	// 희망도서 목록
	@GetMapping("/getHopeBooks")
	public String getHopeBooks(Model model) {
		String nextPage = "/admin/book/hope_books";
		
		List<HopeBookVo> hopeBookVos = bookService.getHopeBooks();
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
	}
	
	// 희망도서 입고처리를 위한 목록
	// 화면에 있는 일부정보를 가지고 registerBookForm으로 전달.
	@GetMapping("/registerHopeBookForm")
	public String registerHopeBookForm (HopeBookVo hopeBookVo, Model model) {
		
		BookVo bookVo = new BookVo();
		bookVo.setB_name(hopeBookVo.getHb_name());
		bookVo.setB_author(hopeBookVo.getHb_author());
		bookVo.setB_publisher(hopeBookVo.getHb_publisher());
		bookVo.setB_publish_year(hopeBookVo.getHb_publish_year());
		
		model.addAttribute("bookVo", bookVo);
		model.addAttribute("hb_no", hopeBookVo.getHb_no());
		return "/admin/book/register_book_form";
	}
	
	@GetMapping("/getAllBooks")
	public String getAllBooks (Model model, @RequestParam(name="pageNum",defaultValue="1") int pageNum) {
		
		int pageSize = 10;
		
		List<BookVo> lists = bookService.getAllBooks((pageNum -1) * 10);
		
		int totalBookCount = bookService.totalBookCount();
		if(totalBookCount % pageSize != 0) {
			totalBookCount = totalBookCount / pageSize +1;
		}else {
			totalBookCount = totalBookCount / pageSize;
		}
		
		model.addAttribute("bookVos", lists);
		model.addAttribute("totalBookCount", totalBookCount);
		model.addAttribute("currentPage", pageNum);
		
		return "/admin/book/full_list_of_books";
	}

	@GetMapping("/getAllBooksTest")
	@ResponseBody
	public String getAllBooksTest () {
		return "success";
	}
	
	@GetMapping("/grape")
	public String grape () {
		return "/admin/member/login_ok";
	}
	
}




