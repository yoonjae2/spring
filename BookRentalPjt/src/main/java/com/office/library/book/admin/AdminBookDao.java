package com.office.library.book.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;

@Component
public class AdminBookDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public boolean isISBN(String b_isbn) {
		return (Integer)sqlSessionTemplate.selectOne("book.selectByIsbn", b_isbn) > 0;
	}

	public int insertBook(BookVo bookVo) {
		return sqlSessionTemplate.insert("book.insertbook", bookVo);
		
	}

	public List<BookVo> searchBookConfirm(String b_name) {
		return sqlSessionTemplate.selectList("book.searchbook", b_name);
		
	}

	public BookVo bookDetail(int b_no) {		
		return sqlSessionTemplate.selectOne("book.detail", b_no);
	}

	public int deleteBookConfirm(int b_no) {		
		return sqlSessionTemplate.delete("book.delete", b_no);
	}

	public int modifyBookConfirm(BookVo bookVo) {		
		return sqlSessionTemplate.update("book.modify", bookVo);
	}

	public List<RentalBooksVo> getRentalBooks() {
		return sqlSessionTemplate.selectList("book.select");
	}

	public int returnBookConfirm(RentalBooksVo rentalBooksVo) {
		return sqlSessionTemplate.update("rental.returnbook", rentalBooksVo);
	}

	public List<HopeBookVo> getHopeBooks() {
		return sqlSessionTemplate.selectList("book.selectHope");
	}

	public int updateRegister(int hb_no) {
		return sqlSessionTemplate.update("book.updateRegister", hb_no);
	}


	public List<BookVo> getAllBooks(int offset) {
		return sqlSessionTemplate.selectList("book.AllBook", offset);
	}

	public int totalBookCount() {
		return sqlSessionTemplate.selectOne("book.totalBookCount");
	}

}
