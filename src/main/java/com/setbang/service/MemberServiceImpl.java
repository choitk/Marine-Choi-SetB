package com.setbang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setbang.dao.MemberDAO;
import com.setbang.domain.MemberVO;

@Service("MemberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO memberDAO;

    // 로그인
    public MemberVO getLogin(MemberVO vo) {
        return memberDAO.getLogin(vo);
    }
    
    // 회원가입
    public void getSignup(MemberVO vo) {
    	memberDAO.getSignup(vo);
    }
    
    // 세션아이디로 회원코드 가져오기
    public int getMemCodeBySessionId(String sessionId) {
        return memberDAO.getMemCodeBySessionId(sessionId);
    }
    
    // 세션아이디로 회원플랜등급 가져오기
    public String getMemPlanBySessionId(String sessionId) {
    	return memberDAO.getMemPlanBySessionId(sessionId);
    }
    
}
