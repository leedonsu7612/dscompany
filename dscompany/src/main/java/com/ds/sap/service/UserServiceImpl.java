package com.ds.sap.service;

import com.ds.sap.domain.LoginDTO;
import com.ds.sap.domain.Manager;
import com.ds.sap.domain.UserVO;
import com.ds.sap.persistence.UserDAO;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDAO userDAO;
    
  
    // 회원가입 처리
    @Override
    public void register(UserVO userVO) throws Exception {
        userDAO.register(userVO);
    }

    // 회원 비밀번호
    @Override
    public UserVO getUser(String uid) throws Exception {
        return userDAO.getUser(uid);
    }

    // 회원정보 수정처리
    @Override
    public void modifyUser(UserVO userVO) throws Exception {
        userDAO.updateUser(userVO);
    }

    // 회원비밀번호 수정처리
    @Override
    public void modifyPw(UserVO userVO) throws Exception {
        userDAO.updatePw(userVO);
    }

    // 회원 프로필 사진 수정
    @Override
    public void modifyUimage(String uid, String uimage) throws Exception {
        userDAO.updateUimage(uid, uimage);
    }

    // 로그인 처리
    @Transactional
    @Override
    public UserVO login(LoginDTO loginDTO) throws Exception {
        userDAO.updateLoginDate(loginDTO.getUid());
        return userDAO.login(loginDTO);
    }

    // 로그인 유지
    @Override
    public void keepLogin(String uid, String sessionId, Date next) throws Exception {
        userDAO.keepLogin(uid, sessionId, next);
    }

    // Session Key 확인
    @Override
    public UserVO checkLoginBefore(String value) throws Exception {
        return userDAO.checkUserWithSessionKey(value);
    }

	@Override
	public void sendEmail(UserVO userVO, String div) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; //네이버 이용시 smtp.naver.com
		String hostSMTPid = "서버 이메일 주소(보내는 사람 이메일 주소)";
		String hostSMTPpwd = "서버 이메일 비번(보내는 사람 이메일 비번)";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "보내는 사람 이메일주소(받는 사람 이메일에 표시됨)";
		String fromName = "프로젝트이름 또는 보내는 사람 이름";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "베프마켓 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += userVO.getUid() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += userVO.getUpw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = userVO.getUemail();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); //네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
		
	}

	@Override
	public void findPw(HttpServletResponse resp, UserVO userVO) throws Exception {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			userVO.setUpw(pw);
			// 비밀번호 변경
			userDAO.updatePw(userVO);
			// 비밀번호 변경 메일 발송
			sendEmail(userVO, "findpw");

			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}


}
