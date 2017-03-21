package com.yuseok.android.sharedpreference;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    // 내부저장소 절대경로 가져오기 (/data/data/패키지명/files)
    String internalStoragePath; // 절대경로는 전역변수로
    final String propertyFile = "test.properties";


    EditText editEmail;
    Switch shuffle;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalStoragePath = getFilesDir().getAbsolutePath();

        editEmail = (EditText) findViewById(R.id.editEmail);
        shuffle = (Switch) findViewById(R.id.shuffle);

        layout = (RelativeLayout) findViewById(R.id.layout2);

        // firstOpen 체크가 되어있으면 도움말 레이아웃을 닫아준다/
        if("false".equals(getProperty("firstOpen"))) {
            layout.setVisibility(View.GONE);
        }
    }


    public void closeHelp(View view) {
        layout.setVisibility(View.GONE);
        saveProperty("firstOpen", "false");
    }


    public void saveSettings(View view) {

    }

    public void saveProperty(String key, String value) {
        Properties prop = new Properties();
        prop.put(key, value);


        try {
            //앱의 내부저장소 / files / test.properties 파일을 저장
            FileOutputStream fos = new FileOutputStream(internalStoragePath + "/" + propertyFile);

            prop.store(fos, "comment"); // key = value

            // 저장 후 파일을 닫아준다.
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = "";

        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(internalStoragePath + "/" + propertyFile);
            prop.load(fis);
            fis.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        prop.list(System.out); // property목록 전체 나열하기

        value = prop.getProperty(key);

        return value;
    }
}
