package com.office.library.admin.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.library.CommonService;

@Service
public class AdminMemberService {
	final static public int ADMIN_ACCOUNT_ALREADY_EXIST = 0;
	final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;

	@Autowired
	private AdminMemberDao adminMemberDao;
	
	@Autowired
	CommonService commonService;

	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());
		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberVo);
			if (result > 0)
				return ADMIN_ACCOUNT_CREATE_SUCCESS;
			else
				return ADMIN_ACCOUNT_CREATE_FAIL;
		} else
			return ADMIN_ACCOUNT_ALREADY_EXIST;

	}

	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);
		if (loginedAdminMemberVo != null)
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN SUCCESS!!");
		else
			System.out.println("[AdminMemberService] ADMIN MEMBER LOGIN FAIL!!");
		return loginedAdminMemberVo;
	}

	public List<AdminMemberVo> listupAdmin() {
		return adminMemberDao.selectAdmin();

	}

	public void setAdminApproval(int a_m_no) {
		adminMemberDao.setAdminApproval(a_m_no);

	}

	public void modifyAccountConfirm(AdminMemberVo adminMemberVo) {
		adminMemberDao.modifyAccountConfirm(adminMemberVo);

	}

	public int findPasswordConfirm(AdminMemberVo adminMemberVo) {
		AdminMemberVo selectedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo.getA_m_id(),
				adminMemberVo.getA_m_name(), adminMemberVo.getA_m_mail());

		int result = 0;

		if (selectedAdminMemberVo != null) {

			String newPassword = commonService.createNewPassword();
			result = adminMemberDao.updatePassword(adminMemberVo.getA_m_id(), newPassword);

			if (result > 0)
				commonService.sendNewPasswordByMail(adminMemberVo.getA_m_mail(), newPassword);
		}

		return result;
	}

	public boolean selectById(String a_m_id) {
		return adminMemberDao.selectById(a_m_id);
	}

	

}
