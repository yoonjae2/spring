package com.office.library;

import java.security.SecureRandom;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class CommonService {
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	
	public String createNewPassword() {
		char[] chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

		StringBuffer stringBuffer = new StringBuffer();
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.setSeed(new Date().getTime());

		int index = 0;
		int length = chars.length;
		for (int i = 0; i < 8; i++) {
			index = secureRandom.nextInt(length);

			if (index % 2 == 0)
				stringBuffer.append(String.valueOf(chars[index]).toUpperCase());
			else
				stringBuffer.append(String.valueOf(chars[index]).toLowerCase());

		}

		System.out.println("[AdminMemberService] NEW PASSWORD: " + stringBuffer.toString());
		return stringBuffer.toString();
	}
	public void sendNewPasswordByMail(String toMailAddr, String newPassword) {
		final MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");				
				mimeMessageHelper.setTo(toMailAddr);
				mimeMessageHelper.setSubject("[회신불가][한국 도서관] 새 비밀번호 안내입니다.");				
				mimeMessageHelper.setText("<h1>새 비밀번호</h1> : " + newPassword, true);

			}

		};
		javaMailSenderImpl.send(mimeMessagePreparator);

	}
}
