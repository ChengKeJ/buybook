package com.bbc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.bbc.model.Book;

public class Cart {
	private HashMap<String,Book> hashMap=new HashMap<String,Book>();

	//����һ����
	public void buyBook(String id){
		//Ҫ�ж��Ƿ��ǵ�һ�ι���
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
	//ɾ��һ����
	public void deleteBook(String id){
		hashMap.remove(id);
	}
	//������е���
	public void clearBook(){
		hashMap.clear();
	}
	
	//���¹��ﳵ�Ĺ�������
	public void updateCart(String id ,int nums){
		Book book=hashMap.get(id);
		book.setBuynums(nums);
		
	}
	//ɾ��ָ������
	
	
	
	//�Զ�����۸�
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
	
	
	
	//չ�ֹ��ﳵ�е�����
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
