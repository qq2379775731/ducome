package com.hhj.ducome;

import com.example.ducome.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;

public class StudentSetActivity extends Activity{
	
	private EditText et_stuName;
	private EditText et_stuNo;
	private EditText et_noComeTime;
	private Button btn_ok;
	private Button btn_cancel;
	private Button btn_delete;
	private String stuName;
	private String stuNo;
	private int noComeTime;
	private int position;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studentset);
		getIntentData();
		findView();
		initView();		
	}
			
	private void findView(){
		et_stuNo=(EditText) findViewById(R.id.et_stuNo);
		et_stuName=(EditText) findViewById(R.id.et_stuName);
		et_noComeTime=(EditText) findViewById(R.id.et_noComeTime);
		btn_ok=(Button) findViewById(R.id.btn_set_ok2);
		btn_cancel=(Button) findViewById(R.id.btn_set_cancel2);	
		btn_delete=(Button) findViewById(R.id.btn_set_delete2);
	}
	
	private void initView(){
		et_stuNo.setHint(stuNo);
		et_stuName.setHint(stuName);
		et_noComeTime.setHint(""+noComeTime);
		setListeners();
	}
	
	private void getIntentData(){
		Bundle bundle=getIntent().getExtras();
		stuNo=bundle.getString(Constant.STUDENT_NO);
		stuName=bundle.getString(Constant.STUDENT_NAME);
		noComeTime=bundle.getInt(Constant.NO_COME_TIME);
		position=bundle.getInt(Constant.POSITION);
	}
	
	private void setListeners(){
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				Bundle bundle =new Bundle();
				bundle.putString(Constant.STUDENT_NAME,et_stuName.getText().toString());
				bundle.putString(Constant.STUDENT_NO,et_stuNo.getText().toString());
				if(!et_noComeTime.getText().toString().isEmpty())
					bundle.putInt(Constant.NO_COME_TIME,Integer.parseInt(et_noComeTime.getText().toString()));
				else
					bundle.putInt(Constant.NO_COME_TIME,-1);		
				bundle.putInt(Constant.POSITION,position);
				intent.putExtras(bundle);
				setResult(Constant.STUDENT_BACK_OK, intent);
				StudentSetActivity.this.finish();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Constant.STUDENT_BACK_CANCEL);
				StudentSetActivity.this.finish();
			}
		});
		btn_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.putExtra(Constant.POSITION, position);
				setResult(Constant.STUDENT_BACK_DELETE,intent);
				StudentSetActivity.this.finish();
			}
		});
	}
}
