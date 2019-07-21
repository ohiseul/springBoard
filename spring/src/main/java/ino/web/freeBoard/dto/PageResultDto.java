package ino.web.freeBoard.dto;

public class PageResultDto extends Page{
	   private int pageNo;
	   private int count;
	   private int beginPage;
	   private int endPage;
	   private boolean prev;
	   private boolean next;
	   private int lastPage ;
	   
	   public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public PageResultDto(int pageNo, int count) {
	      this.pageNo = pageNo;
	      this.count = count;
	      setPageInfo();
	   }
	   
	   private void setPageInfo() {
	      this.lastPage = (count % 5 == 0) ? count / 5
	                                     : count / 5 + 1;
	      int tabSize = 5;
	      int currTab = (pageNo -1) / tabSize + 1; 
	      
	      beginPage = (currTab -1) * tabSize + 1;
	      endPage = (currTab * tabSize > lastPage) ? lastPage 
	                                                 : currTab * tabSize;
	      if(beginPage != 1){
	    	  this.prev = true;
	      }else{
	    	  this.prev = false;
	      }
	      
//	      prev = beginPage != 1;
	      if(endPage != lastPage){
	    	  this.next = true;
	      }
//	      next = endPage != lastPage;
	   }

	   public int getPageNo() {
	      return pageNo;
	   }

	   public int getCount() {
	      return count;
	   }

	   public int getBeginPage() {
	      return beginPage;
	   }

	   public int getEndPage() {
	      return endPage;
	   }

	   public boolean isPrev() {
	      return prev;
	   }

	   public boolean isNext() {
	      return next;
	   }
	}



