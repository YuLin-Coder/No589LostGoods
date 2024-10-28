package controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.ShiwuDao;
import dao.UserDao;
import model.PageBean;
import model.Shiwu;
import model.User;
import utils.PageUtils2;


/**
 * Servlet implementation class ShiwuServlet
 */
public class ShiwuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ShiwuDao dao=new ShiwuDao();
	private UserDao userDao=new UserDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiwuServlet() {
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
		//添加拾物信息
		if(method!=null&&method.equals("addShiwuAct")) {
          try {
				
				Shiwu shiwu = fileUpload(request);
				User user=(User) request.getSession().getAttribute("user");
				shiwu.setU_id(user.getId());
				System.out.println(user.getId()+"***********");
				boolean b=dao.insertShiwu(shiwu);
				if(b) {
					ShiwuList3(request, response,user.getId());
			   
			     }
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			return;
		}
		//查询用户发布的拾物品信息
		if(method!=null&&method.equals("findBySnameAct")) {
			
			ShiwuList1(request,response);
			return;
		}
		
		
		//查询所有的拾物品信息
		if(method!=null&&method.equals("ShiwuListAct")) {
			ShiwuList2(request,response);
			return;
		}
		
		//用户发布拾物品信息
		 if(method!=null&&method.equals("toAddShiwuAct1")) {
				request.getRequestDispatcher("/WEB-INF/users/add_shiwu.jsp").forward(request, response);	
				
				return;
			}
		//跳转到添加页面
	   if(method!=null&&method.equals("toAddShiwuAct2")) {
			request.getRequestDispatcher("/WEB-INF/views/add_shiwu.jsp").forward(request, response);	
			
			return;
		}
	   
	   //跳转到更改界面
	   if(method!=null&&method.equals("queryShiwuAct")) {

			int id=Integer.parseInt(request.getParameter("id"));
			System.out.print(id+"****");
			Shiwu record=dao.findById(id);
			request.setAttribute("list", record);
			request.getRequestDispatcher("/WEB-INF/views/update_shiwu.jsp").forward(request, response);	
			return;
		}
	   
	   //删除物品
	   if(method!=null&&method.equals("deleteShiwuAct")) {
			int id=Integer.parseInt(request.getParameter("id"));
			String type=request.getParameter("type");
			User user=(User) request.getSession().getAttribute("user");
			
			dao.delete(id);
			if(type.equals("1")) {//如果为用户
				ShiwuList3(request, response,user.getId());
				
			}else{
				ShiwuList2(request, response);
			}
			
			return;
		}
	 //多条件查询
	   if(method!=null&&method.equals("findByMapAct")) {
			String wpname=request.getParameter("wpname");
			String detail=request.getParameter("detail");
		
			List<Shiwu> list=dao.findByMap(wpname,detail);
			for(Shiwu s:list) {
				User u=userDao.findById(s.getU_id());
				s.setUser(u);
			}
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.removeAttribute("msg");
				request.getRequestDispatcher("/WEB-INF/views/list_shiwu.jsp").forward(request, response);	
			}else if(list.size()<=0) {
				
				request.setAttribute("msg", "没有此物品信息");
				request.getRequestDispatcher("/WEB-INF/views/list_shiwu.jsp").forward(request, response);	
			}
			
			return;
		}
	   
	   if(method!=null&&method.equals("updateShiwuAct")) {
		   int id=Integer.parseInt(request.getParameter("id"));		   			
			String wpname=request.getParameter("wpname").trim();
			String place=request.getParameter("place").trim();
			String detail=request.getParameter("detail").trim();		
			String createTime=request.getParameter("createTime").trim();	
			String phone=request.getParameter("phone").trim();
			
			Shiwu record=new Shiwu();
			
			record.setWpname(wpname);
			record.setPlace(place);
			record.setDetail(detail);
			record.setCreateTime(createTime);
			record.setPhone(phone);
         boolean b=dao.updateShiwu(record, id);
			if(b){
			    request.setAttribute("msg", "ok");
			    	ShiwuList2(request, response);
			}else {
			    	ShiwuList2(request, response);
			    }
			
			
			return;
		}
		
	   
	 
	
}
	
	//用户发布的拾无信息
	private void ShiwuList1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=(User) request.getSession().getAttribute("user");
		List<Shiwu> list=dao.findByUid(user.getId());
		for(Shiwu s:list) {
			User u=userDao.findById(s.getU_id());
			s.setUser(u);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/users/myshiwu_list.jsp").forward(request, response);	
		
	}
	
	private void ShiwuList3(HttpServletRequest request, HttpServletResponse response,int u_id) throws ServletException, IOException {
		//String sname=request.getParameter("sname");
		System.out.print(u_id);
		List<Shiwu> list=dao.findByUid(u_id);
		for(Shiwu s:list) {
			User user=userDao.findById(s.getU_id());
			s.setUser(user);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/users/myshiwu_list.jsp").forward(request, response);	
		
	}
	
	

	
	//管理员-所有拾物品信息
		private void ShiwuList2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		            pageSize="3";
		        }

		        //1)在service层调用获取PageBean对象的方法
		        PageUtils2 service = new PageUtils2();
		        //只需要传入一个currentPage(当前页数)即可
		        PageBean pageBean = service.queryPageBean3(currentPage,Integer.parseInt(pageSize));
		        //2)把PageBean对象放到域中
		        request.setAttribute("pageBean", pageBean);
		        //3)转发
		        request.getRequestDispatcher("/WEB-INF/views/shiwu_list.jsp").forward(request, response);
			
		}
		
	
		
		
		public String makeFileName(String filename){
			String ext = filename.substring(filename.lastIndexOf(".") + 1);
			return UUID.randomUUID().toString() + "." + ext;
		}
		
		
		private Shiwu fileUpload(HttpServletRequest request) {
			Shiwu shiwu=new Shiwu();
			try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(item.isFormField()){
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					BeanUtils.setProperty(shiwu, name, value);
				}else{
					String filename = item.getName();
					String savefilename = makeFileName(filename);
					//String savepath="D:\\ws\\LostGoods\\WebContent\\upload\\";
					//String savepath="D:\\Program Files\\eclipse_workspace\\LostGoods\\WebContent\\upload\\";
					String savepath= this.getServletContext().getRealPath("/upload");//Tomcat虚拟路径
					InputStream in = item.getInputStream();
					FileOutputStream out = new FileOutputStream(savepath + "\\" + savefilename);
					int len = 0;
					byte buffer[] = new byte[1024];
					while((len = in.read(buffer)) > 0){
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					item.delete();
					shiwu.setImgUrl(savefilename);
					
					System.out.println(shiwu.getImgUrl());
				}
			}
		        return shiwu;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
	

		

}
