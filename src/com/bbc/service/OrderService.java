package com.bbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bbc.util.SQLHelper;

public class OrderService {
	
	//����һ������
	public void addOrder(int userid){
		
		String sql="insert into `order` (orderdate,userid)values(?,?)";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		
		String []parameters={df.format(new Date()),Integer.toString(userid)};
		SQLHelper.executeUpdate(sql, parameters);
	}
	
	//��ݵ�¼�û���id�ţ�ȡ�����orderid
	public int getMaxOrderid(int userid){
		int orderid=1;
		String sql="select max(orderid) from `order` where userid=?";
		String[]parameters={Integer.toString(userid)};
		ResultSet rs=SQLHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				orderid= rs.getInt(1);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			SQLHelper.close(SQLHelper.getRs(), SQLHelper.getPs(), SQLHelper.getCt());
		}
		return orderid;
	}
	
	
}
