package com.hhj.ducome;

import java.io.Serializable;

public class Student implements Serializable{

	final private int rollBookNo; 		//队列序号,方便记录及查询,创建后不可修改
	private String name; 				//姓名
	private String stuNo;				//学号
	private int noComeTime;				//翘课次数
	
	public Student(String stuNo,String name,int rollBookNo){
		this(stuNo,name,0,rollBookNo);
	}
	
	public Student(String stuNo,String name,int noComeTime,int rollBookNo){
		this.name=name;
		this.stuNo=stuNo;
		this.noComeTime=noComeTime;
		this.rollBookNo=rollBookNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getstuNo() {
		return stuNo;
	}
	
	public void setstuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	
	public int getnoComeTime() {
		return noComeTime;
	}
	
	public void setnoComeTime(int noComeTime) {
		if(noComeTime>0)
			this.noComeTime = noComeTime;
		else
			noComeTime=0;
	}
	
	public void addnoComeTime(){
		this.noComeTime++;
	}
	
	public void subnoComeTime(){
		if(this.noComeTime>0)
			this.noComeTime--;
	}
}
