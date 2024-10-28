package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import model.User;
import utils.C3p0Utils;




public class UserDao {
		//加载数据库连接
		private QueryRunner runner=new QueryRunner(C3p0Utils.getDs());
		
		public boolean insertUser(User user) {
			try {
				
				runner.update("insert into user(username,password,type,sex,realname)values(?,?,?,?,?)",
						user.getUsername(),user.getPassword(),user.getType(),user.getSex(),user.getRealname());
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return true;//鎴愬姛杩斿洖true


			
		}
		
	
		public int delete(int id) {
			try {
				
				runner.update("delete from user where id=?",id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			return 1;
		}
		
		
		
		
		public List<User> findAll() {
			try {
				return runner.query("select * from user", new BeanListHandler<User>(User.class));
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		
		public List<User> findMyInfo(int id) {
			try {
				return runner.query("select * from user where id=?", new BeanListHandler<User>(User.class),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		
		//根据
		public User findByRealname(String realname) {
			try {
				return runner.query("select * from user where realname=?", new BeanHandler<User>(User.class),realname);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}

	
		
		public User findById(int id) {
			try {
				return runner.query("select * from user where id=?", new BeanHandler<User>(User.class),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}


		}
		

		
		public boolean updateUser(User user,int id) {
			try {
				runner.update("update user set username=?,password=?,type=?,sex=?,realname=? where id=?",
						user.getUsername(),user.getPassword(),user.getType(),user.getSex(),user.getRealname(),id);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}


			return true;
		}
		
		public boolean updateUserPwd(User user,String username) {
			try {
				runner.update("update user set password=? where username=?",
						user.getPassword(),username);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}


			return true;
		}

		public User findByUserName(String username) {
			try {
				return runner.query("select * from user where username=?", new BeanHandler<User>(User.class),username);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		public User login(String username,String password) {
			try {
				return runner.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),username,password);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		public static void main(String args[]) {
			UserDao userDao=new UserDao();
			User user=userDao.findByUserName("admin");
			
			
			System.out.println(user.getPassword());
		}
		
}
