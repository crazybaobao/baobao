package com.example.saveuserlogindata;



import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends Activity {
	EditText et_name;
	EditText et_pass;
	CheckBox cb_checkbox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//1.����ԴID���ҿؼ�		
		et_name = (EditText) findViewById(R.id.et_name);
		et_pass = (EditText) findViewById(R.id.et_pass);
		cb_checkbox=(CheckBox) findViewById(R.id.cb);
		
		//3.��ȡ������Ϣ
		//��ȡ��SharedPreference����
		SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
		//��ȡsp�е�����
		String name = sp.getString("name", "");
		String pass = sp.getString("pass", "");
		
				
		//4.�����ݻ����������
		et_name.setText(name);
		et_pass.setText(pass);
	}

	//2.д����¼�
	public void click(View v){
		if(cb_checkbox.isChecked()){
			//��ȡ�û���������
			String name = et_name.getText().toString();
			String pass = et_pass.getText().toString();
			//��ȡ��SharedPreference����
			SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
			//�õ��༭��
			Editor ed = sp.edit();
			ed.putString("name", name);
			ed.putString("pass", pass);
			ed.commit();
		}
		
	}
}
