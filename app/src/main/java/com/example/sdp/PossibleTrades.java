package com.example.sdp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PossibleTrades extends AppCompatActivity {
    EditText editTextStock1, editTextStock2, editTextStock3, editTextEntryPrice1, editTextEntryPrice2, editTextEntryPrice3, editTextSL1, editTextSL2, editTextSL3;
    TextView textViewRISK1, textViewRISK2, textViewRISK3, textViewQUA1, textViewQUA2, textViewQUA3;
    Button btnClick, btnPcad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_possible_trades);

        editTextStock1 = findViewById(R.id.editTextStock1);
        editTextStock2 = findViewById(R.id.editTextStock2);
        editTextStock3 = findViewById(R.id.editTextStock3);
        editTextEntryPrice1 = findViewById(R.id.editTextEntryPrice1);
        editTextEntryPrice2 = findViewById(R.id.editTextEntryPrice2);
        editTextEntryPrice3 = findViewById(R.id.editTextEntryPrice3);
        editTextSL1 = findViewById(R.id.editTextSL1);
        editTextSL2 = findViewById(R.id.editTextSL2);
        editTextSL3 = findViewById(R.id.editTextSL3);
        textViewRISK1 = findViewById(R.id.textViewRISK1);
        textViewRISK2 = findViewById(R.id.textViewRISK2);
        textViewRISK3 = findViewById(R.id.textViewRISK3);
        textViewQUA1 = findViewById(R.id.textViewQUA1);
        textViewQUA2 = findViewById(R.id.textViewQUA2);
        textViewQUA3 = findViewById(R.id.textViewQUA3);
        btnClick = findViewById(R.id.btnClick);
        btnPcad = findViewById(R.id.btnPcad);

        SharedPreferences sp = getSharedPreferences("PortfolioData", MODE_PRIVATE);
        final int rpt = sp.getInt("rpt", 0);

        SharedPreferences ptsp = getSharedPreferences("PossibleTradesData", MODE_PRIVATE);
        final SharedPreferences.Editor edit = ptsp.edit();

        if (ptsp.contains("e1")) {
            String xs1 = ptsp.getString("s1","-");
            String xs2 = ptsp.getString("s2","-");
            String xs3 = ptsp.getString("s3","-");
            int xe1 = ptsp.getInt("e1",0);
            int xe2 = ptsp.getInt("e2",0);
            int xe3 = ptsp.getInt("e3",0);
            int xsl1 = ptsp.getInt("sl1",0);
            int xsl2 = ptsp.getInt("sl2",0);
            int xsl3 = ptsp.getInt("sl3",0);
            float xr1 = ptsp.getFloat("r1", 0);
            float xr2 = ptsp.getFloat("r2", 0);
            float xr3 = ptsp.getFloat("r3", 0);
            int xq1 = ptsp.getInt("q1",0);
            int xq2 = ptsp.getInt("q2",0);
            int xq3 = ptsp.getInt("q3",0);

            editTextStock1.setText(xs1, TextView.BufferType.EDITABLE);
            editTextStock2.setText(xs2, TextView.BufferType.EDITABLE);
            editTextStock3.setText(xs3, TextView.BufferType.EDITABLE);
            editTextEntryPrice1.setText(xe1 + "", TextView.BufferType.EDITABLE);
            editTextEntryPrice2.setText(xe2 + "", TextView.BufferType.EDITABLE);
            editTextEntryPrice3.setText(xe3 + "", TextView.BufferType.EDITABLE);
            editTextSL1.setText(xsl1 + "", TextView.BufferType.EDITABLE);
            editTextSL2.setText(xsl2 + "", TextView.BufferType.EDITABLE);
            editTextSL3.setText(xsl3 + "", TextView.BufferType.EDITABLE);
            textViewRISK1.setText(xr1 + "");
            textViewRISK2.setText(xr2 + "");
            textViewRISK3.setText(xr3 + "");
            textViewQUA1.setText(xq1 + "");
            textViewQUA2.setText(xq2 + "");
            textViewQUA3.setText(xq3 + "");
        }

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = editTextStock1.getText().toString();
                String s2 = editTextStock2.getText().toString();
                String s3 = editTextStock3.getText().toString();

                int e1 = Integer.parseInt(editTextEntryPrice1.getText().toString());
                int e2 = Integer.parseInt(editTextEntryPrice2.getText().toString());
                int e3 = Integer.parseInt(editTextEntryPrice3.getText().toString());
                int sl1 = Integer.parseInt(editTextSL1.getText().toString());
                int sl2 = Integer.parseInt(editTextSL2.getText().toString());
                int sl3 = Integer.parseInt(editTextSL3.getText().toString());

                Integer q1,q2,q3;

                float r1 = e1-sl1; float r2 = e2-sl2; float r3 = e3-sl3;
                if (r1 == 0.0)
                    q1 = 0;
                else
                    q1 = rpt/Math.round(r1);
                if (r2 == 0.0)
                    q2 = 0;
                else
                    q2 = rpt/Math.round(r2);
                if (r3 == 0.0)
                    q3 = 0;
                else
                    q3 = rpt/Math.round(r3);

                edit.putString("s1",s1);
                edit.putString("s2",s2);
                edit.putString("s3",s3);
                edit.putInt("e1",e1);
                edit.putInt("e2",e2);
                edit.putInt("e3",e3);
                edit.putInt("sl1",sl1);
                edit.putInt("sl2",sl2);
                edit.putInt("sl3",sl3);
                edit.putFloat("r1",r1);
                edit.putFloat("r2",r2);
                edit.putFloat("r3",r3);
                edit.putInt("q1",q1);
                edit.putInt("q2",q2);
                edit.putInt("q3",q3);
                edit.apply();

                textViewRISK1.setText(r1 + "");
                textViewRISK2.setText(r2 + "");
                textViewRISK3.setText(r3 + "");

                textViewQUA1.setText(q1 + "");
                textViewQUA2.setText(q2 + "");
                textViewQUA3.setText(q3 + "");
            }
        });

        btnPcad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit.putString("s1","-");
                edit.putString("s2","-");
                edit.putString("s3","-");
                edit.putInt("e1",0);
                edit.putInt("e2",0);
                edit.putInt("e3",0);
                edit.putInt("sl1",0);
                edit.putInt("sl2",0);
                edit.putInt("sl3",0);
                edit.putFloat("r1",0);
                edit.putFloat("r2",0);
                edit.putFloat("r3",0);
                edit.putInt("q1",0);
                edit.putInt("q2",0);
                edit.putInt("q3",0);
                edit.apply();

                editTextStock1.setText("-", TextView.BufferType.EDITABLE);
                editTextStock2.setText("-", TextView.BufferType.EDITABLE);
                editTextStock3.setText("-", TextView.BufferType.EDITABLE);
                editTextEntryPrice1.setText("0", TextView.BufferType.EDITABLE);
                editTextEntryPrice2.setText("0", TextView.BufferType.EDITABLE);
                editTextEntryPrice3.setText("0", TextView.BufferType.EDITABLE);
                editTextSL1.setText("0", TextView.BufferType.EDITABLE);
                editTextSL2.setText("0", TextView.BufferType.EDITABLE);
                editTextSL3.setText("0", TextView.BufferType.EDITABLE);
                textViewRISK1.setText("0");
                textViewRISK2.setText("0");
                textViewRISK3.setText("0");
                textViewQUA1.setText("0");
                textViewQUA2.setText("0");
                textViewQUA3.setText("0");
            }
        });

    }
}