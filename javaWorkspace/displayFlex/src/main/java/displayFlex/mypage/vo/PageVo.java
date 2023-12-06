package displayFlex.mypage.vo;

public class PageVo {

	private int listCount;		//총 게시글 개수
	private int currentPage;	//현재 페이지
	private int pageLimit;		//페이징 영역 페이지갯수
	private int boardLimit;		//전체 게시글 갯수
	
	private int maxPage;		//가장 마지막 페이지
	private int startPage;		//페이징 영역 시작값
	private int endPage;		//페이징 영역 마지막값
	
	private int startRow;		//조회할 첫번째 행 번호(ROWNUM)
	private int lastRow;		//조회할 마지막 행 번호(ROWNUM)
	
	public PageVo(int listCount , int currentPage, int pageLimit , int boardLimit) {
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount/boardLimit);
	      this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
	      this.endPage = startPage + pageLimit - 1;
	      
	      if(endPage > maxPage) {
	         endPage = maxPage;
	      }
	      
	      this.startRow = (currentPage - 1) * boardLimit + 1;
	      this.lastRow = startRow + boardLimit - 1;
	}
	

	public int getStartRow() {
		return startRow;
	}


	public int getLastRow() {
		return lastRow;
	}


	public int getListCount() {
		return listCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public int getBoardLimit() {
		return boardLimit;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

}