package com.inhatc.eattoday;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase myDB;
    ArrayList<String> aryMBRList;
    ArrayAdapter<String> adtMembers;
    ListView lstView;
    String strRecord = null;
    ContentValues insertValue;
    Cursor allRCD;
    Button btnOk, btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnOk = (Button)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        btnDel = (Button)findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);

        lstView = (ListView)findViewById(R.id.lstMember);
        myDB = this.openOrCreateDatabase("PayInformation", MODE_PRIVATE, null);
        myDB.execSQL("Create table if not exists Paylist (" +
                " _id integer primary key autoincrement, " +
                "Name text not null, " +
                "Phone text not null, " +
                "Total text not null);");
        getDBData(null);
        if(myDB != null) myDB.close();
    }

    public void getDBData(String strWhere) {
        allRCD = myDB.query("Paylist", null, strWhere, null, null, null, null, null);

        aryMBRList = new ArrayList<String>();
        if (allRCD != null){
            if(allRCD.moveToFirst()) {
                do{
                    strRecord = "전화번호 : " + allRCD.getString(3)+"\t\t|이름 : "+allRCD.getString(2)+"\t\t|결제 : "+allRCD.getString(1);
                    aryMBRList.add(strRecord);
                }while(allRCD.moveToNext());
            }
        }
        adtMembers = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, aryMBRList);

        lstView.setAdapter(adtMembers);
        lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onClick(View v) {
        if(v == btnOk){
            finish();
        }else if(v == btnDel){
            myDB = this.openOrCreateDatabase("PayInformation", MODE_PRIVATE, null);
            myDB.execSQL("Drop table if exists Paylist");
            Toast.makeText(getApplicationContext(), "삭제가 완료 되었습니다.", Toast.LENGTH_SHORT).show();
            if(myDB != null) myDB.close();
            finish();
        }
    }
}
