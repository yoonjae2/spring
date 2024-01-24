package com.office.library.book.user;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;
import com.office.library.book.HopeBookVo;
import com.office.library.book.RentalBookVo;
import com.office.library.user.member.UserMemberVo;

@Component
public class UserBookDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<BookVo> searchBookConfirm(String b_name) {
		return sqlSessionTemplate.selectList("userbook.searchbook", b_name);
	}

	public BookVo bookDetail(String b_no) {
		return sqlSessionTemplate.selectOne("userbook.selectDetail", b_no);
	}

	public int rentalBookConfirm(int b_no) {
		return sqlSessionTemplate.update("userbook.rental", b_no);
	}

	public int rentalEnd(String b_no) {
		return sqlSessionTemplate.update("userbook.bannab", b_no);
	}


	public int insertRentalBook(Map<String, Integer> map) {
		return sqlSessionTemplate.insert("rental.insert", map);
	}

	public List<Map<String, String>> enterBookshelf(int u_m_no) {
		return sqlSessionTemplate.selectList("rental.select", u_m_no);
	}


	public List<Map<String, String>> listupRentalBookHistory(int u_m_no) {
		return sqlSessionTemplate.selectList("rental.history", u_m_no);
	}

	public int requestHopeBookForm(HopeBookVo hopeBookVo) {
		return sqlSessionTemplate.insert("userbook.hopebook", hopeBookVo);
	}

	public List<HopeBookVo> selectRequestHopeBooks(int u_m_no) {
		return sqlSessionTemplate.selectList("userbook.hopebookselect", u_m_no);
	}


}
