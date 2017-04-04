package com.hhj.ducome;

import java.io.Serializable;
import java.util.ArrayList;

public class RollBook implements Serializable{

	private String name; 				//�༶��
	private int studentNum; 			//ѧ����
	private int rollTime;				//�ܵ�������
	final private int rollBookNo;  		//���������,����ȷ��ѧ��
	
	public RollBook(String name,int rollBookNo){
		this(name,0,rollBookNo);
	}
	
	public RollBook(String name,int rollTime,int rollBookNo){
		this.name=name;
		this.studentNum=0;
		if(rollTime<0)
			this.rollTime=0;
		else
			this.rollTime=rollTime;
		this.rollBookNo=rollBookNo;
	}
	
	public int getRollBookNo(){
		return rollBookNo;
	}
	
	public void setClassName(String name){
		this.name=name;
	}
	
	public String getClassName() {
		return name;
	}
	
	public int getStudentNum() {
		return studentNum;
	}
	
	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}
	
	public int getRollTime() {
		return rollTime;
	}
	
	public void setRollTime(int rollTime) {
		this.rollTime = rollTime;
	}
	
	public void addRollTime(){
		rollTime++;
	}
	
	public void subRollTime(){
		if(rollTime>0)
			rollTime--;
	}
	
	public void addStudent(){
		studentNum++;
	}
	
	public void subStudent(){
		if(studentNum>0)
			studentNum--;
	}
}
