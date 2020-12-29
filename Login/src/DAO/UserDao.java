package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.User;

public class UserDao {
	private static final String URL="jdbc:mysql://localhost:3306/db2";
	private static final String DID="root";
	private static final String DPW="mysql";
	private Connection con=null;
	
	private static UserDao instance;
	
	public static UserDao getInstance() {
		if(instance==null) {
			instance=new UserDao();
		}
		return instance;
	}
	private UserDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL, DID, DPW);
		} catch (ClassNotFoundException e) {
			System.err.println("클래스 로딩 오류!!");
		} catch (SQLException e) {
			System.err.println("로그인 오류!!");
		}
	}
	public void insert(User user) {
		String sql="insert into user values(?,?,?,?)";
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getId());
			pst.setString(3, user.getPw());
			pst.setString(4, user.getEmail());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.err.println("insert 쿼리문 오류!!");
		}finally {
			try {
				if(pst!=null && pst.isClosed()) {
					pst.close();
				}
			} catch (SQLException e) {
				System.err.println("statement 객체 닫기 오류!!");
			}
		}
	}
	public void update(User user) {
		String sql="update user set name=?,pw=?,email=? where id=?";
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPw());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.err.println("update 쿼리문 오류!!");
		}finally {
		try {
			if(pst!=null && pst.isClosed()) {
				pst.close();
			}
		} catch (SQLException e) {
			System.err.println("statement 객체 닫기 오류!!");
		}
	}
}
	public void delete(String id) {
		String sql="delete from user where id=?";
		PreparedStatement pst=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			pst.executeUpdate();
			} catch (SQLException e) {
			System.err.println("delete 쿼리문 오류!!");
		}finally {
		try {
			if(pst!=null && pst.isClosed()) {
				pst.close();
			}
		}catch (SQLException e) {
			System.err.println("statement 객체 닫기 오류!!");
		}
	}
}
	public User selectOne(String id) {
		String sql="select * from user where id=?";
		User user=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement(sql);
			pst.setString(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				user=new User();
				user.setName(rs.getString("name"));
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.err.println("select one쿼리문오류!");
		}finally {
			try {
				if(pst!=null && pst.isClosed()) {
					pst.close();
				}
				if(rs!=null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				System.err.println("close 오류!!");
			}
		}
		return user;
	}
	public ArrayList<User> selectAll() {
		User user=null;
		ArrayList<User> userlist=new ArrayList<User>();
		String sql="select * from user";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=con.prepareStatement(sql);
			rs=pst.executeQuery();
			
			while(rs.next()) {
				user=new User();
				user.setName(rs.getString(1));
				user.setId(rs.getString(2));
				user.setPw(rs.getString(3));
				user.setEmail(rs.getString(4));	
				userlist.add(user);
			}
		} catch (SQLException e) {
			System.err.println("select all 쿼리문 오류!!");
		}finally {
			try {
				if(pst!=null && pst.isClosed()) {
					pst.close();
				}
				if(rs!=null && rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				System.err.println("close 오류!!");
			}
		}
		return userlist;
	}
}
