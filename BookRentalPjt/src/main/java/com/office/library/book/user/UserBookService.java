package com.office.library.book.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.user.member.UserMemberVo;

@Service
public class UserBookService {
	@Autowired
	UserBookDao userBookDao;
	
	public List<BookVo> searchBookConfirm(String b_name) {
		return userBookDao.searchBookConfirm(b_name);
	}

	public BookVo bookDetail(String b_no) {
		return userBookDao.bookDetail(b_no);
	}

	public int rentalBookConfirm(int b_no) {
		return userBookDao.rentalBookConfirm(b_no);
	}

	public int rentalEnd(String b_no) {
		return userBookDao.rentalEnd(b_no);
	}

	public int insertRentalBook(int b_no, int u_m_no) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("b_no", b_no);
		map.put("u_m_no", u_m_no);
		return userBookDao.insertRentalBook(map);
		
	}

	public List<Map<String, String>> enterBookshelf(int u_m_no) {
		return userBookDao.enterBookshelf(u_m_no);
	}

	public List<Map<String, String>> listupRentalBookHistory(int u_m_no) {
		return userBookDao.listupRentalBookHistory(u_m_no);
	}

	public int requestHopeBookForm(HopeBookVo hopeBookVo) {
		return userBookDao.requestHopeBookForm(hopeBookVo);
	}

	public List<HopeBookVo> listupRequestHopeBook(int u_m_no) {
		System.out.println("[BookService] listupRequestHopeBook()");
		
		return userBookDao.selectRequestHopeBooks(u_m_no);
	}

	


}
