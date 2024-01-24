package com.office.library.admin.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminMemberDao {
//	mybatis로 대처함
//	@Autowired
//	private JdbcTemplate jdbcTemplate; 
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// mybatis
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate; 
	
	
	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isAdminMember(String a_m_id) {
		return (Integer)sqlSessionTemplate.selectOne("admin.selectcount", a_m_id) > 0;
//		String sql = "select count(*) from tbl_admin_member where a_m_id = ?";
//		int result =  jdbcTemplate.queryForObject(sql, Integer.class, a_m_id);		
//		
//		return result > 0;
	}

	public int insertAdminAccount(AdminMemberVo adminMemberVo) {
		String encodPsw = passwordEncoder.encode( adminMemberVo.getA_m_pw() );
		adminMemberVo.setA_m_pw(encodPsw);
		return sqlSessionTemplate.insert("admin.admininsert", adminMemberVo);
		
//		List<String> args = new ArrayList<String>();
//		String sql = "insert into tbl_admin_member(";		
//		if(adminMemberVo.getA_m_id().equals("Super admin")) {
//			sql += "a_m_approval, ";
//			args.add("1");
//		}
//		sql += "a_m_id,";
//		args.add(adminMemberVo.getA_m_id());		
//		sql += "a_m_pw,"; 
//		args.add( passwordEncoder.encode( adminMemberVo.getA_m_pw() ) );
//		sql += "a_m_name,"; 
//		args.add(adminMemberVo.getA_m_name());
//		sql += "a_m_gender,"; 
//		args.add(adminMemberVo.getA_m_gender());
//		sql += "a_m_part,";
//		args.add(adminMemberVo.getA_m_part());
//		sql += "a_m_position,";
//		args.add(adminMemberVo.getA_m_position());
//		sql += "a_m_mail,"; 
//		args.add(adminMemberVo.getA_m_mail());
//		sql += "a_m_phone,";
//		args.add(adminMemberVo.getA_m_phone());
//		sql += "a_m_reg_date, a_m_mod_date) ";
//		if(adminMemberVo.getA_m_id().equals("Super admin"))
//			sql += "values(?,?,?,?,?,?,?,?,?,now(),now())";
//		else
//			sql += "values(?,?,?,?,?,?,?,?,now(),now())";
//		int result = -1;
//		try {
//			result = jdbcTemplate.update(sql, args.toArray());
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}

	public AdminMemberVo selectAdmin(AdminMemberVo adminMemberVo) {
		AdminMemberVo vo 
		=sqlSessionTemplate.selectOne("admin.adminselect", adminMemberVo);
//		 패스워드 검증
		if( vo!=null && passwordEncoder.matches(adminMemberVo.getA_m_pw(),
				vo.getA_m_pw() ))
			return vo;
		else
			return null;
//		String sql = "select * from tbl_admin_member where a_m_id = ? and a_m_approval > 0";
//		List<AdminMemberVo> lists = new ArrayList<AdminMemberVo>();
//		lists = jdbcTemplate.query(sql,
//				(rs, rowNum) ->{
//					AdminMemberVo vo = new AdminMemberVo();
//					vo.setA_m_no(rs.getInt("a_m_no"));
//					vo.setA_m_approval(rs.getInt("a_m_approval"));
//					vo.setA_m_id(rs.getString("a_m_id"));
//					vo.setA_m_pw(rs.getString("a_m_pw"));
//					vo.setA_m_name(rs.getString("a_m_name"));
//					vo.setA_m_gender(rs.getString("a_m_gender"));
//					vo.setA_m_part(rs.getString("a_m_part"));
//					vo.setA_m_position(rs.getString("a_m_position"));
//					vo.setA_m_mail(rs.getString("a_m_mail"));
//					vo.setA_m_phone(rs.getString("a_m_phone"));
//					vo.setA_m_reg_date(rs.getString("a_m_reg_date"));
//					vo.setA_m_mod_date(rs.getString("a_m_mod_date"));
//					return vo;
//				}
//				, adminMemberVo.getA_m_id());
//		lists = jdbcTemplate.query(sql, new RowMapper<AdminMemberVo>() {
//			@Override
//			public AdminMemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
//				AdminMemberVo vo = new AdminMemberVo();
//				vo.setA_m_no(rs.getInt("a_m_no"));
//				vo.setA_m_approval(rs.getInt("a_m_approval"));
//				vo.setA_m_id(rs.getString("a_m_id"));
//				vo.setA_m_pw(rs.getString("a_m_pw"));
//				vo.setA_m_name(rs.getString("a_m_name"));
//				vo.setA_m_gender(rs.getString("a_m_gender"));
//				vo.setA_m_part(rs.getString("a_m_part"));
//				vo.setA_m_position(rs.getString("a_m_position"));
//				vo.setA_m_mail(rs.getString("a_m_mail"));
//				vo.setA_m_phone(rs.getString("a_m_phone"));
//				vo.setA_m_reg_date(rs.getString("a_m_reg_date"));
//				vo.setA_m_mod_date(rs.getString("a_m_mod_date"));
//				return vo;
//			}
//		}, adminMemberVo.getA_m_id() );
		// 패스워드 검증
//		if( lists!=null && lists.size() > 0 &&
//				passwordEncoder.matches(adminMemberVo.getA_m_pw(), lists.get(0).getA_m_pw() ))
//			return lists.get(0);
//		else
//			return null;
	}

	public List<AdminMemberVo> selectAdmin() {		
		return sqlSessionTemplate.selectList("admin.adminselectall");		
	}

	public void setAdminApproval(int a_m_no) {
		sqlSessionTemplate.update("admin.adminupdate", a_m_no);	
	}

	public void modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		sqlSessionTemplate.update("admin.adminaccountupdate", adminMemberVo);
		
	}

	public AdminMemberVo selectAdmin(String a_m_id, String a_m_name, String a_m_mail) {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("a_m_id", a_m_id);maps.put("a_m_name", a_m_name);maps.put("a_m_mail", a_m_mail);		
		return  sqlSessionTemplate.selectOne("admin.findpswselect", maps);		
	}
	// 패스워드 찾기를 통해 패스워드 업데이트
	public int updatePassword(String a_m_id, String newPassword) {
		Map<String, String> maps = new HashMap<String, String>();
		newPassword = passwordEncoder.encode(newPassword);
		maps.put("a_m_id", a_m_id);maps.put("newPassword", newPassword);
		return sqlSessionTemplate.update("admin.updatepassword", maps);		
	}

	public boolean selectById(String a_m_id) {
		return sqlSessionTemplate.selectOne("admin.selectById", a_m_id);
	}

	
	
}









