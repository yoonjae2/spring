package com.office.library.user.member;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMemberDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int createAccountConfirm(UserMemberVo userMemberVo) {
		userMemberVo.setU_m_pw( passwordEncoder.encode(userMemberVo.getU_m_pw()) );
		return sqlSessionTemplate.insert("user.insert", userMemberVo);
	}

	public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {
		String u_m_id = userMemberVo.getU_m_id();
		UserMemberVo vo = sqlSessionTemplate.selectOne("user.selectOne"
				, u_m_id);
		return passwordEncoder.matches(userMemberVo.getU_m_pw(),vo.getU_m_pw())? vo : null;
		
	}

	public int modifyAccountConfirm(UserMemberVo userMemberVo) {
		return sqlSessionTemplate.update("user.update", userMemberVo);		
	}

	public UserMemberVo selectUser(String u_m_id, String u_m_name, String u_m_mail) {
		UserMemberVo vo = new UserMemberVo();
		vo.setU_m_id(u_m_id); vo.setU_m_name(u_m_name); vo.setU_m_mail(u_m_mail);
		return sqlSessionTemplate.selectOne("user.findPswSelect", vo);
	}

	public int updatePassword(String u_m_id, String u_m_pw) {
		UserMemberVo vo = new UserMemberVo();
		u_m_pw = passwordEncoder.encode(u_m_pw);
		vo.setU_m_id(u_m_id); vo.setU_m_pw(u_m_pw);
		return sqlSessionTemplate.update("user.updatePsw", vo);
	}

	public boolean selectById(String u_m_id) {
		UserMemberVo vo =  sqlSessionTemplate.selectOne("user.selectOne", u_m_id);		
		return vo!=null;
	}

}
