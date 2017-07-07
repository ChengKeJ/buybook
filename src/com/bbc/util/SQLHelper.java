package com.bbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import com.bbc.model.Book;
import com.bbc.model.User;;
public class SQLHelper {
	
	private static Connection ct=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	private static String url="";
	private static String user="";
	private static String password="";
	private static String driver="";
	
	private static Properties pp=null;
	private static InputStream fis=null;
	static {		
		try {
			//�������ļ��ж�ȡ������Ϣ
			pp=new Properties();
			//fis=new FileInputStream("dbinfo.properties");
			//��java web��Ŀ���棬��ȡ�ļ�Ҫ����������������������ȡ��Դ�ļ�ʱ��Ĭ�ϵ���Ŀ¼��src�¡�
			//��dbinfo.properties�ļ�����src�£�����ʹ������ķ���ȥ����
			fis=SQLHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
			//��dbinfo.properties�ļ�����src�µİ����������ں�SQLHelper.javaһ���������ϰ�·�������£�
			//fis=SQLHelper.class.getClassLoader().getResourceAsStream("com/wl/utils/dbinfo.properties");
			pp.load(fis);
			url=pp.getProperty("url");
			user=pp.getProperty("user");
			password=pp.getProperty("password");
			driver=pp.getProperty("driver");			
			Class.forName(driver);
			//
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				fis=null;
			}
		}
	}
	//�õ�����
	public static Connection getConnection(){
		try {
			ct=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	
	//дһ�������������update��䣬���� insert  delete  update����
	public static void executeUpdate(String sql,String[]parameters){
		try {
			ct=getConnection();
			ps=ct.prepareStatement(sql);
			//����ѭ����ֵ
			if(parameters!=null){
				for (int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}	
			}			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			//��������׳��쳣
			throw new RuntimeException();
		}finally{
			close(rs,ps,ct);
		}		
	}
	
	//дһ�����������update���.
	//дһ��ͨ�õĲ�ѯ���
	public static ResultSet executeQuery(String sql,String[]parameters){
		try {
			ct=getConnection();
			ps=ct.prepareStatement(sql);
			if(parameters!=null){
				for (int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return rs;
	}
		//�˴��޷���ʱ�ر���Դ��˭���ã�˭����ر���Դ��
		//���Կ��ǽ��ж��η�װ����д�ú�������������һ��ArrayList ���Ϳ����ڱ������ڲ���ʱ�ر���Դ�ˡ�
		public static ArrayList <User> executeQuery2(String sql,String[]parameters){
			ArrayList<User> al=new ArrayList<User>();
			try {
			
			
			ct=getConnection();
			ps=ct.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			
					
			rs=ps.executeQuery();
			while(rs.next()){
				User user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setTel(rs.getString(5));
				user.setGrade(rs.getInt(6));
				al.add(user);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				SQLHelper.close(rs, ps, ct);
			}
			
			return al;
		}
		public static ArrayList <Book> executeQuery3(String sql,String[]parameters){
			ArrayList<Book> al=new ArrayList<Book>();
			try {
			ct=getConnection();
			ps=ct.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			
			rs=ps.executeQuery();
			while(rs.next()){
				Book book=new Book();
				book.setBookid(rs.getInt(1));
				book.setCatalogid(rs.getInt(2));
				book.setBookname(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setPublishHouse(rs.getString(5));
				book.setPrice(rs.getFloat(6));
				book.setImg(rs.getString(7));
				
				
				al.add(book);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				SQLHelper.close(rs, ps, ct);
			}
			
			return al;
		}
	
	public static void close(ResultSet rs,Statement ps,Connection ct){
		//�ر���Դ
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				rs=null;
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ps=null;
			}
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				ct=null;
			}
		}
	}

	public static Connection getCt() {
		return ct;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}
	
}
