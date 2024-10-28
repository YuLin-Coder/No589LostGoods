package dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import model.Shiwu;
import utils.C3p0Utils;




public class ShiwuDao {
	//获取连接执行查询
		private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());
		//添加拾伍物品
		public boolean insertShiwu(Shiwu r) {
			try {
				//执行插入sql
				runner.update("insert into shiwu(u_id,wpname,imgUrl,detail,place,createTime,phone)values(?,?,?,?,?,?,?)",
						r.getU_id(),r.getWpname(),r.getImgUrl(),r.getDetail(),r.getPlace(),r.getCreateTime(),r.getPhone());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return true;//成功返回true


			
		}
		
		//根据id删除
		public int delete(int id) {
			try {
				//执行删除的sql
				runner.update("delete from shiwu where id=?",id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}

			return 1;//删除成功返回1表示结束
		}
		//根据物品名称删除
		public int deleteShiwu(String wpname) {
			try {
				//执行删除的sql
				runner.update("delete from shiwu where wpname=?",wpname);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}

			return 1;//删除成功返回1表示结束
		}
		
	
		
		//查询所有拾物信息
		public List<Shiwu> findAll() {
					try {
						return runner.query("select * from shiwu", new BeanListHandler<Shiwu>(Shiwu.class));//添加实体类的适配器进行类的反射
					} catch (SQLException e) {//捕获异常
						throw new RuntimeException(e);//抛出运行异常
					}

		}
		
		
		
		//根据失主查询所有拾物信息
		public List<Shiwu> findByUid(int u_id) {
			try {
				return runner.query("select * from shiwu where u_id=?", new BeanListHandler<Shiwu>(Shiwu.class),u_id);//添加实体类的适配器进行类的反射
			} catch (SQLException e) {//捕获异常
				throw new RuntimeException(e);//抛出运行异常
			}

		}
		/**
		 * 
		 * 多条件查询
		 */
		public List<Shiwu> findByMap(String wpname,String detail) {
			try {
				String sql = "select * from shiwu where 1=1 ";
		        //创建一个集合用来存储查询的参数，因为我们不清楚客户到底输入几个参数，所以用集合来存放
		        List<String> list = new ArrayList<String>();
		        if (wpname != "") {
		            //如果用户输入的pname不为空，那需要进行字符串拼接
		            sql += "and wpname like ? ";
		            //将用户输入的参数添加到集合
		            list.add("%" + wpname + "%");
		        }
		        if (detail != "") {
		            sql += "and detail like ? ";
		            list.add("%" + detail + "%");
		        }
		        //最后将集合转化成数组
		        Object[] params = list.toArray();
		        //调用runner对象的query查询方法，并将集合返回
		        return runner.query(sql, new BeanListHandler<Shiwu>(Shiwu.class), params);
			} catch (SQLException e) {//捕获异常
				throw new RuntimeException(e);//抛出运行异常
			}

		}
		
		public List<Shiwu> queryData(int currentPage,int pageSize ) {
	       
	        //创建QueryRunner对象,传入数据源
	        QueryRunner qr = new QueryRunner(C3p0Utils.getDs());
	        String sql="select * from shiwu limit ?,?";
	     
	        //起始行
	        int startNo = (currentPage-1)*pageSize;
	        List<Shiwu> list=null;
	        //todo 使用JavaBean对象的list集合的实现类 BeanListHandler类封装
	        try {
	            list=qr.query(sql, new BeanListHandler<Shiwu>(Shiwu.class), new Object[]{startNo,pageSize});
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
	        String sql="select count(*) from shiwu";
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
		public Shiwu findById(int id) {
			try {//返回查询的信息
				return runner.query("select * from shiwu where id=?", new BeanHandler<Shiwu>(Shiwu.class),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}


		}
		
		//根据认领物品查询。
		public Shiwu findByWpName(String wpname) {
			try {//返回查询的信息
				return runner.query("select * from shiwu where wpname=?", new BeanHandler<Shiwu>(Shiwu.class),wpname);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}


		}
		
		
		//更改拾物信息
		public boolean updateShiwu(Shiwu r,int id) {
			try {//执行更改
				runner.update("update shiwu set wpname=?,detail=?,place=?,createTime=?,phone=? where id=?",
						r.getWpname(),r.getDetail(),r.getPlace(),r.getCreateTime(),r.getPhone(),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);//抛出运行异常
			}
			return true;//返回true
		}
		
		

	
		public static void main(String args[]) {
			ShiwuDao ShiwuDao=new ShiwuDao();
			Shiwu Shiwu=new Shiwu();
			Shiwu.setCreateTime("2020-03-17 1:30:29");
			boolean b=ShiwuDao.insertShiwu(Shiwu);
			
			
			System.out.println(b);
		}
		
}
