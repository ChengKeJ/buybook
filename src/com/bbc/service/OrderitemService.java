package com.bbc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.bbc.model.Book;
import com.bbc.util.SQLHelper;

public class OrderitemService {

	
	public void addOrderitem(int orderid,ArrayList<Book> al){
	//����һ��������				
		String sql="insert into `orderitem`(quantity,orderid,bookid) values(?,?,?)";
		
		for(int i=0;i<al.size();i++){
			Book book= al.get(i);
			int quantity =book.getBuynums();
			int bookid=book.getBookid();
			String []parameters={String.valueOf(quantity) ,Integer.toString(orderid),String.valueOf(bookid)};
			SQLHelper.executeUpdate(sql, parameters);
		}
		
		
		
	}
}

