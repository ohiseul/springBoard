package ino.web.util;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.view.AbstractView;
//이 다운로드 뷰는 파일을 받도록 ...
@Repository
public class DownloadView extends AbstractView{
	//AbstractView 커스텀 뷰  생성하기위해 extends 한다... 보통의 뷰들이 다 이거로 만들어짐
	 public DownloadView(){
		    setContentType("application/download; UTF-8");
	  }


	@Override		//뷰가 생성되고 리턴할때 도는놈...
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = (String) model.get("FILE_PATH");
		File serverFile = new File(path);
		String fileName = (String) model.get("FILE_ORIGIN");
		fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileUtils.copyFile(serverFile, response.getOutputStream());
	}

}
