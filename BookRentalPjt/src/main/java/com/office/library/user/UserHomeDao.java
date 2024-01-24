package com.office.library.user;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.office.library.book.BookVo;

@Component
public class UserHomeDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;


	public List<BookVo> home() {
		return sqlSessionTemplate.selectList("userbook.home");
	}


	public List<BookVo> home(int offset) {
		return sqlSessionTemplate.selectList("userbook.home", offset);
	}


	public int totalBookCount() {
		return sqlSessionTemplate.selectOne("userbook.totalBookCount");
	}


}
