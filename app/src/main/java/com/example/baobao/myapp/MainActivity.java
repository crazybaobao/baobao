package com.example.baobao.myapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    static final String AUDIO_PATH = "http://yinyueshiting.baidu.com/data2/music/238979477/124380645248400128.mp3";
    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;
    private Button add_voice;
    private Button sub_voice;
    private Button mute_voice;
    private SharedPreferences sharedPreferences;
    private static int cur_voice = 0;
    private final static int MIN_VOICE = 0;
    private final static int MAX_VOICE = 8;
    private Toast toast = null;

    private EditText file_name;
    private EditText file_content;
    private Button read_button;
    private Button save_button;
    public static int LONGTIME = Toast.LENGTH_LONG;
    private TextView tv;

    public DBAction dbHelper;
    private Button wb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        wb=(Button)findViewById(R.id.web);
        wb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent aweb =new Intent();
                aweb.setClass(getApplicationContext(),WebAction.class);
                MainActivity.this.startActivity(aweb);

            }
        });


        // 初始化控件
        final Spinner mSpinner = (Spinner) findViewById(R.id.spinner1);

        tv = (TextView) findViewById(R.id.text);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.spinnername);
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> _Adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mItems);
        //绑定 Adapter到控件
        mSpinner.setAdapter(_Adapter);

        mSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                cursor.moveToFirst();
                switch (position) {
                    case 0: {


                        do {

                            CharSequence s= cursor.getString(cursor.getColumnIndex("age"));
                            Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
                            if (s.equals("Windows")) {
                                tv.setText(cursor.getString(cursor.getColumnIndex("name")));

                            break;}
                        }

                        while (cursor.moveToNext());
                    }
                    break;
                    case 1: {
                        //SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //Cursor cursor = db.query("Book", null, null, null, null, null, null);
                        //cursor.moveToFirst();
                        do {

                            CharSequence s = cursor.getString(cursor.getColumnIndex("age"));
                            Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
                            if (s.equals("Linux")) {
                                tv.setText(cursor.getString(cursor.getColumnIndex("name")));
                            }break;

                        }
                        while (cursor.moveToNext());
                    }
                    break;
                    case 2: {
                        //SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //Cursor cursor = db.query("Book", null, null, null, null, null, null);
                        //cursor.moveToFirst();
                        do {
                            CharSequence s = cursor.getString(cursor.getColumnIndex("age"));
                            Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
                            if (s.equals("Uinx")) {
                                tv.setText(cursor.getString(cursor.getColumnIndex("name")));
                            }
                            break;
                        }
                        while (cursor.moveToNext());
                    }
                    break;
                    case 3: {
                        // SQLiteDatabase db = dbHelper.getWritableDatabase();
                        // Cursor cursor = db.query("Book", null, null, null, null, null, null);
                        // cursor.moveToFirst();
                        do {

                            CharSequence s = cursor.getString(cursor.getColumnIndex("age"));
                            Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
                            if (s.equals("google cloud")) {
                                tv.setText(cursor.getString(cursor.getColumnIndex("name")));
                            }
                            break;
                        }
                        while (cursor.moveToNext());
                    }
                    ;
                    case 4: {
                        //  SQLiteDatabase db = dbHelper.getWritableDatabase();
                        //  Cursor cursor = db.query("Book", null, null, null, null, null, null);
                        //  cursor.moveToFirst();
                        do {

                            CharSequence s = cursor.getString(cursor.getColumnIndex("age"));
                            Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
                            if (s.equals("others")) {
                                tv.setText(cursor.getString(cursor.getColumnIndex("name")));
                            }
                            break;
                        }
                        while (cursor.moveToNext());
                    }
                    break;
                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);

        add_voice = (Button) findViewById(R.id.add_voice);
        sub_voice = (Button) findViewById(R.id.sub_voice);
        mute_voice = (Button) findViewById(R.id.mute_voice);
        cur_voice = getVoicevalue(sharedPreferences);
        //Toast.makeText(MainActivity.this, "上次设置音量:" + cur_voice, Toast.LENGTH_SHORT).show();
        showTextToast("上次设置音量:" + cur_voice);

        add_voice.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (cur_voice < MAX_VOICE)
                                                 cur_voice = cur_voice + 1;
                                             String voicetext = (String) generateVoice(cur_voice);
                                             //Toast.makeText(MainActivity.this, "音量" + cur_voice + "\n" + voicetext, Toast.LENGTH_SHORT).show();
                                             showTextToast("音量" + cur_voice + "\n" + voicetext);
                                             saveVoicevalue(cur_voice, sharedPreferences);
                                         }
                                     }
        );
        sub_voice.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             if (cur_voice > MIN_VOICE)
                                                 cur_voice = cur_voice - 1;
                                             String voicetext = (String) generateVoice(cur_voice);
                                             //Toast.makeText(MainActivity.this, "音量" + cur_voice + "\n" + voicetext, Toast.LENGTH_SHORT).show();
                                             showTextToast("音量" + cur_voice + "\n" + voicetext);
                                             saveVoicevalue(cur_voice, sharedPreferences);
                                         }
                                     }
        );

        mute_voice.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              cur_voice = 0;
                                              String voicetext = (String) generateVoice(cur_voice);
                                              //Toast.makeText(MainActivity.this, "音量" + cur_voice + "\n" + voicetext, Toast.LENGTH_SHORT).show();
                                              showTextToast("音量" + cur_voice + "\n" + voicetext);
                                              saveVoicevalue(cur_voice, sharedPreferences);
                                          }
                                      }
        );

        read_button = (Button) findViewById(R.id.button2);
        save_button = (Button) findViewById(R.id.button);
        file_name = (EditText) findViewById(R.id.editText);
        file_content = (EditText) findViewById(R.id.editText2);
        tv = (TextView) findViewById(R.id.text);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_file_name = file_name.getText().toString();
                Log.d("wangyun", str_file_name);
                if (str_file_name == "")
                    showTextToast("文件名为空");
                    //Toast.makeText(MainActivity.this,"lalala",Toast.LENGTH_SHORT).show();
                else {
                    String str_file_content = file_content.getText().toString();

                    try {
                        save(str_file_name, str_file_content);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        read_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_file_name = file_name.getText().toString();
                if (str_file_name == "") {
                    showTextToast("文件名为空");
                } else {
                    String str_file_content = null;
                    try {
                        str_file_content = read(str_file_name);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    file_content.setText(str_file_content);

                }
            }
        });

        dbHelper = new DBAction(this, "Book.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put("id","1");
        values.put("age", "Windows");
        values.put("name", "这里显示Windows");
        db.insert("Book", null, values);
        values.clear();

        //values.put("id","2");
        values.put("age", "Linux");
        values.put("name", "这里显示Linux");
        db.insert("Book", null, values);
        values.clear();

        //values.put("id","3");
        values.put("age", "Uinx");
        values.put("name", "这里显示Uinx");
        db.insert("Book", null, values);
        values.clear();

        //values.put("id","4");
        values.put("age", "google cloud");
        values.put("name", "这里显示google cloud");
        db.insert("Book", null, values);
        values.clear();

        //values.put("id","5");
        values.put("age", "others");
        values.put("name", "这里显示others");
        db.insert("Book", null, values);
        values.clear();
    }

    private String read(String a) throws IOException {
        byte[] buf = new byte[1024];
        int size = 0;
        FileInputStream fileInputStream = openFileInput(a);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while ((size = fileInputStream.read(buf)) > 0) {
            byteArrayOutputStream.write(buf, 0, size);
        }
        return byteArrayOutputStream.toString();
    }

    private void save(String a, String b) throws IOException {
        FileOutputStream fileOutputStream = openFileOutput(a, Context.MODE_PRIVATE);
        fileOutputStream.write(b.getBytes());
    }

    void saveVoicevalue(int voicevalue, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("key", voicevalue);
        boolean ret = editor.commit();
        if (ret == true) {
        }
        //Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        //showTextToast("保存成功");
        else
            //Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
            showTextToast("保存失败");
    }


    private CharSequence generateVoice(int voice) {
        CharSequence str = "";
        while (voice > 0) {
            str = str + "|";
            voice--;
        }
        return str;

    }

    int getVoicevalue(SharedPreferences sharedPreferences) {
        int ret = sharedPreferences.getInt("key", 0);
        return ret;
    }

    private void showTextToast(String msg) {
        if (toast == null) {
            toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }
}


