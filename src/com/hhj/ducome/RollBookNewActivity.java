package com.hhj.ducome;

import com.example.ducome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RollBookNewActivity extends Activity{
	
	private EditText et_cls;
	private EditText et_rollTime;
	private Button btn_ok;
	private Button btn_cancel;
	private String clsName;
	private int stuNum;
	private int rollTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rollbooknew);
		findView();
		initView();
	}
			
	private void findView(){
		et_cls=(EditText) findViewById(R.id.et_cls_new);
		et_rollTime=(EditText) findViewById(R.id.et_rollTime_new);
		btn_ok=(Button) findViewById(R.id.btn_ok_new);
		btn_cancel=(Button) findViewById(R.id.btn_cancel_new);	
	}
	
	private void initView(){
		et_cls.setHint("请输入班级名称");
		et_rollTime.setHint("请输入次数");
		setListeners();
	}
	
	private void setListeners(){
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				Bundle bundle =new Bundle();
				bundle.putString(Constant.CLASS_NAME,et_cls.getText().toString());
				if(!et_rollTime.getText().toString().isEmpty())
					bundle.putInt(Constant.ROLL_TIME,Integer.parseInt(et_rollTime.getText().toString()));
				else
					bundle.putInt(Constant.ROLL_TIME,-1);		
				intent.putExtras(bundle);
				setResult(Constant.ROLLBOOK_BACK_OK, intent);
				RollBookNewActivity.this.finish();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Constant.ROLLBOOK_BACK_CANCEL);
				RollBookNewActivity.this.finish();
			}
		});
	}
}
