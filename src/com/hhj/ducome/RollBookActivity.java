package com.hhj.ducome;

import java.util.ArrayList;

import com.example.ducome.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

public class RollBookActivity extends Activity {
	
	private ListView listView;
	private MyAdapterForR adapterForR;	
	private RollBook tmpBook;
	private Button btn_add;
	private Button btn_sub;
	private Button btn_set;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rollbooklist);
		listView=(ListView) findViewById(R.id.list_view);
		showRollBooks();
	}
	
	public void showRollBooks(){
		adapterForR=new MyAdapterForR(this);
		listView.setAdapter(adapterForR);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case Constant.ROLLBOOK_SEND:
			if(resultCode==Constant.ROLLBOOK_BACK_OK){
				Bundle bundle=data.getExtras();
				adapterForR.changeItem(bundle.getInt(Constant.POSITION), bundle.getString(Constant.CLASS_NAME)
						,  bundle.getInt(Constant.ROLL_TIME));
			}else if(resultCode==Constant.ROLLBOOK_BACK_DELETE){
				Bundle bundle=data.getExtras();
				adapterForR.deleteItem(bundle.getInt(Constant.POSITION));
			}else if(resultCode==Constant.ROLLBOOK_BACK_RETURN){
				Bundle bundle=data.getExtras();
				adapterForR.changeItem(bundle.getInt(Constant.POSITION),bundle.getInt(Constant.STUDENT_NUMBER));
			}
			break;
		case Constant.ROLLBOOK_SEND_NEW:
			if(resultCode==Constant.ROLLBOOK_BACK_OK){
				Bundle bundle=data.getExtras();
				adapterForR.addItem(bundle.getString(Constant.CLASS_NAME),bundle.getInt(Constant.ROLL_TIME));
			}
			break;
		default:
		}
	}
	
	@Override
	public void onBackPressed() {
		Log.d("TAG", "6");
		adapterForR.save();
		super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_new:
			Intent intent=new Intent(this,RollBookNewActivity.class);
			this.startActivityForResult(intent, Constant.ROLLBOOK_SEND_NEW);
			break;
		case R.id.action_deleteAll:
		//	new Dialog(this).
			adapterForR.deleteAll();
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}
}

