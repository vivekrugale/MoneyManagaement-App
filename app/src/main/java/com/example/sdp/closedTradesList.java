package com.example.sdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class closedTradesList extends AppCompatActivity {
    TextView cstock1, cstock2, cstock3, odate1, odate2, odate3, cdate1, cdate2, cdate3, cret1, cret2, cret3;
    Button btnCcad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closed_trades_list);

        cstock1 = findViewById(R.id.tvStock1);
        cstock2 = findViewById(R.id.tvStock2);
        cstock3 = findViewById(R.id.tvStock3);
        odate1 = findViewById(R.id.tvOpenD1);
        odate2 = findViewById(R.id.tvOpenD2);
        odate3 = findViewById(R.id.tvOpenD3);
        cdate1 = findViewById(R.id.tvCloseD1);
        cdate2 = findViewById(R.id.tvCloseD2);
        cdate3 = findViewById(R.id.tvCloseD3);
        cret1 = findViewById(R.id.tvRet1);
        cret2 = findViewById(R.id.tvRet2);
        cret3 = findViewById(R.id.tvRet3);
        btnCcad = findViewById(R.id.btnCcad);

        SharedPreferences sp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);

        String stock1 = sp.getString("cstock1", "");
        String openDate1 = sp.getString("copenDate1", "");
        String closedDate1 = sp.getString("cclosedDate1", "");
        int ret1 = sp.getInt("cret1", 0);

        cstock1.setText(stock1);
        odate1.setText(openDate1);
        cdate1.setText(closedDate1);
        if (ret1 == 0){
            cret1.setText("");
        }
        else cret1.setText(ret1 + "");

        String stock2 = sp.getString("cstock2", "");
        String openDate2 = sp.getString("copenDate2", "");
        String closedDate2 = sp.getString("cclosedDate2", "");
        int ret2 = sp.getInt("cret2", 0);

        cstock2.setText(stock2);
        odate2.setText(openDate2);
        cdate2.setText(closedDate2);
        if (ret2 == 0){
            cret2.setText("");
        }
        else cret2.setText(ret2 + "");

        String stock3 = sp.getString("cstock3", "");
        String openDate3 = sp.getString("copenDate3", "");
        String closedDate3 = sp.getString("cclosedDate3", "");
        int ret3 = sp.getInt("cret3", 0);

        cstock3.setText(stock3);
        odate3.setText(openDate3);
        cdate3.setText(closedDate3);
        if (ret3 == 0){
            cret3.setText("");
        }
        else cret3.setText(ret3 + "");

        final SharedPreferences.Editor edit = sp.edit();

        edit.putInt("absret1", ret1);
        edit.putInt("absret2", ret2);
        edit.putInt("absret3", ret3);
        edit.apply();

        btnCcad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.clear();
                edit.apply();

                cstock1.setText("");
                odate1.setText("");
                cdate1.setText("");
                cret1.setText("");
                cstock2.setText("");
                odate2.setText("");
                cdate2.setText("");
                cret2.setText("");
                cstock3.setText("");
                odate3.setText("");
                cdate3.setText("");
                cret3.setText("");
            }
        });
    }
}