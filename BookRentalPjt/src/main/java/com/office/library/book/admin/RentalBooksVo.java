package com.office.library.book.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalBooksVo {
	private String u_m_name;
	private String b_name;
	private String b_author;
	private String b_publisher;
	private String b_call_number;
	private String b_rantal_able;
	private String b_genre;
	private String b_no;
	private String rb_no;
	private String rb_end_date;
}
