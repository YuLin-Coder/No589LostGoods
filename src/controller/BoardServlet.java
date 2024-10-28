package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import model.Board;
import model.PageBean;
import utils.PageUtils;
import utils.PageUtils2;


/*
 * Servlet implementation class BoardServlet
 */
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDao dao=new BoardDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
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
		//添加公告
		if(method!=null&&method.equals("addBoardAct")) {
			addBoard(request,response);
			return;
		}
		
		//公告列表
		if(method!=null&&method.equals("BoardListAct")) {
			
			BoardList(request,response);	
			return;
		}
		
		
		//跳转到添加公告表单
	   if(method!=null&&method.equals("toAddBoardAct")) {
			request.getRequestDispatcher("/WEB-INF/board/add_board.jsp").forward(request, response);	
			
			return;
		}
	   
	   //表单回显公告信息
	   if(method!=null&&method.equals("queryBoardAct")) {

			int id=Integer.parseInt(request.getParameter("id"));
			System.out.print(id+"****");
			Board record=dao.findById(id);
			request.setAttribute("board", record);
			request.getRequestDispatcher("/WEB-INF/board/update_board.jsp").forward(request, response);	 
			return;
		}
	   
	   //删除公告
	   if(method!=null&&method.equals("deleteBoardAct")) {
			int id=Integer.parseInt(request.getParameter("id"));	
			dao.delete(id);		
			BoardList(request, response);	
			return;
		}
	   
	   //条件查询公告
	   if(method!=null&&method.equals("findByMapAct")) {
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			System.out.print(content+"****");
			List<Board> list=dao.findByMap(title, content);
			if(list.size()>0) {
				request.setAttribute("list", list);
				request.removeAttribute("msg");
				request.getRequestDispatcher("/WEB-INF/board/list_board.jsp").forward(request, response);	
			}else if(list.size()<=0) {
				
				request.setAttribute("msg", "没有该记录");
				request.getRequestDispatcher("/WEB-INF/board/list_board.jsp").forward(request, response);	
			}
			return;
		}
	   
	   if(method!=null&&method.equals("updateBoardAct")) {
		   int id=Integer.parseInt(request.getParameter("id"));
		    String title=request.getParameter("title");
			String content=request.getParameter("content");
			String editor=request.getParameter("editor");
			Board record=new Board();
			record.setTitle(title);
			record.setContent(content);
			record.setEditor(editor);;
			boolean b=dao.updateBoard(record, id);
			if(b){
			    	request.setAttribute("msg", "ok");
			    	BoardList(request, response);
			}
			return;
		}
		
	
}
	
	

	
//分页展示公告列表
		private void BoardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 String currentPage = request.getParameter("currentPage");
		        if (currentPage==null||currentPage.equals("")){
		          
		            currentPage="1";
		        }
		      
		        String pageSize = request.getParameter("pageSize");
		       
		        if (pageSize==null||pageSize.equals("")){
		           
		            pageSize="6";
		        }

		       
		        PageUtils2 service = new PageUtils2();
		       
		        PageBean pageBean = service.queryPageBeanByBoard(currentPage,Integer.parseInt(pageSize));
		        
		        request.setAttribute("pageBean", pageBean);
		      
		        request.getRequestDispatcher("/WEB-INF/board/board_list.jsp").forward(request, response);
			
		}

	
		private void addBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    String title=request.getParameter("title");
				String content=request.getParameter("content");
				String editor=request.getParameter("editor");
				Board record=new Board();
				record.setTitle(title);
				record.setContent(content);
				record.setEditor(editor);;
				Date nowtime=new Date();		
				try {
					SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String createTime=simpleDateFormat.format(nowtime);
					record.setFbsj(createTime);
				}catch(Exception e) {};
				
			    boolean b=dao.insertboard(record);
			    if(b) {
			    	  BoardList(request, response);
						 
			    }		
	}
	

}
