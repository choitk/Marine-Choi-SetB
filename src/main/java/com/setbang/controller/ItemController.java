package com.setbang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.setbang.domain.ItemVO;
import com.setbang.service.ItemService;
import com.setbang.service.SupportService;

@Controller
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SupportService supportService;
	
	//물품 신청 등록 화면 조회
	@RequestMapping(value = "itemApply.do")
	public String viewItemApply(HttpSession session, ItemVO vo, HttpServletRequest request, Model model) {
		logger.info("ItemController.viewItemApply 접근");

		return "/item/itemApply";
	}
	

	// 물품 카테고리로 카테고리별 물품을 찾아와서 List에 담기
    @RequestMapping(value = "selectItemCat.do", method = RequestMethod.POST)
    @ResponseBody
    public List seletctItem(@RequestParam("icatCode") String icatCode, HttpServletRequest request, Model model, ItemVO vo) {
		
    	System.out.println("icatCode : " + icatCode);
    	
    	int icatcode = Integer.parseInt(icatCode);
    	System.out.println("icatcode : " + icatcode);
    	
    	//i_cat_code를 가지고 물품리스트 가져오기
    	List<ItemVO> Itemlist = itemService.selectItem(icatcode);
    	System.out.println("list : " + Itemlist);
    	model.addAttribute("Itemlist", Itemlist);
    	
    	for (ItemVO item : Itemlist) {
    	    System.out.println("i_code: " + item.getI_code());
    	    System.out.println("i_name: " + item.getI_name());
    	    // 다른 속성들도 출력 가능
    	}
    	
    	return Itemlist;
	}
    
    
    // i_code로 i_name을 찾아와서 List에 담기
    @RequestMapping(value = "selectIname.do", method = RequestMethod.POST)
    @ResponseBody 
    public List selectIname(@RequestParam("itemCode") String itemCode, Model model, ItemVO vo) {
    	
    	System.out.println("itemCode : " + itemCode);
    	int itemcode = Integer.parseInt(itemCode);
    	
    	List<ItemVO> ItemNamelist = itemService.selectItemName(itemcode);
    	System.out.println("ItemName : " + ItemNamelist);
    	
    	return ItemNamelist; 
    }


    // 주문내역 insert
    @RequestMapping(value = "/orderInsert.do", method = RequestMethod.POST)
    @ResponseBody     
    public String orderInsert(@RequestBody List<ItemVO> orderData, HttpSession session) {
       
       // 세션에서 회원정보 가져오기
       String sessionId = (String) session.getAttribute("sessionId");
       // 회원아이디로 회원코드 가져오기
       int memCode = itemService.getMemCodeBySessionId(sessionId);
       System.out.println("memCode : " + memCode);
       // vo에 memCode 저장~
      // vo.setMem_code(memCode);
       
       for (ItemVO vo : orderData) {
          vo.setMem_code(memCode);
          System.out.println("memcode : " + vo.getMem_code());
          System.out.println("iCode : " + vo.getI_code());
          //vo.setI_unit_amount(1);
          System.out.println("iAmountUnit : " + vo.getI_unit_amount());
          // vo.setMem_code(memCode);
           itemService.insetOrder(vo);
       }
       
//       System.out.println("i_code : " + vo.getI_code());
//       System.out.println("i_unit_amount : " + vo.getI_amount());
//       System.out.println("memCode : " + vo.getMem_code());
       //itemService.insetOrder(vo);
       
       return "redirect:/itemlist.do";
    }
}
