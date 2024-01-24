package com.office.library.book.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.UploadFileService;
import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;

@Service
public class AdminBookService {
	final static public int BOOK_ISBN_ALREADY_EXIST = 0; // 이미 등록된 도서
	final static public int BOOK_REGISTER_SUCCESS = 1; // 신규 도서 등록 성공
	final static public int BOOK_REGISTER_FAIL = -1; // 신규 도서 등록 실패

	@Autowired
	AdminBookDao bookDao;	 

	public int registerBookConfirm(BookVo bookVo) {
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());

		if (!isISBN) {
			int result = bookDao.insertBook(bookVo);

			if (result > 0)
				return BOOK_REGISTER_SUCCESS;

			else
				return BOOK_REGISTER_FAIL;

		} else {
			return BOOK_ISBN_ALREADY_EXIST;

		}
	}

	public List<BookVo> searchBookConfirm(String b_name) {
		return bookDao.searchBookConfirm(b_name);		
	}

	public BookVo bookDetail(int b_no) {		
		return bookDao.bookDetail(b_no) ;
	}

	public int deleteBookConfirm(int b_no) {		
		return bookDao.deleteBookConfirm(b_no);
	}

	// 책정보 수정
	public BookVo modifyBookForm(int b_no) {		
		return bookDao.bookDetail(b_no);
	}

	public int modifyBookConfirm(BookVo bookVo) {	
		
		return bookDao.modifyBookConfirm(bookVo);
	}

	public String getFilename(BookVo bookVo) {
		return bookDetail(bookVo.getB_no()).getB_thumbnail();
		
	}

	public List<RentalBooksVo> getRentalBooks() {
		return bookDao.getRentalBooks();
	}

	public int returnBookConfirm(RentalBooksVo rentalBooksVo) {
		return bookDao.returnBookConfirm(rentalBooksVo);
	}

	public List<HopeBookVo> getHopeBooks() {
		return bookDao.getHopeBooks();
	}

	public int updateRegister(int hb_no) {
		return bookDao.updateRegister(hb_no);
	}

	public List<BookVo> getAllBooks(int offset) {
		return bookDao.getAllBooks(offset);
	}

	public int totalBookCount() {
		return bookDao.totalBookCount();
	}



	

}
