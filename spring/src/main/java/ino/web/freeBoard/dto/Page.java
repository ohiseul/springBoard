package ino.web.freeBoard.dto;

	public class Page {
		//pageNo = 요청페이지
		private int pageNo = 1;
		private int listSize = 5;
		private String keyword;
		private String searchType;
		private int begin=1;
		private int end;
		private int count;
		
		
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public void setBegin(int begin) {
			this.begin = begin;
		}

		public void setEnd(int end) {
			this.end = end;
		}

		public void setListSize(int listSize) {
			this.listSize = listSize;
		}
		
		public String getSearchType() {
			return searchType;
		}

		public void setSearchType(String searchType) {
			this.searchType = searchType;
		}

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public int getListSize() {
			return listSize;
		}

		public int getPageNo() {
			return pageNo;
		}
		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		   public int getBegin() {
		      return (this.pageNo -1) * 5 + 1;
		   }
		   public int getEnd() {
		      return this.pageNo * 5;
		   }
		}

	
