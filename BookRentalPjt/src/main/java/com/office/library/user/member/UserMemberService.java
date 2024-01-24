package com.office.library.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.CommonService;
import com.office.library.admin.member.AdminMemberVo;

@Service
public class UserMemberService {
	@Autowired
	UserMemberDao userMemberDao;
	@Autowired
	CommonService commonService;
	
	public int createAccountConfirm(UserMemberVo userMemberVo) {		
		return userMemberDao.createAccountConfirm(userMemberVo);
	}

	public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {		
		return userMemberDao.loginConfirm(userMemberVo);
	}

	public int modifyAccountConfirm(UserMemberVo userMemberVo) {
		return userMemberDao.modifyAccountConfirm(userMemberVo);		
	}

	public int findPasswordConfirm(UserMemberVo userMemberVo) {
		
		UserMemberVo selectedUserMemberVo = userMemberDao.selectUser(userMemberVo.getU_m_id(),
				userMemberVo.getU_m_name(), userMemberVo.getU_m_mail());

		int result = 0;

		if (selectedUserMemberVo != null) {

			String newPassword = commonService.createNewPassword();
			result = userMemberDao.updatePassword(userMemberVo.getU_m_id(), newPassword);

			if (result > 0)
				commonService.sendNewPasswordByMail(userMemberVo.getU_m_mail(), newPassword);
		}

		return result;
	}

	public boolean selectById(String u_m_id) {
		return userMemberDao.selectById(u_m_id);		
	}

}
