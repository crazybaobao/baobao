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
		
		//1.从资源ID查找控件		
		et_name = (EditText) findViewById(R.id.et_name);
		et_pass = (EditText) findViewById(R.id.et_pass);
		cb_checkbox=(CheckBox) findViewById(R.id.cb);
		
		//3.获取保存信息
		//获取到SharedPreference对象
		SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
		//获取sp中的内容
		String name = sp.getString("name", "");
		String pass = sp.getString("pass", "");
		
				
		//4.把数据回显至输入框
		et_name.setText(name);
		et_pass.setText(pass);
	}

	//2.写点击事件
	public void click(View v){
		if(cb_checkbox.isChecked()){
			//获取用户名和密码
			String name = et_name.getText().toString();
			String pass = et_pass.getText().toString();
			//获取到SharedPreference对象
			SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
			//得到编辑器
			Editor ed = sp.edit();
			ed.putString("name", name);
			ed.putString("pass", pass);
			ed.commit();
		}
		
	}
}
