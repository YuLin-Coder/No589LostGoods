package utils;

import java.util.List;

import dao.GoodsDao;
import dao.MeetDao;
import dao.ShiwuDao;
import dao.UserDao;
import model.Goods;
import model.Meet;
import model.PageBean;
import model.Shiwu;
import model.User;
public class PageUtils {
	 private UserDao userdao=new UserDao();
	 private ShiwuDao shiwuDao=new ShiwuDao();
	 public PageBean queryPageBean(String currentPage,int pageSize) {
	        //1)封装PageBean对象
	        PageBean pageBean = new PageBean();
	        //todo 显示当前页数据应该在dao层实现
	        //1.2)记录当前页的数据
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	        //得到dao层的实现类对象,操作方法
	        GoodsDao dao = new GoodsDao();
	        //1.3)显示当前页数据
	        //前提:准备好总记录数和每页大小
	        //获取数据库的总记录数
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);
	        //每页的数据大小
	        pageBean.setPageSize(pageSize);
	        List<Goods> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	        for(Goods goods:list) {
	        	User user =userdao.findById(goods.getU_id());
	        	goods.setUser(user);
	        }
	        //1.4)添加到PageBean对象中
	        pageBean.setData(list);
	        return pageBean;
	    }
	 
	 public PageBean queryPageBean2(String currentPage,int pageSize) {
	        //1)封装PageBean对象
	        PageBean pageBean = new PageBean();
	        //todo 显示当前页数据应该在dao层实现
	        //1.2)记录当前页的数据
	        pageBean.setCurrentPage(Integer.parseInt(currentPage));
	        //得到dao层的实现类对象,操作方法
	        MeetDao dao = new MeetDao();
	        //1.3)显示当前页数据
	        //前提:准备好总记录数和每页大小
	        //获取数据库的总记录数
	        int totalCount=dao.queryCount();
	        pageBean.setTotalCount(totalCount);
	        //每页的数据大小
	        pageBean.setPageSize(pageSize);
	        List<Meet> list = dao.queryData(pageBean.getCurrentPage() ,pageBean.getPageSize());
	        for(Meet m:list) {	        
	        	User user =userdao.findById(m.getU_id());
	        	m.setUser(user);
	        }
	        //1.4)添加到PageBean对象中
	        pageBean.setMeetData(list);
	        return pageBean;
	    }
	 
	 
	 public boolean existsUserName(String username) {
	        if(userdao.findByUserName(username)==null){
	            return false;//表示不可以用
	        }
	        return true;//表示可以用

	    }


}
