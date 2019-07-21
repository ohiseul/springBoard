package ino.web.freeBoard.service;

import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.dto.Page;
import ino.web.freeBoard.dto.PageResultDto;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeBoardService extends Page{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**글 리스트*/
	public Map<String, Object>  freeBoardList(Page page) throws Exception {
		Map<String, Object> result = new HashMap<>();
		int Cntnum = sqlSessionTemplate.selectOne("freeBoardCount",page);
		page.setCount(Cntnum);
		result.put("page", new PageResultDto(page.getPageNo(), Cntnum));
		result.put("list",sqlSessionTemplate.selectList("freeBoardGetList", page));

		return result;

	}
	/**리스트 사이즈*/
	public int getBoardListCnt(){
		return sqlSessionTemplate.selectOne("freeBoardCount");
	}
	
	/**등록*/
	public void freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
	}
	/**수정*/
	public void freeBoardUpdatePro(FreeBoardDto dto){
		sqlSessionTemplate.update("freeBoardUpdateByNum",dto);
	}
	/**상세*/
	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}
	/**삭제*/
	public void freeBoardDelete(int num){
		sqlSessionTemplate.delete("freeBoardDeleteByNum", num);
	}	

	
}
