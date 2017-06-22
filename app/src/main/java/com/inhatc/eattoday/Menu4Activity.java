package com.inhatc.eattoday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class Menu4Activity extends AppCompatActivity implements View.OnClickListener {

    Button btnConfirm, btnCancel;
    TextView txtMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu4);

        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(this);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        txtMenu = (TextView)findViewById(R.id.textMenu);
    }

    private static String getRandomString(int length){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();

        String chars[] =
                ("김치찌개,된장찌개,비빔밥,불고기,갈비찜," +
                        "짜장면,짬뽕,볶음밥,탕수육,군만두," +
                "모듬초밥,돈고쓰라멘,미소라멘,쇼유라멘,타코야키").split(",");

        for(int i=0; i<length; i++){
            buffer.append(chars[random.nextInt(chars.length)]);
        }
        return buffer.toString();
    }

    @Override
    public void onClick(View v) {
        if(v == btnConfirm){
            txtMenu.setText(getRandomString(1));
        }else if(v == btnCancel){
            finish();
        }
    }
}
