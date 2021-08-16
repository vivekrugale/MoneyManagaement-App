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

public class openTrade3 extends AppCompatActivity {
    EditText editTextEntry3, editTextQuantity3, editTextISL3, editTextCSL3, editTextCMP3, editTextClose3;
    TextView textViewDate3Opened, textViewStock3, textViewOpenReturns3;
    Button btnCalculate3, btnClose3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trade3);

        editTextEntry3 = findViewById(R.id.editTextEntry3);
        editTextQuantity3 = findViewById(R.id.editTextQuantity3);
        editTextISL3 = findViewById(R.id.editTextISL3);
        editTextCSL3 = findViewById(R.id.editTextCSL3);
        editTextCMP3 = findViewById(R.id.editTextCMP3);
        editTextClose3 = findViewById(R.id.editTextClose3);
        textViewDate3Opened = findViewById(R.id.textViewDate3Opened);
        textViewStock3 = findViewById(R.id.textViewStock3);
        textViewOpenReturns3 = findViewById(R.id.textViewOpenReturns3);
        btnCalculate3 = findViewById(R.id.btnCalculate3);
        btnClose3 = findViewById(R.id.btnClose3);

        SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
        if(sp.contains("spStock3")){
            String spDate3 = sp.getString("spDate3","-");
            String spStock3 = sp.getString("spStock3", "-");

            textViewDate3Opened.setText(spDate3, TextView.BufferType.EDITABLE);
            textViewStock3.setText(spStock3, TextView.BufferType.EDITABLE);
        }

        btnCalculate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int entry = Integer.parseInt(editTextEntry3.getText().toString());
                int quantity = Integer.parseInt(editTextQuantity3.getText().toString());
                int ISL = Integer.parseInt(editTextISL3.getText().toString());
                int CSL = Integer.parseInt(editTextCSL3.getText().toString());
                int CMP = Integer.parseInt(editTextCMP3.getText().toString());
                int returns = quantity*(CMP - entry);
                textViewOpenReturns3.setText(returns+"");

                SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();

                edit.putInt("returns3", returns);
                edit.apply();

                SharedPreferences sp3 = getSharedPreferences("Stock3", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp3.edit();
                edit1.putInt("entry", entry);
                edit1.putInt("quantity", quantity);
                edit1.putInt("ISL", ISL);
                edit1.putInt("CSL", CSL);
                edit1.putInt("CMP", CMP);
                edit1.putInt("returns", returns);
                edit1.apply();
            }
        });

        SharedPreferences sp1 = getSharedPreferences("Stock3", MODE_PRIVATE);
        if (sp1.contains("returns")){
            int entry = sp1.getInt("entry", 0);
            int quantity = sp1.getInt("quantity", 0);
            int ISL = sp1.getInt("ISL", 0);
            int CSL = sp1.getInt("CSL", 0);
            int CMP = sp1.getInt("CMP", 0);
            int returns = sp1.getInt("returns", 0);

            editTextEntry3.setText(entry + "", TextView.BufferType.EDITABLE);
            editTextQuantity3.setText(quantity + "", TextView.BufferType.EDITABLE);
            editTextISL3.setText(ISL + "", TextView.BufferType.EDITABLE);
            editTextCSL3.setText(CSL + "", TextView.BufferType.EDITABLE);
            editTextCMP3.setText(CMP + "", TextView.BufferType.EDITABLE);
            textViewOpenReturns3.setText(returns + "");
        }

        btnClose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock3 = textViewStock3.getText().toString();
                String openDate3 = textViewDate3Opened.getText().toString();
                String closeDate3 = editTextClose3.getText().toString();
                int ret3 = Integer.parseInt(textViewOpenReturns3.getText().toString());

                SharedPreferences sp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();
                edit.putString("cstock3", stock3);
                edit.putString("copenDate3", openDate3);
                edit.putString("cclosedDate3", closeDate3);
                edit.putInt("cret3", ret3);
                edit.apply();

                SharedPreferences sp1 = getSharedPreferences("Stock3", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp1.edit();
                edit1.remove("entry");
                edit1.remove("quantity");
                edit1.remove("ISL");
                edit1.remove("CSL");
                edit1.remove("CMP");
                edit1.remove("returns");
                edit1.apply();

                editTextEntry3.setText("");
                editTextQuantity3.setText("");
                editTextISL3.setText("");
                editTextCSL3.setText("");
                editTextCMP3.setText("");
                textViewOpenReturns3.setText("");

                Intent intent = new Intent(openTrade3.this, closedTradesList.class);
                startActivity(intent);
            }
        });

    }
}