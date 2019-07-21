package ino.web.fileBoard.controller;

import ino.web.fileBoard.dto.FileBoardDto;
import ino.web.fileBoard.dto.Files;
import ino.web.fileBoard.service.FileBoardService;
import ino.web.freeBoard.dto.Page;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileBoardController {
	
	@Autowired
	private FileBoardService filBoardService;
	
	@RequestMapping("/filemain.ino")
	public String list(Model model, Page page) throws Exception {
			Map<String, Object> result = filBoardService.fileBoardList(page);
			model.addAttribute("page", result.get("page"));
			model.addAttribute("fileBoardList", result.get("fileBoardList"));
		return "fileBoardMain";
		
	}
	
	/**등록 컨트롤러*/
	@RequestMapping("/fileBoardInsert.ino")
	public String fileBoardInsert(FileBoardDto dto){
		return "fileBoardInsert";
	}
	
	@RequestMapping(value="/fileBoardInsertPro.ino", method=RequestMethod.POST)
	public ModelAndView fileBoardInsertPro(FileBoardDto dto) throws Exception{
		ModelAndView mav = new ModelAndView();
		System.out.println("파일확인용"+dto.getAttach().length);
		System.out.println("컨트롤러 오나요?");
		if(dto.getAttach().length==0){
			System.out.println("파일이 존재하지 않음");
			filBoardService.fileBoardInsertPro(dto);
		}else{
			//파일이 존재함
			System.out.println("파일이 존재함");
		
			//어려 파일의 등록이 끝난 후 게시글 등록을 한다.
			filBoardService.fileBoardInsertPro(dto);
			System.out.println("게시물 등록 끝나고 파일등록 시작(게시물 번호를 가져오기 위해 게시글을 먼저 등록하였음)");
			for(int i=0; i < dto.getAttach().length; i++){	
				MultipartFile attach = dto.getAttach()[i];
				System.out.println(i+"번째 파일");
				
				String path = "C:/ohisoul/imgFile"; 
				String oName = attach.getOriginalFilename();
				String sName = UUID.randomUUID().toString().replaceAll("-", "");
				sName += oName.substring(oName.lastIndexOf("."));
				File imgFile = new File(path +"/"+ oName);
				attach.transferTo(imgFile);
				
				Files f = new Files();
				System.out.println("동일한 글번호 들어가?"+dto.getNum());
				f.setNum(dto.getNum());
				System.out.println("파일이 등록될 글번호");
				f.setBeStatus(1);
				f.setFilePath(path);
				f.setFileSize((int)attach.getSize());
				f.setOriName(oName);
				f.setSysName(sName);		
				//파일의 리스트 만큼 같은 게시판 번호로 파일을 넣어준다
				System.out.println(i+"번째 등록완료:");
			}
			System.out.println("파일등록 끝났다.");
		}
		
		
		mav.setViewName("redirect:fileBoardDetail.ino?num="+dto.getNum());
		return mav;
	}
	
	/**상세 컨트롤러*/
	@RequestMapping("/fileBoardDetail.ino")
	public void fileBoardDetail(HttpServletRequest request,Model model){
		int num = Integer.parseInt(request.getParameter("num"));
//		FileBoardDto dto = filBoardService.getDetailByNum(num);
//		return new ModelAndView("fileBoardDetail", "fileBoardDto", dto);
		Map<String, Object> result = filBoardService.getDetailByNum(num);
			model.addAttribute("fileList", result.get("fileList"));
			model.addAttribute("fileBoardList", result.get("fileBoardList"));
	}
	
	/**수정컨트롤러*/
	@RequestMapping("/fileBoardUpdate.ino")
	public String fileBoardUpdate(HttpServletRequest request,Model model){
		int num = Integer.parseInt(request.getParameter("num"));
//		FileBoardDto dto = filBoardService.getDetailByNum(num);
//		return new ModelAndView("fileBoardUpdate", "fileBoardDto", dto);
		Map<String, Object> result = filBoardService.getDetailByNum(num);
		model.addAttribute("fileList", result.get("fileList"));
		model.addAttribute("fileBoardList", result.get("fileBoardList"));
		return "fileBoardUpdate";
	}
	
	@RequestMapping("/fileBoardUpdatePro.ino")
	public ModelAndView fileBoardUpdatePro(HttpServletRequest request,FileBoardDto dto) throws Exception{
		filBoardService.fileBoardUpdatePro(dto);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:fileBoardDetail.ino?num="+dto.getNum());
		return mav;
	}
	
	/**삭제컨트롤러*/
	@RequestMapping("/fileBoardDelete.ino")
	public String fileBoardDelete(HttpServletRequest request) throws Exception{
		int num = Integer.parseInt(request.getParameter("num"));
		filBoardService.fileBoardDelete(num);
		return "redirect:main.ino";
	}	
	
	
	/**파일 수정*/

	/**파일 삭제*/
	@RequestMapping("/fileDelete.ino")
	@ResponseBody
	public void fileDelete(int fileNo) throws Exception{
		System.out.println("파일삭제왔어"+fileNo);
		filBoardService.fileDelete(fileNo);
	}	
	
	/**파일다운로드*/
	
	@RequestMapping("/downFile.do")

	public void downFile(int num,int fileNo, HttpServletResponse response) throws Exception {

		System.out.println("다운로드 옴");

 

		List<Files> files = filBoardService.fileList(num);

		System.out.println("files:" + files);

		for (Files f1 : files) {

			if (f1.getFileNo() == fileNo) {

				String path = f1.getFilePath();

				String name = f1.getSysName();

				// 다운로드 할 파일 이름

				String dName = f1.getOriName();

				

				System.out.println("path : " + path);

				System.out.println("name : " + name);

				

				File f = new File(path, name);

				

				// 전송하는 데이터의 해석 정보

				if (dName == null) {

					response.setHeader("Content-Type", "image/jpg");

				} else {

					response.setHeader(

						"Content-Type", "application/octet-stream"

					);

					

					// 한글 이름 처리하기

					dName = new String(dName.getBytes("utf-8"), "8859_1");

 

					response.setHeader(

						"Content-Disposition", "attachment;filename=" + dName

					);

				}

				

				// 파일을 읽고 사용자에게 전송

				FileInputStream fis = new FileInputStream(f);

				BufferedInputStream bis = new BufferedInputStream(fis);

				

				OutputStream out = response.getOutputStream();

				BufferedOutputStream bos = new BufferedOutputStream(out);

			

				while (true) {

					int ch = bis.read();

					if (ch == -1) break;

					

					bos.write(ch);

				}

			
				bis.close();  fis.close();

				bos.close();  out.close();
			}

		}
	}
	
	
	
	
	
}

	

