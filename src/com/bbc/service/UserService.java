package com.bbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bbc.model.User;
import com.bbc.util.SQLHelper;

public class UserService {

	
		//检验 登录用户名和密码是否正确
	
	public boolean checkUser(User user){
		
		
		boolean b=false;
		String sql="select * from users where username=? and password=?";
		String []parameters={user.getUsername(),user.getPassword()};
		
		ResultSet rs= SQLHelper.executeQuery(sql, parameters);
		
		try {
			if(rs.next()){
				b=true;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	}
	//增加一个新注册用户
	//删除一个睡眠账号
	//修改用户信息
	//查找用户信息
	//根据给定的用户名和密码从数据中查找并返回该用户的完整信息
	public User getUserByNameAndPSW(String username,String password){
		User user=new User();
		String sql="select * from users where username=? and password=?";
		String []parameters={username,password};
		user=SQLHelper.executeQuery2(sql, parameters).get(0);
		
		return user;
	}
	
}
