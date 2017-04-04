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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapterForR extends BaseAdapter{
	
	private List<RollBook> books;
	private DataSave data;
	private LayoutInflater mInflater;
	private Context mcontext;
	private int TotalBooks;
	
	public void save(){
		Log.d("TAG", "8");
		data.saveRollBooks((ArrayList<RollBook>) books);
		data.setTotalBooks(TotalBooks);
		data.saveBooksNumber();
	}
	
	public MyAdapterForR(Context context){
		mcontext=context;		
		data=new DataSave(context);
		books=data.getRollBooks();
		TotalBooks=data.getTotalBooks();
		mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
/*	
	public void addItem(final RollBook book){
		books.add(book);
		TotalBooks++;
		notifyDataSetChanged();
	}*/
	
	public void addItem(final String clsName,final int rollTime){
		TotalBooks++;
		books.add(new RollBook(clsName,rollTime,TotalBooks));
		notifyDataSetChanged();
	}
	
	public void changeItem(int position,String clsName,int rollTime){
		if(!clsName.isEmpty())
			books.get(position).setClassName(clsName);
		if(rollTime>=0)
			books.get(position).setRollTime(rollTime);
		notifyDataSetChanged();
	}
	
	public void changeItem(int position,int stuNum){
		Log.d("TAG", "5");
		books.get(position).setStudentNum(stuNum);
		notifyDataSetChanged();
	}
	
	public void deleteItem(int position){
		books.remove(position);
		notifyDataSetChanged();
	}
	
	public void deleteAll(){
		books.clear();
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {	
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		return books.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolderForB viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolderForB();
			convertView =mInflater.inflate(R.layout.activity_rollbook, null);
			viewHolder.tv_clsText=(TextView) convertView.findViewById(R.id.tv_cls);
			viewHolder.tv_stuNumText=(TextView) convertView.findViewById(R.id.tv_stuNum);
			viewHolder.tv_rollTimeText=(TextView) convertView.findViewById(R.id.tv_rollTime);
			viewHolder.btn_add=(Button) convertView.findViewById(R.id.btn_addrollTime);
			viewHolder.btn_sub=(Button) convertView.findViewById(R.id.btn_subrollTime);
			viewHolder.btn_set=(Button) convertView.findViewById(R.id.btn_bookSet);	
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolderForB)convertView.getTag();			
		}
		viewHolder.tv_clsText.setText(books.get(position).getClassName());
		viewHolder.tv_stuNumText.setText(""+books.get(position).getStudentNum());
		viewHolder.tv_rollTimeText.setText(""+books.get(position).getRollTime());
		convertView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Activity activity=(RollBookActivity)mcontext;
				Intent intent=new Intent(mcontext,StudentActivity.class);
				Bundle bundle=new Bundle();
				bundle.putInt(Constant.ROLLBOOKNO, books.get(position).getRollBookNo());
				bundle.putInt(Constant.POSITION, position);
			//	bundle.putSerializable(Constant.DATASAVE_OBJECT, data);
				intent.putExtras(bundle);
				activity.startActivityForResult(intent,Constant.ROLLBOOK_SEND);
			}
			
		});
		initListener(viewHolder,position);
		return convertView;
	}
	
	private void initListener(final ViewHolderForB viewHolder,final int position){
		viewHolder.btn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				books.get(position).addRollTime();
				notifyDataSetChanged();
			}
		});
		viewHolder.btn_sub.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				books.get(position).subRollTime();
				notifyDataSetChanged();
			}
		});
		viewHolder.btn_set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Activity activity=(RollBookActivity)mcontext;
				Intent intent=new Intent(mcontext,RollBookSetActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString(Constant.CLASS_NAME,books.get(position).getClassName());
				bundle.putInt(Constant.ROLL_TIME,books.get(position).getRollTime());
				bundle.putInt(Constant.POSITION,position);
				intent.putExtras(bundle);
				activity.startActivityForResult(intent,Constant.ROLLBOOK_SEND);
			}
		});
	}
}

class ViewHolderForB{
	public TextView tv_clsText;
	public TextView tv_stuNumText;
	public TextView tv_rollTimeText;
	public Button btn_add;
	public Button btn_sub;
	public Button btn_set;
}
