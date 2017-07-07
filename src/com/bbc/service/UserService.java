package com.bbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.bbc.model.User;
import com.bbc.util.SQLHelper;

public class UserService {

	
		//���� ��¼�û����������Ƿ���ȷ
	
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
	//����һ����ע���û�
	//ɾ��һ��˯���˺�
	//�޸��û���Ϣ
	//�����û���Ϣ
	//���ݸ������û���������������в��Ҳ����ظ��û���������Ϣ
	public User getUserByNameAndPSW(String username,String password){
		User user=new User();
		String sql="select * from users where username=? and password=?";
		String []parameters={username,password};
		user=SQLHelper.executeQuery2(sql, parameters).get(0);
		
		return user;
	}
	
}
