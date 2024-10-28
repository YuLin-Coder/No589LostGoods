package utils;

import java.util.List;
import dao.BoardDao;
import dao.MessageDao;
import dao.ShiwuDao;
import dao.UserDao;
import model.Board;
import model.Meet;
import model.Message;
import model.PageBean;
import model.Shiwu;
import model.User;

public class PageUtils2 {
	private UserDao userdao=new UserDao();
	 public PageBean queryPageBean(String currentPage,int pageSize) {

	       
	        PageBean pageBean = new PageBean();
	      
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	      
	        MessageDao dao = new MessageDao();
	       
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);
	      
	        pageBean.setPageSize(pageSize);
	        List<Message> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	        for(Message m:list) {
	        	User user=userdao.findById(m.getU_id());
	        	m.setUser(user);
	        }
	        pageBean.setMdata(list);
	        return pageBean;
	    }
	 
	 public PageBean queryPageBean2(String currentPage,int pageSize) {

	        PageBean pageBean = new PageBean();
	        
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	        
	        MessageDao dao = new MessageDao();
	     
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);	       
	        pageBean.setPageSize(pageSize);
	        List<Message> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	        for(Message m:list) {
	        	User user=userdao.findById(m.getU_id());
	        	m.setUser(user);
	        }
	        pageBean.setMdata(list);
	        return pageBean;
	    }
	 
	 public PageBean queryPageBean3(String currentPage,int pageSize) {

	      
	        PageBean pageBean = new PageBean();
	       
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	       
	        ShiwuDao dao = new ShiwuDao();
	     
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);
	     
	        pageBean.setPageSize(pageSize);
	        List<Shiwu> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	        for(Shiwu s:list) {
	        	User user=userdao.findById(s.getU_id());
	        	s.setUser(user);
	        }
	        pageBean.setSdata(list);
	        return pageBean;
	    }
	 
	 public PageBean queryPageBeanByBoard(String currentPage,int pageSize) {

	      
	        PageBean pageBean = new PageBean();
	        
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	     
	        BoardDao dao = new BoardDao();
	       
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);
	     
	        pageBean.setPageSize(pageSize);
	        List<Board> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	   
	        pageBean.setBdata(list);
	        return pageBean;
	    }
	 
	


}
