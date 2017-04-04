package com.hhj.ducome;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.util.Log;

public class DataSave implements Serializable{
	
	private ArrayList<RollBook> books;
	private ArrayList<Student> stus;
	private Context context;
	private int TotalBooks;
	
	public DataSave(Context context){
		this.context=context;
		books=new ArrayList<RollBook>();
		stus=new ArrayList<Student>();
		TotalBooks=0;
	}
	
	public void save(){
		saveRollBooks(books);
		saveBooksNumber();
	}
	
	public int getTotalBooks(){
		return TotalBooks;
	}
	
	public void setTotalBooks(int TotalBooks){
		this.TotalBooks=TotalBooks;
	}
	
	private void getBooksNumber(){
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(context.openFileInput(Constant.CONFIG_SAVE_PATH));
			TotalBooks=ois.readInt();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveBooksNumber(){
		ObjectOutputStream oos; 
		try {
			oos=new ObjectOutputStream(context.openFileOutput(Constant.CONFIG_SAVE_PATH,context.MODE_PRIVATE));
			oos.writeObject(new Integer(TotalBooks));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<RollBook> getRollBooks(){
		RollBook tmpBook;
		ObjectInputStream ois=null;
		try {
			Log.d("Tag", "output");
			ois=new ObjectInputStream(context.openFileInput(Constant.ROLLBOOK_SAVE_PATH));
			while(null!=(tmpBook=(RollBook)ois.readObject())){
				books.add(tmpBook);
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return books;
	}

	public ArrayList<Student> getStudents(int rollBookNo){
		Student tmpStu;
		ObjectInputStream ois=null;
		try {			
			ois=new ObjectInputStream(context.openFileInput("Students"+rollBookNo+".data"));
			while(null!=(tmpStu=(Student)ois.readObject())){
				stus.add(tmpStu);
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return stus;
	}

	public void saveRollBooks(ArrayList<RollBook> books){
		ObjectOutputStream oos; 
		try {
			oos=new ObjectOutputStream(
					context.openFileOutput(Constant.ROLLBOOK_SAVE_PATH,context.MODE_PRIVATE));
			Iterator iterator=books.iterator();
			while(iterator.hasNext()){
				oos.writeObject(iterator.next());
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveStudnets(ArrayList<Student> students,int rollBookNo){
		ObjectOutputStream oos; 
		try {
			oos=new ObjectOutputStream(
					context.openFileOutput("Students"+rollBookNo+".data",context.MODE_PRIVATE));
			Iterator iterator=students.iterator();
			while(iterator.hasNext()){
				oos.writeObject(iterator.next());
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
