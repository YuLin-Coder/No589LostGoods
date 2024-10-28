package model;

import java.util.List;

public class PageBean {
    List<Goods> data;
    public List<Board> getBdata() {
		return bdata;
	}

	public void setBdata(List<Board> bdata) {
		this.bdata = bdata;
	}

	List<Board> bdata;
    public List<Shiwu> getSdata() {
		return sdata;
	}
  
	public void setSdata(List<Shiwu> sdata) {
		this.sdata = sdata;
	}
	
	
	List<Shiwu> sdata;
    public List<Message> getMdata() {
		return mdata;
	}

	public void setMdata(List<Message> mdata) {
		this.mdata = mdata;
	}

	List<Message> mdata;
	public List<Meet> getMeetData() {
		return meetData;
	}

	public void setMeetData(List<Meet> meetData) {
		this.meetData = meetData;
	}

	List<Meet> meetData;

    int firstPage;
    int prePage;
    int nextPage;
    int totalPage;
    int currentPage;
    int pageSize;
    int totalCount;

    public List<Goods> getData() {
        return data;
    }

    public void setData(List<Goods> data) {
        this.data = data;
    }


    public int getFirstPage() {
        return 1;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        return this.getCurrentPage()==this.getFirstPage()?1:
                this.getCurrentPage()-1;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

 
    public int getNextPage() {
        return this.getCurrentPage()==this.getTotalPage()?this
                .getTotalPage():
                this.getCurrentPage()+1;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return this.getTotalCount()%this.getPageSize()==0?this.getTotalCount()/this.getPageSize()
                :
                this.getTotalCount()/this.getPageSize()+1;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}