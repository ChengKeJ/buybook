package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String [] args){
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String x=df.format(new Date());
	System.out.println(x);
	
	}
}
