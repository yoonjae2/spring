package com.office.library.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalBookVo {
    private int rb_no;          
    private int b_no;      
    private int u_m_no;
    private String rb_start_date; 
    private String rb_end_date;  
    private String rb_reg_date; 
    private String rb_mod_date;  
   
    
}
