package top.icoding.util;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
public class Page {
	/**
	 * 总条数
	 * */
	private int totalNumber;
	/**
	 * 当前页数
	 * */
	private int currentPage;
	/**
	 * 总页数
	 * */
	private int totalPage;
	/**
	 * 每页显示多少条
	 * */
	private int pageNumber=10;
	/**
	 * 数据库中从第几条开始查询
	 * */
	private int dbIndex;
	/**
	 * 数据库中查询多少条
	 * */
	private int dbNumber;
	
	
	private void countNumber(){
		/**
		 * 获取总页数
		 * */
		int totalPageTemp=this.totalNumber/this.pageNumber;
		int plus=(this.totalNumber%this.pageNumber==0)?0:1;
		totalPageTemp=(totalPageTemp+plus)<=0?1:totalPageTemp+plus;
		this.totalPage=totalPageTemp;
		/**
		 * 判断当前页数是否大于总页数，如果大于则改为总页数
		 * 判断当前页数是否小于1页，如果小鱼则变成第一页
		 * */
		if(this.currentPage>this.totalPage){
			this.currentPage=this.totalPage;
		}
		if(this.currentPage<1){
			this.currentPage=1;
		}
		/**
		 * 算出从数据库内第几条开始查询，查询多少条
		 * */
		this.dbIndex=(this.currentPage-1)*this.pageNumber;
		this.dbNumber=this.pageNumber;
	}
	
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
		countNumber();
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getDbIndex() {
		return dbIndex;
	}
	public void setDbIndex(int dbIndex) {
		this.dbIndex = dbIndex;
	}

	public int getDbNumber() {
		return dbNumber;
	}

	public void setDbNumber(int dbNumber) {
		this.dbNumber = dbNumber;
	}

	@Override
	public String toString() {
		return "Page [totalNumber=" + totalNumber + ", currentPage="
				+ currentPage + ", totalPage=" + totalPage + ", pageNumber="
				+ pageNumber + ", dbIndex=" + dbIndex + ", dbNumber="
				+ dbNumber + "]";
	}
	
}
