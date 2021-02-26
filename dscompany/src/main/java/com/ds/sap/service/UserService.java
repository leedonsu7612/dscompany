package com.ds.sap.service;

import com.ds.sap.domain.LoginDTO;
import com.ds.sap.domain.UserVO;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public interface UserService {

    // 회원가입 처리
    public void register(UserVO userVO) throws Exception;

    // 회원정보 수정처리
    public void modifyUser(UserVO userVO) throws Exception;

    // 회원 정보
    public UserVO getUser(String uid) throws Exception;

    // 회원비밀번호 수정처리
    public void modifyPw(UserVO userVO) throws Exception;

    // 회원 프로필 사진 수정
    public void modifyUimage(String uid, String uimage) throws Exception;

    // 로그인 처리
    public UserVO login(LoginDTO loginDTO) throws Exception;

    // 로그인유지
    public void keepLogin(String uid, String sessionId, Date next) throws Exception;

    // Session Key 확인
    public UserVO checkLoginBefore(String value) throws Exception;
   


   //이메일발송
    public void sendEmail(UserVO userVO, String div) throws Exception;

    //비밀번호찾기
    public void findPw(HttpServletResponse resp, UserVO userVO) throws Exception;

}
