package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.office.library.admin.member.AdminMemberVo;

@Controller
@RequestMapping("/user/member")
public class UserMemberController {
	@Autowired
	UserMemberService userMemberService;
	
	// 회원가입폼
	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		return "/user/member/create_account_form";
	}
	
	// 회원가입 실행
	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		String nextPage = "/user/member/create_account_ok";
		
		boolean isLogined = userMemberService.selectById(userMemberVo.getU_m_id());
		
		if(!isLogined) {
			int result = userMemberService.createAccountConfirm(userMemberVo);
			if(result <= 0)
				nextPage = "/user/member/create_account_ng";
		}
		else
			nextPage = "/user/member/create_account_ng";
		return nextPage;
	}
	
	//로그인 폼
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/member/login_form";
	}
	
	//로그인 처리
	@PostMapping("/loginConfirm")
	public String loginConfirm(UserMemberVo userMemberVo,
			HttpSession session ) {
		String nextPage = "/user/member/login_ok";
		UserMemberVo loginedUserMemberVo = userMemberService.loginConfirm(userMemberVo);
		if(loginedUserMemberVo == null)
			nextPage = "/user/member/login_ng";
		else {
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
		}
		return nextPage;
		
	}
	// 로그아웃
	@GetMapping("/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		session.invalidate();
		return "redirect:/user";
	}
	private UserMemberVo getLogined(HttpSession session) {
		UserMemberVo loginedUserMemberVo 
		=  (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		return loginedUserMemberVo; 
	}
	
	// 계정수정 화면 폼
	@GetMapping("/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		String nextPage = "/user/member/modify_account_form";
		
		if(getLogined(session)==null)
			nextPage = "redirect:/user/member/loginForm";
		return nextPage;		
	}
	// 계정수정 
	@SuppressWarnings("unused")
	@PostMapping("/modifyAccountConfirm")
	public String modifyAccountConfirm(HttpSession session, UserMemberVo userMemberVo) 
	{
		String nextPage = "/user/member/modify_account_ok";
		UserMemberVo loginedUserMemberVo = getLogined(session);
		if(loginedUserMemberVo == null)
			return "redirect:/user/member/loginForm";
		
		userMemberVo.setU_m_id( loginedUserMemberVo.getU_m_id() );
		userMemberVo.setU_m_pw( loginedUserMemberVo.getU_m_pw() );
		
		int result =  userMemberService.modifyAccountConfirm(userMemberVo);
		if(result<=0)
			nextPage = "/user/member/modify_account_ng";
		else 
			session.setAttribute("loginedUserMemberVo", userMemberVo);			
		
		return nextPage;
	}
	// 비밀번호 재 발급
// 비밀번호 찾기 폼
	@GetMapping("findPasswordForm")
	public String findPasswordForm() {
		return "/user/member/find_password_form";
	}
	// 비밀번호 찾기 프로세스
	@PostMapping("/findPasswordConfirm")
	public String findPasswordConfirm(UserMemberVo userMemberVo) {
		System.out.println("[AdminMemberController] findPasswordConfirm()");
		
		String nextPage = "/user/member/find_password_ok";
		
		int result = userMemberService.findPasswordConfirm(userMemberVo);
		
		if (result <= 0)
			nextPage = "/user/member/find_password_ng";
		
		return nextPage;		
	}
	
}
