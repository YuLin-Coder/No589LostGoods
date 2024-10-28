package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;
import utils.PageUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao=new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method=request.getParameter("method");
		if(method!=null&&method.equals("registeAct")) {
			registeUser(request,response);
			return;
		}
		if(method!=null&&method.equals("homepageAct")) {
			//registeUser(request,response);
			request.getRequestDispatcher("/WEB-INF/users/homepage.jsp").forward(request, response);
			return;
		}
		if(method!=null&&method.equals("consoleAct")) {
			//registeUser(request,response);
			request.getRequestDispatcher("/WEB-INF/views/console.jsp").forward(request, response);
			return;
		}
		
		if(method!=null&&method.equals("userListAct1")) {
		
			userList1(request, response);
			return;
		}
		
		if(method!=null&&method.equals("userListAct2")) {
			userList2(request,response);
			
			return;
		}
		
		if(method!=null&&method.equals("findByNameAct")) {
			String username=request.getParameter("username");
			User user=userDao.findByUserName(username);
			List<User> list=userDao.findMyInfo(user.getId());
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.removeAttribute("msg");
				request.getRequestDispatcher("/WEB-INF/views/list_user.jsp").forward(request, response);	
			}else if(list.size()<=0) {
				
				request.setAttribute("msg", "没有此纪录");
				request.getRequestDispatcher("/WEB-INF/views/list_user.jsp").forward(request, response);	
			}
			
			return;
		}
		
		
		if(method!=null&&method.equals("queryUserAct")) {
			/*userList2(request,response);*/
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.print(id+"****");
			User record=userDao.findById(id);
			request.setAttribute("list", record);
			request.getRequestDispatcher("/WEB-INF/views/update_user.jsp").forward(request, response);	
			return;
		}
		
		if(method!=null&&method.equals("queryUserNameAct")) {
			String username=request.getParameter("username");
			System.out.print(username+"****");
			User record=userDao.findByUserName(username);
			request.setAttribute("list", record);
			request.getRequestDispatcher("/WEB-INF/users/update_pwd.jsp").forward(request, response);	
			return;
		}		
		
		if(method!=null&&method.equals("toAddUserAct")) {
			request.getRequestDispatcher("/WEB-INF/views/add_user.jsp").forward(request, response);	
			return;
		}
	
		if(method!=null&&method.equals("addUserAct")) {
			addUser(request,response);
			return;
		}
		
		if(method!=null&&method.equals("toRegsiteAct")) {
			request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);	
			return;
		}
		
		if(method!=null&&method.equals("registeAct")) {
			registeUser(request,response);
			return;
		}
		
		if(method!=null&&method.equals("deleteUserAct")) {
			int id=Integer.parseInt(request.getParameter("id"));	
			userDao.delete(id);
			userList2(request, response);
			return;
		}
		if(method!=null&&method.equals("updatePwdAct")) {
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			User user=new User();
			user.setPassword(password);
			boolean b=userDao.updateUserPwd(user, username);
			if(b) {
				userList1(request, response);
			}
			return;
		}	
		if(method!=null&&method.equals("updateUserAct")){
			int id=Integer.parseInt(request.getParameter("id"));
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			int type=Integer.parseInt(request.getParameter("type"));
			String sex=request.getParameter("sex");
			String realname=request.getParameter("realname");
		    UserDao us=new UserDao();
		    User user=new User();
		    user.setUsername(username);
		    user.setPassword(password);
		    user.setSex(sex);
		    user.setType(type);
		    user.setRealname(realname);
		    String flag=request.getParameter("flag");
		   us.updateUser(user,id);
		    if(("1".equals(flag))){
		    	
		    	userList1(request, response);
		    }else  if("2".equals(flag)){
		    	userList2(request, response);
		    }
			return;
		}	
	}
	
	private void userList1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String username=request.getParameter("username");
		User user=(User) request.getSession().getAttribute("user");
		List<User> list=userDao.findMyInfo(user.getId());
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/user_list.jsp").forward(request, response);		
	}
	
	
	
	//添加用户
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		int type=Integer.parseInt(request.getParameter("type"));
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
	   
	    if(new PageUtils().existsUserName(username)){
            request.setAttribute("msg","该用户名已存在");
            request.setAttribute("username",username);
        	userList2(request, response);
        }else {
        	UserDao us=new UserDao();
     	    User user=new User();
     	    user.setUsername(username);
     	    user.setPassword(password);
     	    user.setRealname(realname);
     	    user.setSex(sex);     	    
     	    user.setType(type);
     	    us.insertUser(user);
  			userList2(request, response);    	

        }
	   
	   
	}

	//用户信息
	private void userList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list=userDao.findAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/user_list.jsp").forward(request, response);			
	}
	

	//注册
	private void registeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String realname=request.getParameter("realname");
		String sex=request.getParameter("sex");
		  if(new PageUtils().existsUserName(username)){
	            request.setAttribute("msg","该用户名已存在");
	            request.setAttribute("username",username);
	            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request,response);//返回到登陆界面
	        }else {
	        	UserDao us=new UserDao();
	     	    User user=new User();
	     	    user.setUsername(username);
	     	    user.setPassword(password);
	     	    user.setRealname(realname);
	     	    user.setSex(sex);     	    
	     	    user.setType(1);
	     	   us.insertUser(user);
	     	   request.setAttribute("msg", "ok");
		       request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);	
	        	

	        }
		   
		 
		
	}

}
