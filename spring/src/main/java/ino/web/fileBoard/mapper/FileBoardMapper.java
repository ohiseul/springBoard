package ino.web.fileBoard.mapper;

import ino.web.fileBoard.dto.FileBoardDto;

import org.apache.ibatis.annotations.Select;

public interface FileBoardMapper {
	
	  @Select("SELECT NUM, TITLE, NAME, TO_CHAR(REGDATE,'YYYY-MM-DD') REGDATE, CONTENT FROM FILEBOARD ORDER BY LPAD( NUM, 5 ) DESC")
	  public FileBoardDto fileBoardGetList2();
	
}



