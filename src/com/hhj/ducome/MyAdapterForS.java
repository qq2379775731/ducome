package com.hhj.ducome;

import java.util.ArrayList;
import java.util.List;

import com.example.ducome.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapterForS extends BaseAdapter{

	private List<Student> stus;
	private DataSave data;
	private LayoutInflater mInflater;
	private Context mcontext;
	private int rollBookNo;
	
	public int getStudentNumber(){
		return stus.size();
	}
	
	public void save(){
		Log.d("TAG", "3");
		data.saveStudnets((ArrayList<Student>) stus, rollBookNo);
	}
	
	public MyAdapterForS(Context context,int rollBookNo){
		mcontext=context;
		data=new DataSave(context);
		this.rollBookNo=rollBookNo;
		stus=data.getStudents(rollBookNo);
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public MyAdapterForS(Context context,int rollBookNo,DataSave data){
		mcontext=context;
		this.data=data;
		this.rollBookNo=rollBookNo;
		stus=data.getStudents(rollBookNo);
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void addItem(final Student student){
		stus.add(student);
		notifyDataSetChanged();
	}
	
	public void changeItem(int position,String stuNo,String stuName,int noComeTime){
		if(!stuNo.isEmpty())
			stus.get(position).setstuNo(stuNo);
		if(!stuName.isEmpty())
			stus.get(position).setName(stuName);
		if(noComeTime>=0)
			stus.get(position).setnoComeTime(noComeTime);
		notifyDataSetChanged();
	}
		
	public void deleteItem(int position){
		stus.remove(position);
		notifyDataSetChanged();
	}
	
	public void deleteAll(){
		stus.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {	
		return stus.size();
	}

	@Override
	public Object getItem(int position) {
		return stus.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderForS viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolderForS();
			convertView =mInflater.inflate(R.layout.activity_student, null);
			viewHolder.tv_stuNo=(TextView) convertView.findViewById(R.id.tv_stuNo);
			viewHolder.tv_stuName=(TextView) convertView.findViewById(R.id.tv_stuName);
			viewHolder.tv_noComeTime=(TextView) convertView.findViewById(R.id.tv_noComeTime);
			viewHolder.btn_add=(Button) convertView.findViewById(R.id.btn_add_noComeTime);
			viewHolder.btn_sub=(Button) convertView.findViewById(R.id.btn_sub_noComeTime);
			viewHolder.btn_set=(Button) convertView.findViewById(R.id.btn_stuSet);	
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolderForS)convertView.getTag();			
		}
		viewHolder.tv_stuNo.setText(stus.get(position).getstuNo());
		viewHolder.tv_stuName.setText(stus.get(position).getName());
		viewHolder.tv_noComeTime.setText(""+stus.get(position).getnoComeTime());
		initListener(viewHolder,position);
		return convertView;
	}
	
	private void initListener(final ViewHolderForS viewHolder,final int position){
		viewHolder.btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stus.get(position).addnoComeTime();;
				notifyDataSetChanged();
			}
		});
		viewHolder.btn_sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stus.get(position).subnoComeTime();
				notifyDataSetChanged();
			}
		});
		viewHolder.btn_set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity activity=(Activity) mcontext;
				Intent intent=new Intent(mcontext,StudentSetActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString(Constant.STUDENT_NAME,stus.get(position).getName());
				bundle.putString(Constant.STUDENT_NO,stus.get(position).getstuNo());
				bundle.putInt(Constant.NO_COME_TIME,stus.get(position).getnoComeTime());
				bundle.putInt(Constant.POSITION,position);
				intent.putExtras(bundle);
				activity.startActivityForResult(intent,Constant.STUDENT_SEND);
			}
		});
	}
}

class ViewHolderForS{
	public TextView tv_stuNo;
	public TextView tv_stuName;
	public TextView tv_noComeTime;
	public Button btn_add;
	public Button btn_sub;
	public Button btn_set;
}
