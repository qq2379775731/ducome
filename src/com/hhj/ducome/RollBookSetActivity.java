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

public class RollBookSetActivity extends Activity{
	
	private EditText et_cls;
	private EditText et_rollTime;
	private Button btn_ok;
	private Button btn_cancel;
	private Button btn_delete;
	private String clsName;
	private int stuNum;
	private int rollTime;
	private int position;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rollbookset);
		getIntentData();
		findView();
		initView();		
	}
			
	private void findView(){
		et_cls=(EditText) findViewById(R.id.et_cls);
		et_rollTime=(EditText) findViewById(R.id.et_rollTime);
		btn_ok=(Button) findViewById(R.id.btn_set_ok);
		btn_cancel=(Button) findViewById(R.id.btn_set_cancel);
		btn_delete=(Button) findViewById(R.id.btn_set_delete);
	}
	
	private void initView(){
		et_cls.setHint(clsName);
		et_rollTime.setHint(""+rollTime);
		setListeners();
	}
	
	private void getIntentData(){
		Bundle bundle=getIntent().getExtras();
		clsName=bundle.getString(Constant.CLASS_NAME);
		rollTime=bundle.getInt(Constant.ROLL_TIME);
		position=bundle.getInt(Constant.POSITION);
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
				bundle.putInt(Constant.POSITION,position);
				intent.putExtras(bundle);
				setResult(Constant.ROLLBOOK_BACK_OK, intent);
				RollBookSetActivity.this.finish();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(Constant.ROLLBOOK_BACK_CANCEL);
				RollBookSetActivity.this.finish();
			}
		});
		btn_delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent =new Intent();
				intent.putExtra(Constant.POSITION, position);
				setResult(Constant.ROLLBOOK_BACK_DELETE,intent);
				RollBookSetActivity.this.finish();
			}
			
		});
	}
}
