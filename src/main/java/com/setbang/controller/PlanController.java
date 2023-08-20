package com.setbang.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.setbang.domain.CardVO;
import com.setbang.domain.MemberVO;
import com.setbang.domain.PlanVO;
import com.setbang.service.CardService;
import com.setbang.service.MemberService;
import com.setbang.service.PlanService;


@Controller
public class PlanController {
	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private PlanService planService;
	
	
	
	// 서비스 플랜 결제
	@RequestMapping(value = "planPayment.do", method = RequestMethod.POST)
	public String planPayment(HttpSession session,
	                          @RequestParam("plan_code") int planCode,
	                          @RequestParam("card_code") int cardCode,
	                          @RequestParam("card_easypw") int cardEasypw) {
	        int currentEasypw = cardService.getEasypwByCardcode(cardCode);
	        
	        if (currentEasypw != 0 && currentEasypw == cardEasypw) {
	            PlanVO plan = new PlanVO();
	            plan.setPlan_code(planCode);
	            plan.setCard_code(cardCode);
	            planService.planPayment(plan); 
	            
	            return "redirect:/planApply.do";
	        }
	      // task - 결제가 안됐을때, 비밀번호가 틀렸을때 알림창 띄워야함
	    return "redirect:/planApply.do";
	}

	
    // 서비스 플랜 결제 페이지로 이동
    @RequestMapping(value = "planApply.do", method = RequestMethod.GET)
    public String planApply(HttpSession session, Model model) {
    	
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId != null) {
            int memCode = memberService.getMemCodeBySessionId(sessionId);
            if (memCode != 0) {
                // 등록된 카드 불러오기
                CardVO card = new CardVO();
                card.setMem_code(memCode);
                List<CardVO> cardList = cardService.getCardList(card);
                model.addAttribute("cardList", cardList);
                
                return "/plan/planApply";
            }
    	}
    	return "redirect:/loginPage.do";
    }
 
		
}