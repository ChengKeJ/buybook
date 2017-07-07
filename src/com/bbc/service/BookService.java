package com.bbc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bbc.model.Book;
import com.bbc.util.SQLHelper;

public class BookService {
	
	//取锟斤拷全锟斤拷锟斤拷锟斤拷
	
	public ArrayList<Book> getAllBooks(){
		
		ArrayList<Book> al=new ArrayList<Book>();
		String sql="select * from book";
		String []parameters=null;
		al=SQLHelper.executeQuery3(sql, parameters);		
		return al;
		
	}
	
	//锟斤拷锟斤拷锟捷匡拷锟斤拷book锟斤拷锟斤拷锟斤拷锟斤拷锟� select COUNT(*) from book
	
	public int getRowCount (){
		int rowCount=0;
		String sql="select COUNT(*) from book";
		String []parameters=null;
		ResultSet rs= SQLHelper.executeQuery(sql, parameters);
		
		try {
			if(rs.next()){
				rowCount=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			SQLHelper.close(rs, SQLHelper.getPs(), SQLHelper.getCt());
		}
		
		
		return rowCount;
	}
	//锟斤拷锟絧ageSize 锟斤拷rowCount 锟斤拷锟斤拷pageCount
	public int getPageCount(int pageSize){
		//pageCount显示的行数
		int rowCount=getRowCount();
		int pageCount=0;
		if(rowCount%pageSize==0){
			pageCount=rowCount/pageSize;
		}else{
			pageCount=rowCount/pageSize+1;
		}
		return pageCount;
		
	}
	
	//锟斤拷锟絧ageNow,pageSize取锟斤拷锟斤拷页锟斤拷锟斤拷锟�
	public ArrayList<Book> getBooksBySelected(int pageNow,int pageSize){
		ArrayList<Book> al=new ArrayList<Book>();
		String sql="";
		String []parameters=null;
		
		
		if(pageNow==1){
			sql="select * from book LIMIT "+pageSize+""; 
			//sql="select top "+pageCount+" * from TUserLogin";
		}else{
			//sql="select top "+pageCount+" * from TUserLogin where id not in (select top "+(pageNow-1)*pageCount+" id from tuserlogin)";
			//sql="select * from tuserlogin limit pageCount offset (pageNow-1)*pageCount";
			sql="select * from book limit "+pageSize+" offset "+(pageNow-1)*pageSize+"";
		}
		/*
		if(pageNow==1){
			sql="select top "+pageSize+" * from book";
		}else{
			sql="select top "+pageSize+" * from book where bookid not in(select top "+
			(pageNow-1)*pageSize+" bookid from book)";
		}*/
		al= SQLHelper.executeQuery3(sql, parameters);
		
		
		return al;
		
		
	}
	//锟斤拷锟斤拷锟斤拷
	//删锟斤拷锟斤拷
	//锟斤拷锟斤拷锟斤拷
	//锟斤拷锟侥筹拷锟絠d取锟斤拷一锟斤拷锟斤拷锟斤拷锟斤拷锟�
	public Book getOneBookById(String id){
		
		Book book=new Book();
		
		String sql="select * from book where bookid=?";
		String []parameters={id};
		book= SQLHelper.executeQuery3(sql, parameters).get(0);
		
		
		
		return book;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
