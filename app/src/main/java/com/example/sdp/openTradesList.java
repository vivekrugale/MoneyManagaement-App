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

public class openTradesList extends AppCompatActivity {
    EditText editTextDate1, editTextStock1, editTextDate2, editTextStock2, editTextDate3, editTextStock3;
    Button btnTrade1, btnTrade2, btnTrade3, btnReset1, btnReset2, btnReset3, btnOcad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trades_list);

        editTextDate1 = findViewById(R.id.editTextDate1);
        editTextStock1 = findViewById(R.id.editTextStock1);
        editTextDate2 = findViewById(R.id.editTextDate2);
        editTextStock2 = findViewById(R.id.editTextStock2);
        editTextDate3 = findViewById(R.id.editTextDate3);
        editTextStock3 = findViewById(R.id.editTextStock3);
        btnTrade1 = findViewById(R.id.btnTrade1);
        btnTrade2 = findViewById(R.id.btnTrade2);
        btnTrade3 = findViewById(R.id.btnTrade3);
        btnReset1 = findViewById(R.id.btnReset1);
        btnReset2 = findViewById(R.id.btnReset2);
        btnReset3 = findViewById(R.id.btnReset3);
        btnOcad = findViewById(R.id.btnOcad);

        SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
        final SharedPreferences.Editor edit = sp.edit();

        btnTrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = editTextDate1.getText().toString();
                String stock1 = editTextStock1.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("OpenTrades").child(stock1 + "'s");
                myRef.child("date").setValue(date1);
                myRef.child("stock").setValue(stock1);

                edit.putString("spDate1",date1);
                edit.putString("spStock1",stock1);
                edit.apply();

                Intent intent1 = new Intent(openTradesList.this, openTrade1.class);
                intent1.putExtra("keydate1", date1);
                intent1.putExtra("keystock1", stock1);
                startActivity(intent1);
            }
        });

        if(sp.contains("spStock1")){
            String spDate1 = sp.getString("spDate1","-");
            String spStock1 = sp.getString("spStock1", "-");

            editTextDate1.setText(spDate1, TextView.BufferType.EDITABLE);
            editTextStock1.setText(spStock1, TextView.BufferType.EDITABLE);
        }

        btnTrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date2 = editTextDate2.getText().toString();
                String stock2 = editTextStock2.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("OpenTrades").child(stock2 + "'s");
                myRef.child("date").setValue(date2);
                myRef.child("stock").setValue(stock2);

                edit.putString("spDate2",date2);
                edit.putString("spStock2",stock2);
                edit.apply();

                Intent intent2 = new Intent(openTradesList.this, openTrade2.class);
                intent2.putExtra("keydate2", date2);
                intent2.putExtra("keystock2", stock2);
                startActivity(intent2);
            }
        });

        if(sp.contains("spStock2")) {
            String spDate2 = sp.getString("spDate2","-");
            String spStock2 = sp.getString("spStock2", "-");

            editTextDate2.setText(spDate2, TextView.BufferType.EDITABLE);
            editTextStock2.setText(spStock2, TextView.BufferType.EDITABLE);
        }

        btnTrade3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date3 = editTextDate3.getText().toString();
                String stock3 = editTextStock3.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("OpenTrades").child(stock3 + "'s");
                myRef.child("date").setValue(date3);
                myRef.child("stock").setValue(stock3);

                edit.putString("spDate3",date3);
                edit.putString("spStock3",stock3);
                edit.apply();

                Intent intent3 = new Intent(openTradesList.this, openTrade3.class);
                intent3.putExtra("keydate3", date3);
                intent3.putExtra("keystock3", stock3);
                startActivity(intent3);
            }
        });

        if(sp.contains("spStock3")) {
            String spDate3 = sp.getString("spDate3","-");
            String spStock3 = sp.getString("spStock3", "-");

            editTextDate3.setText(spDate3, TextView.BufferType.EDITABLE);
            editTextStock3.setText(spStock3, TextView.BufferType.EDITABLE);
        }

        btnReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock1 = editTextStock1.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("OpenTrades").child(stock1 + "'s").removeValue();

                edit.remove("returns1");
                edit.remove("spDate1");
                edit.remove("spStock1");
                edit.apply();

                editTextDate1.setText("");
                editTextStock1.setText("");
            }
        });

        btnReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock2 = editTextStock2.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("OpenTrades").child(stock2 + "'s").removeValue();

                edit.remove("returns2");
                edit.remove("spDate2");
                edit.remove("spStock2");
                edit.apply();

                editTextDate2.setText("");
                editTextStock2.setText("");
            }
        });

        btnReset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock3 = editTextStock3.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("OpenTrades").child(stock3 + "'s").removeValue();

                edit.remove("returns3");
                edit.remove("spDate3");
                edit.remove("spStock3");
                edit.apply();

                editTextDate3.setText("");
                editTextStock3.setText("");
            }
        });

        btnOcad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.clear();
                edit.apply();

                editTextDate1.setText("");
                editTextStock1.setText("");
                editTextDate2.setText("");
                editTextStock2.setText("");
                editTextDate3.setText("");
                editTextStock3.setText("");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("OpenTrades").removeValue();
            }
        });

    }
}