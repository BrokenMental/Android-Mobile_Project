package com.inhatc.eattoday;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Menu2Activity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase myDB;
    ContentValues insertValue;
    Button btnPay, btnCancel;
    CheckBox chkMenu1, chkMenu2, chkMenu3, chkMenu4, chkMenu5;
    TextView txtResult;
    EditText edtName = null, edtPhone = null;
    int Result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btnPay = (Button) findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtResult.setText(Result + "원");

        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);

        chkMenu1 = (CheckBox) findViewById(R.id.chkMenu1);
        chkMenu1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.chkMenu1) {
                    if (isChecked) {
                        Result += 4000;
                        txtResult.setText(Result + "원");
                    } else {
                        Result -= 4000;
                        txtResult.setText(Result + "원");
                    }
                }
            }
        });
        chkMenu2 = (CheckBox) findViewById(R.id.chkMenu2);
        chkMenu2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.chkMenu2) {
                    if (isChecked) {
                        Result += 4000;
                        txtResult.setText(Result + "원");
                    } else {
                        Result -= 4000;
                        txtResult.setText(Result + "원");
                    }
                }
            }
        });
        chkMenu3 = (CheckBox) findViewById(R.id.chkMenu3);
        chkMenu3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.chkMenu3) {
                    if (isChecked) {
                        Result += 5000;
                        txtResult.setText(Result + "원");
                    } else {
                        Result -= 5000;
                        txtResult.setText(Result + "원");
                    }
                }
            }
        });
        chkMenu4 = (CheckBox) findViewById(R.id.chkMenu4);
        chkMenu4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.chkMenu4) {
                    if (isChecked) {
                        Result += 14000;
                        txtResult.setText(Result + "원");
                    } else {
                        Result -= 14000;
                        txtResult.setText(Result + "원");
                    }
                }
            }
        });
        chkMenu5 = (CheckBox) findViewById(R.id.chkMenu5);
        chkMenu5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.chkMenu5) {
                    if (isChecked) {
                        Result += 4000;
                        txtResult.setText(Result + "원");
                    } else {
                        Result -= 4000;
                        txtResult.setText(Result + "원");
                    }
                }
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        myDB = this.openOrCreateDatabase("PayInformation", MODE_PRIVATE, null);

        if (v == btnPay) {
            if (Result == 0) {
                Toast.makeText(getApplicationContext(), "음식을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            } else if (edtName.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "이름을 써 주세요.", Toast.LENGTH_SHORT).show();
            } else if (edtPhone.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "전화번호를 써 주세요.", Toast.LENGTH_SHORT).show();
            } else {
                insertValue = new ContentValues();
                insertValue.put("Name", txtResult.getText().toString());
                insertValue.put("Phone", edtName.getText().toString());
                insertValue.put("Total", edtPhone.getText().toString());
                myDB.insert("Paylist", null, insertValue);
                Toast.makeText(getApplicationContext(), "결제 완료", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else if (v == btnCancel) {
            finish();
        }
        if (myDB != null) myDB.close();
    }
}