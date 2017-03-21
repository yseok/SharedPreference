package com.yuseok.android.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {


    EditText editName;
    Switch switchShuffle;

    RelativeLayout layout;

    PropertyUtil propertyUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyUtil = PropertyUtil.getInstance(this);

        editName = (EditText) findViewById(R.id.editEmail);
        switchShuffle = (Switch) findViewById(R.id.switchShuffle);
        layout = (RelativeLayout) findViewById(R.id.layout2);

        // firstOpen 체크가 되어 있으면 도움말 레이아웃을 닫아준다
        if("false".equals(propertyUtil.getProperty("firstOpen"))){
            layout.setVisibility(View.GONE);
        }

        // 세팅된 값을 가져와서 화면에 뿌린다
        loadSetting();
    }

    public void closeHelp(View view){
        layout.setVisibility(View.GONE);
        propertyUtil.saveProperty("firstOpen", "false");
    }

    public void saveSetting(View view){
        // 1. Preference 생성하기
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        // 2. SharedPreference에 값을 입력하기 위해서는 에디터를 통해서만 가능
        SharedPreferences.Editor editor = sharedPref.edit();

        //editor.putInt( "키", "값");
        editor.putString("email",    editName.getText().toString());
        editor.putBoolean("shuffle", switchShuffle.isChecked());

        // 3. 입력된 값을 반영
        editor.commit();
    }

    public void loadSetting(){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        // 프로퍼티 가져오기
        String email = sharedPref.getString("email", "");
        boolean shuffle = sharedPref.getBoolean("shuffle", false);

        // 화면에 세팅
        editName.setText(email);
        switchShuffle.setChecked(shuffle);
    }
}
