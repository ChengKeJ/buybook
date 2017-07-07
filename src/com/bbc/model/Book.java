package com.bbc.model;

public class Book {
	
	private int bookid;
	private int catalogid;
	private String bookname;
	private String author;
	private String publishHouse;
	private float price;
	private String img;//��ʾͼ��ķ����ͼƬ�洢��·��
	private int buynums=1;//�Ѿ�������������
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublishHouse() {
		return publishHouse;
	}
	public void setPublishHouse(String publishHouse) {
		this.publishHouse = publishHouse;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getBuynums() {
		return buynums;
	}
	public void setBuynums(int buynums) {
		this.buynums = buynums;
	}
}