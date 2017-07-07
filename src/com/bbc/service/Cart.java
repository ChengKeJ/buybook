package com.bbc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bbc.model.Book;

public class Cart {
	private HashMap<String,Book> hashMap=new HashMap<String,Book>();

	//购买一本书
	public void buyBook(String id){
		//要判断是否是第一次购买
		if(hashMap.containsKey(id)){
			Book book= hashMap.get(id);
			int nums= book.getBuynums();
			book.setBuynums(nums+1);
			
		}else{
			BookService bookService=new BookService();
			Book book= bookService.getOneBookById(id);
			hashMap.put(id, book);
		}
		
		
	}
	//删除一本书
	public void deleteBook(String id){
		hashMap.remove(id);
	}
	//清空所有的书
	public void clearBook(){
		hashMap.clear();
	}
	
	//更新购物车的购买数量
	public void updateCart(String id ,int nums){
		Book book=hashMap.get(id);
		book.setBuynums(nums);
		
	}
	//删除指定的书
	
	
	
	//自动计算价格
	public float getPrice(){
		
		float price=0.0f;
		Iterator<String> it=  hashMap.keySet().iterator();
		while(it.hasNext()){
			String key=  it.next();
			Book book= hashMap.get(key);
			price+=book.getPrice()*book.getBuynums();
		}
		return price;	
		
	}
	
	
	
	//展现购物车中的内容
	public ArrayList<Book> showMyCart(){
		
		ArrayList<Book> al=new ArrayList<Book>();
		Iterator<String> it= hashMap.keySet().iterator();
		
		while(it.hasNext()){
			String key=it.next();
			Book book=hashMap.get(key);
			al.add(book);
			
		}
		return al;
		
	}
	
	
	
	
	
	
}
