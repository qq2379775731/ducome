package com.hhj.ducome;

import com.example.ducome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StudentNewActivity extends Activity{
	
	private EditText et_stuName;
	private EditText et_stuNo;
	private EditText et_noComeTime;
	private Button btn_ok;
	private Button btn_cancel;
	private String name;
	private String stuNo;
	private int noComeTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studentnew);
		findView();
		initView();
	}
	
	private void findView(){
		et_stuName=(EditText) findViewById(R.id.et_stuName_new);
		et_stuNo=(EditText) findViewById(R.id.et_stuNo_new);
		et_noComeTime=(EditText) findViewById(R.id.et_noComeTime_new);
		btn_ok=(Button) findViewById(R.id.btn_ok_newStudent);
		btn_cancel=(Button) findViewById(R.id.btn_cancel_newStudnet);	
	}
	
	private void initView(){
		et_stuName.setHint("请输入姓名");
		et_stuNo.setHint("请输入学号");
		et_noComeTime.setHint("请输入未到次数");
		setListeners();
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
				intent.putExtras(bundle);
				setResult(Constant.STUDENT_BACK_OK, intent);
				StudentNewActivity.this.finish();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Constant.STUDENT_BACK_CANCEL);
				StudentNewActivity.this.finish();
			}
		});
	}
	
}
