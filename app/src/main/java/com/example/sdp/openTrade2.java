package com.example.sdp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class openTrade2 extends AppCompatActivity {
    EditText editTextEntry2, editTextQuantity2, editTextISL2, editTextCSL2, editTextCMP2, editTextClose2;
    TextView textViewDate2Opened, textViewStock2, textViewOpenReturns2;
    Button btnCalculate2, btnClose2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trade2);

        editTextEntry2 = findViewById(R.id.editTextEntry2);
        editTextQuantity2 = findViewById(R.id.editTextQuantity2);
        editTextISL2 = findViewById(R.id.editTextISL2);
        editTextCSL2 = findViewById(R.id.editTextCSL2);
        editTextCMP2 = findViewById(R.id.editTextCMP2);
        editTextClose2 = findViewById(R.id.editTextClose2);
        textViewDate2Opened = findViewById(R.id.textViewDate1Opened);
        textViewStock2 = findViewById(R.id.textViewStock1);
        textViewOpenReturns2 = findViewById(R.id.textViewOpenReturns2);
        btnCalculate2 = findViewById(R.id.btnCalculate2);
        btnClose2 = findViewById(R.id.btnClose2);

        SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
        if(sp.contains("spStock2")){
            String spDate2 = sp.getString("spDate2","-");
            String spStock2 = sp.getString("spStock2", "-");

            textViewDate2Opened.setText(spDate2, TextView.BufferType.EDITABLE);
            textViewStock2.setText(spStock2, TextView.BufferType.EDITABLE);
        }

        btnCalculate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int entry = Integer.parseInt(editTextEntry2.getText().toString());
                int quantity = Integer.parseInt(editTextQuantity2.getText().toString());
                int ISL = Integer.parseInt(editTextISL2.getText().toString());
                int CSL = Integer.parseInt(editTextCSL2.getText().toString());
                int CMP = Integer.parseInt(editTextCMP2.getText().toString());
                int returns = quantity*(CMP - entry);
                textViewOpenReturns2.setText(returns+"");

                SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();

                edit.putInt("returns2", returns);
                edit.apply();

                SharedPreferences sp2 = getSharedPreferences("Stock2", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp2.edit();
                edit1.putInt("entry", entry);
                edit1.putInt("quantity", quantity);
                edit1.putInt("ISL", ISL);
                edit1.putInt("CSL", CSL);
                edit1.putInt("CMP", CMP);
                edit1.putInt("returns", returns);
                edit1.apply();
            }
        });

        SharedPreferences sp1 = getSharedPreferences("Stock2", MODE_PRIVATE);
        if (sp1.contains("returns")){
            int entry = sp1.getInt("entry", 0);
            int quantity = sp1.getInt("quantity", 0);
            int ISL = sp1.getInt("ISL", 0);
            int CSL = sp1.getInt("CSL", 0);
            int CMP = sp1.getInt("CMP", 0);
            int returns = sp1.getInt("returns", 0);

            editTextEntry2.setText(entry + "", TextView.BufferType.EDITABLE);
            editTextQuantity2.setText(quantity + "", TextView.BufferType.EDITABLE);
            editTextISL2.setText(ISL + "", TextView.BufferType.EDITABLE);
            editTextCSL2.setText(CSL + "", TextView.BufferType.EDITABLE);
            editTextCMP2.setText(CMP + "", TextView.BufferType.EDITABLE);
            textViewOpenReturns2.setText(returns + "");
        }

        btnClose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock2 = textViewStock2.getText().toString();
                String openDate2 = textViewDate2Opened.getText().toString();
                String closeDate2 = editTextClose2.getText().toString();
                int ret2 = Integer.parseInt(textViewOpenReturns2.getText().toString());

                SharedPreferences sp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();
                edit.putString("cstock2", stock2);
                edit.putString("copenDate2", openDate2);
                edit.putString("cclosedDate2", closeDate2);
                edit.putInt("cret2", ret2);
                edit.apply();

                SharedPreferences sp1 = getSharedPreferences("Stock2", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp1.edit();
                edit1.remove("entry");
                edit1.remove("quantity");
                edit1.remove("ISL");
                edit1.remove("CSL");
                edit1.remove("CMP");
                edit1.remove("returns");
                edit1.apply();

                editTextEntry2.setText("");
                editTextQuantity2.setText("");
                editTextISL2.setText("");
                editTextCSL2.setText("");
                editTextCMP2.setText("");
                textViewOpenReturns2.setText("");

                Intent intent = new Intent(openTrade2.this, closedTradesList.class);
                startActivity(intent);
            }
        });

    }
}