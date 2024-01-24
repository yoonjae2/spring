package com.office.library.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.book.BookVo;

@Service
public class UserHomeService {
	@Autowired
	UserHomeDao userHomeDao;


	public List<BookVo> home(int offset) {
		return userHomeDao.home(offset);
	}


	public int totalBookCount() {
		return userHomeDao.totalBookCount();
	}

	
}
