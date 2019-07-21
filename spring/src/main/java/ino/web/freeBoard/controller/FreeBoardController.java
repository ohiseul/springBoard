package ino.web.freeBoard.controller;

import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.dto.Page;
import ino.web.freeBoard.service.FreeBoardService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
	@RequestMapping("/main.ino")
	public String list(Model model, Page page) throws Exception {
			Map<String, Object> result = freeBoardService.freeBoardList(page);
			System.out.println(page.getKeyword());
			System.out.println(page.getBegin());
			System.out.println(page.getEnd());
			model.addAttribute("page", result.get("page"));
			model.addAttribute("list", result.get("list"));
		return "boardMain";
		
	}
	
	/**등록 컨트롤러*/
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(FreeBoardDto dto){
		return "freeBoardInsert";
	}
	
	@RequestMapping("/freeBoardInsertPro.ino")
	public ModelAndView freeBoardInsertPro(HttpServletRequest request, FreeBoardDto dto) throws Exception{
		ModelAndView mav = new ModelAndView();
		freeBoardService.freeBoardInsertPro(dto);
		mav.setViewName("redirect:freeBoardDetail.ino?num="+dto.getNum());
		return mav;
	}
	
	/**상세 컨트롤러*/
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView freeBoardDetail(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getDetailByNum(num);
		return new ModelAndView("freeBoardDetail", "freeBoardDto", dto);
	}
	
	/**수정컨트롤러*/
	@RequestMapping("/freeBoardUpdate.ino")
	public ModelAndView freeBoardUpdate(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getDetailByNum(num);
		return new ModelAndView("freeBoardUpdate", "freeBoardDto", dto);
	}
	
	@RequestMapping("/freeBoardUpdatePro.ino")
	public ModelAndView freeBoardUpdatePro(HttpServletRequest request,FreeBoardDto dto) throws Exception{
		freeBoardService.freeBoardUpdatePro(dto);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:freeBoardDetail.ino?num="+dto.getNum());
		return mav;
	}
	
	/**삭제컨트롤러*/
	@RequestMapping("/freeBoardDelete.ino")
	public String freeBoardDelete(HttpServletRequest request) throws Exception{
		int num = Integer.parseInt(request.getParameter("num"));
		freeBoardService.freeBoardDelete(num);
		return "redirect:main.ino";
	}	
}
