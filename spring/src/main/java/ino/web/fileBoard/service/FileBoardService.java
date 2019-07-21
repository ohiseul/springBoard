package ino.web.fileBoard.service;

import ino.web.fileBoard.dto.FileBoardDto;
import ino.web.fileBoard.dto.Files;
import ino.web.freeBoard.dto.Page;
import ino.web.freeBoard.dto.PageResultDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileBoardService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**파일보드리스트*/
	public Map<String, Object>  fileBoardList(Page page) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int Cntnum = sqlSessionTemplate.selectOne("fileBoardCount");
		result.put("page", new PageResultDto(page.getPageNo(), Cntnum));
		result.put("fileBoardList",sqlSessionTemplate.selectList("fileBoardGetList", page));
		return result;
	}
	/**파일리스트*/
	public List<Files>  fileList(int num) throws Exception {
		List<Files> files= sqlSessionTemplate.selectList("FileListByNo", num);
		return files;
	}

	/**상세*/
	public Map<String, Object> getDetailByNum(int num){
		Map<String, Object> result = new HashMap<>();
		result.put("fileList",sqlSessionTemplate.selectList("FileListByNo", num));
		result.put(	"fileBoardList",sqlSessionTemplate.selectOne("fileBoardDetailByNum", num));
		return result;
	}
	/**등록*/
	public void fileBoardInsertPro(FileBoardDto dto){
		sqlSessionTemplate.insert("fileBoardInsertPro",dto);
	}
	/**수정*/
	public void fileBoardUpdatePro(FileBoardDto dto){
		sqlSessionTemplate.update("fileBoardUpdateByNum",dto);
	}
	/**삭제*/
	public void fileBoardDelete(int num){
		sqlSessionTemplate.delete("fileBoardDeleteByNum", num);
	}
	/**파일존재여부*/
	public boolean getFileBeStatus(){
		boolean flag;
		int num = sqlSessionTemplate.selectOne("fileBeStatusByNum");
		if(num == 1){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	
	}
	/**파일등록*/
	public void fileInsertPro(Files files){
		sqlSessionTemplate.insert("insertFile",files);
	}
	
	/**파일수정*/
	
	/**파일삭제*/
	public void fileDelete(int fileNo){
		sqlSessionTemplate.delete("fileDeleteByNum", fileNo);
	}
}
