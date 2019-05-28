package com.example.eye;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Context.MODE_PRIVATE;

public class DBHelper extends SQLiteOpenHelper {
    Context mContext;
    String PACKAGE_NAME = "com.example.eye";
    String DB_NAME = "LENS.db";
    String sql;
    SQLiteDatabase db;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    //실행할 때 DB 최초 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        copyDB(mContext);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS t3");
        onCreate(db);
    }

    // DB를 복사하기
    // assets의 /db/xxxx.db 파일을 설치된 프로그램의 내부 DB공간으로 복사하기
    public void copyDB (Context mContext){
        SharedPreferences sh_Pref = mContext.getSharedPreferences("DB", MODE_PRIVATE);
        SharedPreferences.Editor toEdit = sh_Pref.edit();

        if (sh_Pref == null || sh_Pref.getInt("DB", 0) == 0) {
            Log.i("Database", "copyDB");
            AssetManager manager = mContext.getResources().getAssets();
            String folderPath = "/data/data/" + PACKAGE_NAME + "/databases";
            String filePath = "/data/data/" + PACKAGE_NAME + "/databases/" + DB_NAME;
            File folder = new File(folderPath);
            File file = new File(filePath);
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            try {
                InputStream is = manager.open(DB_NAME, AssetManager.ACCESS_BUFFER);
                BufferedInputStream bis = new BufferedInputStream(is);
                int filesize = bis.available();
                byte[] tempdata = new byte[filesize];
                if (folder.exists()) {
                } else {
                    folder.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                bos = new BufferedOutputStream(fos);
                int read = -1;
                while ((read = bis.read(tempdata, 0, filesize)) != -1) {
                    bos.write(tempdata, 0, read);
                }
                bos.flush();
                bos.close();
                fos.close();
                bis.close();
                is.close();
            } catch (IOException e) {
                Log.e("ErrorMessage : ", e.getMessage());
            }
            toEdit.putInt("DB", 1);
            toEdit.commit();
        }
    }
}