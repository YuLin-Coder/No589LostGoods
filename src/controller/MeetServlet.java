package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MeetDao;
import dao.ShiwuDao;
import dao.UserDao;
import model.Meet;
import model.PageBean;
import model.Shiwu;
import model.User;
import utils.PageUtils;

/**
 * 处理用户认领业务逻辑
 */
public class MeetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MeetDao meetDao=new MeetDao();
	private ShiwuDao shiwuDao=new ShiwuDao();
	 private UserDao userdao=new UserDao();

    public MeetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		//审核认领通过，status为1
		if(method!=null&&method.equals("passAct")) {
			    int id=Integer.parseInt(request.getParameter("id"));
			    //int s_id=Integer.parseInt(request.getParameter("s_id"));
			    Meet record=meetDao.findById(id);
				String type=request.getParameter("type");
				Meet meet=new Meet();
				meet.setStatus(2);
				boolean b=meetDao.updateStatus(meet, id);
				shiwuDao.deleteShiwu(record.getWpname());
				if(b&&type.equals("1")) {
					 Integer uid=Integer.parseInt(request.getParameter("u_id"));	
					 List<Meet> list=meetDao.findByUid(uid);
					 for(Meet m:list) {				        	
				        	User user =userdao.findById(m.getU_id());
				        	m.setUser(user);			        
				        }
					 request.setAttribute("list", list);
				     request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
				}else {
					MeetList(request, response);
					
				}
			return;
	   }
		//审核认领失败，status为0
		if(method!=null&&method.equals("disAct")) {
			 int id=Integer.parseInt(request.getParameter("id"));
				String type=request.getParameter("type");
				Meet meet=new Meet();
				meet.setStatus(0);
				boolean b=meetDao.updateStatus(meet, id);
				if(b&&type.equals("1")) {
					 Integer uid=Integer.parseInt(request.getParameter("u_id"));	
					 List<Meet> list=meetDao.findByUid(uid);
					 for(Meet m:list) {				        	
				        	User user =userdao.findById(m.getU_id());
				        	m.setUser(user);				        	
				        }
					 request.setAttribute("list", list);
				     request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
				}else {
					MeetList(request, response);
				}	
			return;
	   }
	//跳转到添加页面
	 if(method!=null&&method.equals("toAddMeetAct")) {
			String wpname=request.getParameter("wpname");	
		    request.setAttribute("wpname", wpname);
			request.getRequestDispatcher("/WEB-INF/views/add_meet.jsp").forward(request, response);	
			return;
	   }
	 
	 //根据认领物品查找
	 if(method!=null&&method.equals("findByRlwpAct")) {
			String wpname=request.getParameter("wpname");	
		
			List<Meet> list=meetDao.findByWpname(wpname);//根据输入的认领物品查询
			for(Meet m:list) {
	        	User user =userdao.findById(m.getU_id());
	        	m.setUser(user);	    
	        }
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.removeAttribute("msg");
				request.getRequestDispatcher("/WEB-INF/views/list_meet.jsp").forward(request, response);	
			}else if(list.size()<=0) {
				
				request.setAttribute("msg", "没有此认领物品信息");
				request.getRequestDispatcher("/WEB-INF/views/list_meet.jsp").forward(request, response);	
			}
			return;
		}
	 
	 
	 //用户端查看我的认领物品
	 if(method!=null&&method.equals("findByUidAct")) {
		    Integer uid=Integer.parseInt(request.getParameter("u_id"));	
		    List<Meet> list=meetDao.findByUid(uid);
		    request.setAttribute("list", list);
		    for(Meet m:list) {	        	
	        	User user =userdao.findById(m.getU_id());
	        	m.setUser(user);	        
	        }
			request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
			return;
	   }
	   
	
	
	//管理员查看认领物品列表
	if(method!=null&&method.equals("meetListAct2")) {
		MeetList(request,response);
		return;
	}	
	
	//删除认领消息
	if(method!=null&&method.equals("deleteMeetAct")) {
		int id=Integer.parseInt(request.getParameter("id"));
		
		String type=request.getParameter("type");
		System.out.println(type+"****");
		meetDao.delete(id);
		if(type.equals("1")) {//如果是用户删除认领信息
			User user=(User) request.getSession().getAttribute("user");
		    List<Meet> list=meetDao.findByUid(user.getId());
		    for(Meet m:list) {	        	
	        	User u =userdao.findById(m.getU_id());
	        	m.setUser(u);	        
	        }
		    request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
		}else if(type.equals("2")){
			MeetList(request, response);
		}
		
		return;
	}
			
	//用户认领
	if(method!=null&&method.equals("addMeetAct")) {
		 
		String phone=request.getParameter("phone");
		String wpname=request.getParameter("wpname");
		String bz=request.getParameter("bz");
		String type=request.getParameter("type");
        User user=(User) request.getSession().getAttribute("user");
		Meet meet=new Meet();
		meet.setWpname(wpname);
		meet.setPhone(phone);
	
		meet.setBz(bz);
		meet.setU_id(user.getId());
		meet.setStatus(1);//1表示审核中
		Date nowtime=new Date();		
		try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime=simpleDateFormat.format(nowtime);
			meet.setRlsj(createTime);
		}catch(Exception e) {};
		boolean b=meetDao.insertmeet(meet);
		if(b&&type.equals("1")) {
				/* Integer uid=Integer.parseInt(request.getParameter("u_id")); */
			    User u=(User) request.getSession().getAttribute("user");
			    List<Meet> list=meetDao.findByUid(u.getId());
			    for(Meet m:list) {		        	
		        	User user1 =userdao.findById(m.getU_id());
		        	m.setUser(user1);		    
		        }
			    request.setAttribute("list", list);
				request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
		}else {
			MeetList(request, response);
		}
			return;
		}
	//表单回显
	if(method!=null&&method.equals("queryMeetAct")) {
		int id=Integer.parseInt(request.getParameter("id"));//获取认领表的id	
		Meet record=meetDao.findById(id);
		User user=userdao.findById(record.getU_id());
		request.setAttribute("list", record);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/update_meet.jsp").forward(request, response);	
		return;			
	}
			
	//修改认领信息
	if(method!=null&&method.equals("updateMeetAct")) {
			    int id=Integer.parseInt(request.getParameter("id"));			 
				String phone=request.getParameter("phone");			
				String bz=request.getParameter("bz");
				String type=request.getParameter("type");
				Meet meet=new Meet();				
				meet.setPhone(phone);			
				meet.setBz(bz);	
				boolean b=meetDao.updateMeet(meet, id);
				if(b&&type.equals("1")) {
					User user=(User) request.getSession().getAttribute("user");
				    List<Meet> list=meetDao.findByUid(user.getId());
				    for(Meet m:list) {			        	
			        	User u =userdao.findById(m.getU_id());
			        	m.setUser(u);			       
			        }
					 request.setAttribute("list", list);
				     request.getRequestDispatcher("/WEB-INF/users/my_meet.jsp").forward(request, response);	
				}else {
					MeetList(request, response);
				}
			}		
		}					
//认领列表
private void MeetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String currentPage = request.getParameter("currentPage");
    if (currentPage==null||currentPage.equals("")){
        //如果第一次访问,没有传递currentPage参数,则当前页为1
        currentPage="1";
    }
    //接收用户输入的每页显示数据的记录数
    String pageSize = request.getParameter("pageSize");
    //如果没有传递过来,设置一个默认值为2
    //细节:当点击下一页或者别的,会导致pageSize丢失了,应该要带回去
    if (pageSize==null||pageSize.equals("")){
        //设置pageSize的默认值是2
        pageSize="6";
    }

    //1)在service层调用获取PageBean对象的方法
    PageUtils service = new PageUtils();
    //只需要传入一个currentPage(当前页数)即可
    PageBean pageBean = service.queryPageBean2(currentPage,Integer.parseInt(pageSize));
    //2)把PageBean对象放到域中
    request.setAttribute("pageBean", pageBean);
    
    //3)转发
    request.getRequestDispatcher("/WEB-INF/views/meet_list.jsp").forward(request, response);
	}
}
