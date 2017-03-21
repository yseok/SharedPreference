package com.yuseok.android.sharedpreference;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by YS on 2017-03-21.
 */

public class PropertyUtil {
    private String PROP_FILE = "sp.properties";
    private String internalStorage;
    private static PropertyUtil instance = null; // 싱글톤
    private static Context context;

    // 생성자가 호출될 때 internalStorage를 세팅해줘야 한다.
    private PropertyUtil() {
        internalStorage = context.getFilesDir().getAbsolutePath();
    }

    public static PropertyUtil getInstance(Context ctx) {

        context = ctx;
//        PropertyUtil.context = ctx;

        // this.context = ctx; // static에서는 this를 사용할 수 없다. // this는 '나'를 새로 불러오는 것이므로
        if (instance == null) {
            instance = new PropertyUtil();
            // this.context = context; //context를 이곳에 선언하면 맨처음 불러온 액티비티의 context만을 사용하게 된다.
        }

        return instance;
    }

    public void saveProperty(String key, String value) {
        Properties prop = new Properties();
        prop.put(key, value);

        try {
            //앱의 내부저장소 / files / test.properties 파일을 저장
            FileOutputStream fos = new FileOutputStream(internalStorage + "/" + PROP_FILE);

            prop.store(fos, "comment"); // key = value

            // 저장 후 파일을 닫아준다.
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = "";

        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(internalStorage + "/" + PROP_FILE);
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        prop.list(System.out); // property목록 전체 나열하기

        value = prop.getProperty(key);

        return value;
    }
}
