package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.User;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object attribute=request.getParameter("method");
		System.out.println("请求已经到达");
		String method="";
		if(attribute!=null) {
			method=attribute.toString();
		}
		if("login".equals(method)){
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		if("loginAct".equals(method)){
			login(request,response);
			return;
		}
		
		if("loginOutAct".equals(method)){
			request.getSession().setAttribute("username", null);
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("id");
			request.getSession().removeAttribute("type");
			//request.getRequestDispatcher("/login.jsp").forward(request, response);
			response.sendRedirect("LoginServlet?method=login");
			return;
			
		}
		
	}
	//登录处理
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
	
		int userType=Integer.parseInt(request.getParameter("type").trim());
	      
	   System.out.println(userType);
		UserDao userdao=new UserDao();
		User user=userdao.findByUserName(username);
		if(user==null){
			request.setAttribute("message", "用户名不存在！");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}else if(!user.getPassword().equals(password)){
			request.setAttribute("message", "用户名或密码错误");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			
		}else if((user.getPassword().equals(password))&&((user.getType()==1)&&(userType==1))){			
			request.getSession().setAttribute("user",user);
			request.getSession().setAttribute("type", user.getType());
			request.getRequestDispatcher("/WEB-INF/users/main.jsp").forward(request, response);
		}else if((user.getPassword().equals(password))&&((user.getType()==2)&&(userType==2))) {
			request.getSession().setAttribute("type", user.getType());
			request.getSession().setAttribute("realname", user.getRealname());
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("id", user.getId());
			request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "用户和用户类型不匹配");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
		
	}

}
