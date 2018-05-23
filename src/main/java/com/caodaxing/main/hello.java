package com.caodaxing.main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class hello {

	public static void main(String[] args) {
		Object tex1 = new text("zhangsan", 18);
		try {
			Field field = tex1.getClass().getDeclaredField("name");
			field.setAccessible(true);
			System.out.println(field.get(tex1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class text	{
	private String name;
	private int age;
	public text(String name,int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
