package com.hhj.ducome;

import com.example.ducome.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class StudentActivity extends Activity{
	
	private ListView listView;
	private MyAdapterForS adapterForS;
	private Student tmpStu;
	private int rollBookNo;
//	private DataSave data;
	private int rollBookPosition;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studnetlist);		
		showStudent();
	}
	
	public void showStudent(){
		listView=(ListView) findViewById(R.id.list_view2);
		Bundle bundle=getIntent().getExtras();
	//	data=(DataSave) bundle.getSerializable(Constant.DATASAVE_OBJECT);
	//	data=new DataSave(this);
		rollBookNo=bundle.getInt(Constant.ROLLBOOKNO);
		rollBookPosition=bundle.getInt(Constant.POSITION);
		adapterForS=new MyAdapterForS(this,rollBookNo);
		listView.setAdapter(adapterForS);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case Constant.STUDENT_SEND:
			if(resultCode==Constant.STUDENT_BACK_OK){
				Bundle bundle=data.getExtras();
				adapterForS.changeItem(bundle.getInt(Constant.POSITION), bundle.getString(Constant.STUDENT_NO)
						,bundle.getString(Constant.STUDENT_NAME),  bundle.getInt(Constant.NO_COME_TIME));
			}else if(resultCode==Constant.STUDENT_BACK_DELETE){
				Bundle bundle=data.getExtras();
				adapterForS.deleteItem(bundle.getInt(Constant.POSITION));
			}
			break;
		case Constant.STUDENT_SEND_NEW:
			if(resultCode==Constant.STUDENT_BACK_OK){
				Bundle bundle=data.getExtras();
				adapterForS.addItem(new Student(bundle.getString(Constant.STUDENT_NO),bundle.getString(Constant.STUDENT_NAME)
						,bundle.getInt(Constant.NO_COME_TIME),0));
			}
			break;
		default:
		}
	}
	
	@Override
	public void onBackPressed() {
		Log.d("TAG", "1");
		adapterForS.save();
		Intent intent=new Intent();
		Bundle bundle=new Bundle();
		bundle.putInt(Constant.STUDENT_NUMBER, adapterForS.getStudentNumber());
		bundle.putInt(Constant.POSITION,rollBookPosition);
		intent.putExtras(bundle);
		setResult(Constant.ROLLBOOK_BACK_RETURN, intent);
		Log.d("TAG", "2");
		super.onBackPressed();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_student, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_new_student:
			Intent intent=new Intent(this,StudentNewActivity.class);
			this.startActivityForResult(intent, Constant.STUDENT_SEND_NEW);
			break;
		case R.id.action_delete_allStudents:
			adapterForS.deleteAll();;
			break;
		default:
		}
		return super.onOptionsItemSelected(item);
	}
}
