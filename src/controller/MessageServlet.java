package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDao;
import dao.UserDao;
import model.Goods;
import model.Message;
import model.PageBean;
import model.User;
import utils.PageUtils;
import utils.PageUtils2;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MessageDao messageDao=new MessageDao();
	private UserDao userDao=new UserDao();
       
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
	//跳转到添加页面
	 if(method!=null&&method.equals("toAddMessageAct1")) {
			request.getRequestDispatcher("/WEB-INF/users/add_message.jsp").forward(request, response);	
			return;
	   }
	 
	 
	 if(method!=null&&method.equals("findByMessageAct")) {
		User user=(User) request.getSession().getAttribute("user");
			
			System.out.print(user.getId()+"****");
			List<Message> list=messageDao.findMyMessage(user.getId());
			for(Message m:list) {
				User u=userDao.findById(m.getU_id());
				m.setUser(u);
			}
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.removeAttribute("msg");
				request.getRequestDispatcher("/WEB-INF/views/list_message.jsp").forward(request, response);	
			}else if(list.size()<=0) {
				
				request.setAttribute("msg", "没有此留言信息");
				request.getRequestDispatcher("/WEB-INF/views/list_message.jsp").forward(request, response);	
			}
			
			return;
		}
	   
	 
	 	
		   
		   
	if(method!=null&&method.equals("messageListAct1")) {
		
			MessageList1(request, response);
			return;
	}
	
	
	if(method!=null&&method.equals("messageListAct2")) {
		MessageList2(request,response);
		return;
}	
	
	if(method!=null&&method.equals("messageListAct3")) {
		MessageList3(request,response);
		return;
}	
	if(method!=null&&method.equals("deleteMessageAct")) {
		int id=Integer.parseInt(request.getParameter("id"));
		String mname=request.getParameter("mname");
		String type=request.getParameter("type");
		System.out.println(type+"****"+mname);
		messageDao.delete(id);
		if(type.equals("1")) {
			User user=(User) request.getSession().getAttribute("user");
			List<Message> list=messageDao.findMyMessage(user.getId());
			for(Message m:list) {
				User u=userDao.findById(m.getU_id());
				m.setUser(u);
			}
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/users/message_list.jsp").forward(request, response);	
		}else if(type.equals("2")){
			MessageList2(request, response);
		}
		
		return;
	}
			
	//管理员添加
	if(method!=null&&method.equals("addMessageAct")) {
		
		String content=request.getParameter("content");
	
		String type=request.getParameter("type");
		Message message=new Message();
		User user=(User) request.getSession().getAttribute("user");
		message.setU_id(user.getId());
		message.setContent(content);
		Date nowtime=new Date();		
		try {
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime=simpleDateFormat.format(nowtime);
			message.setCreateTime(createTime);
		}catch(Exception e) {};
	
		
		boolean b=messageDao.insertMessage(message);
		if(b&&type.equals("1")) {
			MessageList1(request, response);
		}else {
			MessageList2(request, response);
		}
		
				
			return;
		}
	if(method!=null&&method.equals("queryMessageAct")) {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.print(id+"****");
		Message record=messageDao.findById(id);
		request.setAttribute("list", record);
		request.getRequestDispatcher("/WEB-INF/views/update_message.jsp").forward(request, response);	
		return;	
				
	}
	if(method!=null&&method.equals("findByMapAct")) {
		findByMapAct(request, response);
		return;	
				
	}
			
			
			
	if(method!=null&&method.equals("updateMessageAct")) {
			 int id=Integer.parseInt(request.getParameter("id"));
			
				String content=request.getParameter("content");
				String type=request.getParameter("type");
				Message message=new Message();
				User user=(User) request.getSession().getAttribute("user");
				message.setU_id(user.getId());
				message.setContent(content);												
				boolean b=messageDao.updateMessage(message, id);
				if(b&&type.equals("1")) {
					MessageList1(request, response);
				}else {
					MessageList2(request, response);
				}
				
				
			}
			
			
			
			
			
			
		}

	
		
		

	
private void MessageList3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    PageUtils2 service = new PageUtils2();
    //只需要传入一个currentPage(当前页数)即可
    PageBean pageBean = service.queryPageBean2(currentPage,Integer.parseInt(pageSize));
    //2)把PageBean对象放到域中
    request.setAttribute("pageBean", pageBean);
    //3)转发
    request.getRequestDispatcher("/WEB-INF/users/message_list2.jsp").forward(request, response);
	}

private void MessageList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    PageUtils2 service = new PageUtils2();
    //只需要传入一个currentPage(当前页数)即可
    PageBean pageBean = service.queryPageBean(currentPage,Integer.parseInt(pageSize));
    //2)把PageBean对象放到域中
    request.setAttribute("pageBean", pageBean);
    //3)转发
    request.getRequestDispatcher("/WEB-INF/views/message_list.jsp").forward(request, response);
	
}

private void findByMapAct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String realname=request.getParameter("realname");
	User user=userDao.findByRealname(realname);
	List<Message> list=messageDao.findMyMessage(user.getId());
	for(Message m:list) {
		User u=userDao.findById(m.getU_id());
		m.setUser(u);
	}
	request.setAttribute("list", list);
	request.getRequestDispatcher("/WEB-INF/views/message_list.jsp").forward(request, response);	
	
}

private void MessageList1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	User user=(User) request.getSession().getAttribute("user");	
	List<Message> list=messageDao.findMyMessage(user.getId());
	for(Message m:list) {
		User u=userDao.findById(m.getU_id());
		m.setUser(u);
	}
	request.setAttribute("list", list);
	request.getRequestDispatcher("/WEB-INF/users/message_list.jsp").forward(request, response);	
	
}
	

}
