package dao;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import model.Board;
import utils.C3p0Utils;




public class BoardDao {
	//获取连接执行查询
		private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());
		//发布公告
		public boolean insertboard(Board b) {
			try {
				//执行插入sql
				runner.update("insert into board(title,content,editor,fbsj)values(?,?,?,?)",
						b.getTitle(),b.getContent(),b.getEditor(),b.getFbsj());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return true;//成功返回true


			
		}
		
		//根据id删除
		public int delete(int id) {
			try {
				//执行删除的sql
				runner.update("delete from board where id=?",id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}

			return 1;//删除成功返回1表示结束
		}
		
		//查询所有公告信息
		public List<Board> findAll() {
					try {
						return runner.query("select * from board", new BeanListHandler<Board>(Board.class));//添加实体类的适配器进行类的反射
					} catch (SQLException e) {//捕获异常
						throw new RuntimeException(e);//抛出运行异常
					}

		}
		
		
		
		
		/**
		 * 
		 * 根据用户名查询个人的信息
		 */
		public List<Board> findByMap(String title,String content) {
			try {
				String sql = "select * from board where 1=1 ";
		        //创建一个集合用来存储查询的参数，因为我们不清楚客户到底输入几个参数，所以用集合来存放
		        List<String> list = new ArrayList<String>();
		        if (title != "") {
		            //如果用户输入的pname不为空，那需要进行字符串拼接
		            sql += "and title like ? ";
		            //将用户输入的参数添加到集合
		            list.add("%" + title + "%");
		        }
		        if (content != "") {
		            sql += "and content like ? ";
		            list.add("%" + content + "%");
		        }
		        //最后将集合转化成数组
		        Object[] params = list.toArray();
		        //调用runner对象的query查询方法，并将集合返回
		        return runner.query(sql, new BeanListHandler<Board>(Board.class), params);
			} catch (SQLException e) {//捕获异常
				throw new RuntimeException(e);//抛出运行异常
			}

		}
		
		public List<Board> queryData(int currentPage,int pageSize ) {
	        /**
	         * 总结 select * from board limit (当前页页码-1)*每页显示的记录条数,每页显示的记录条数;
	         */
	        //创建QueryRunner对象,传入数据源
	        QueryRunner qr = new QueryRunner(C3p0Utils.getDs());
	        String sql="select * from board limit ?,?";
	        /**
	        String sql="select * from employee limit (当前页页码-1)*每页显示的记录条数,每页显示的记录条数;\n";
	         */
	        //起始行
	        int startNo = (currentPage-1)*pageSize;
	        List<Board> list=null;
	        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
	        try {
	            list=qr.query(sql, new BeanListHandler<Board>(Board.class), new Object[]{startNo,pageSize});
	            return list;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }
	    /**
	     * 提供一个查询总记录数的方法
	     *
	     */
	  
	    public int queryCount() {
	        //获取QueryRunner对象,传入数据源
	        QueryRunner qr = new QueryRunner(C3p0Utils.getDs());
	        String sql="select count(*) from board";
	        try {
	            Long count = (long) qr.query(sql, new ScalarHandler());
	            //将long的类型转成int类型
	            return count.intValue();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }

		//根据id查询
		public Board findById(int id) {
			try {//返回查询的信息
				return runner.query("select * from board where id=?", new BeanHandler<Board>(Board.class),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}


		}

		//更改公告
		public boolean updateBoard(Board b,int id) {
			try {//执行更改
				runner.update("update board set title=?,content=?,editor=?  where id=?",
						b.getTitle(),b.getContent(),b.getEditor(),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}


			return true;//返回true
		}
		
		

	
		public static void main(String args[]) {
			
			
			
		}
		
}
