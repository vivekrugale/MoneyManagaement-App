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

import org.w3c.dom.Text;

public class openTrade1 extends AppCompatActivity {
    EditText editTextEntry1, editTextQuantity1, editTextISL1, editTextCSL1, editTextCMP1, editTextClose1;
    TextView textViewOpenReturns1, textViewStock1, textViewDate1Opened;
    Button btnCalculate1, btnClose1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_trade1);

        editTextEntry1 = findViewById(R.id.editTextEntry1);
        editTextQuantity1 = findViewById(R.id.editTextQuantity1);
        editTextISL1 = findViewById(R.id.editTextISL1);
        editTextCSL1 = findViewById(R.id.editTextCSL1);
        editTextCMP1 = findViewById(R.id.editTextCMP1);
        editTextClose1 = findViewById(R.id.editTextClose1);
        textViewDate1Opened = findViewById(R.id.textViewDate1Opened);
        textViewStock1 = findViewById(R.id.textViewStock1);
        textViewOpenReturns1 = findViewById(R.id.textViewOpenReturns1);
        btnCalculate1 = findViewById(R.id.btnCalculate1);
        btnClose1 = findViewById(R.id.btnClose1);

        SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
        if(sp.contains("spStock1")){
            String spDate1 = sp.getString("spDate1","-");
            String spStock1 = sp.getString("spStock1", "-");

            textViewDate1Opened.setText(spDate1, TextView.BufferType.EDITABLE);
            textViewStock1.setText(spStock1, TextView.BufferType.EDITABLE);
        }

        btnCalculate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent1 = getIntent();
//                String stock1 = intent1.getStringExtra("keystock1");
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                final DatabaseReference myRef = database.getReference("OpenTrades").child(stock1 + "'s");

                int entry = Integer.parseInt(editTextEntry1.getText().toString());
                int quantity = Integer.parseInt(editTextQuantity1.getText().toString());
                int ISL = Integer.parseInt(editTextISL1.getText().toString());
                int CSL = Integer.parseInt(editTextCSL1.getText().toString());
                int CMP = Integer.parseInt(editTextCMP1.getText().toString());
                int returns = quantity*(CMP - entry);

                SharedPreferences sp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();

                edit.putInt("returns1", returns);
                edit.apply();

//                myRef.child("entry").setValue(entry);
//                myRef.child("quantity").setValue(quantity);
//                myRef.child("ISL").setValue(ISL);
//                myRef.child("CSL").setValue(CSL);
//                myRef.child("CMP").setValue(CMP);
//                myRef.child("returns").setValue(returns);

                textViewOpenReturns1.setText(returns+"");

                //MISSION OFFLINE
                SharedPreferences sp1 = getSharedPreferences("Stock1", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp1.edit();
                edit1.putInt("entry", entry);
                edit1.putInt("quantity", quantity);
                edit1.putInt("ISL", ISL);
                edit1.putInt("CSL", CSL);
                edit1.putInt("CMP", CMP);
                edit1.putInt("returns", returns);
                edit1.apply();

            }
        });

        //MISSION OFFLINE
        SharedPreferences sp1 = getSharedPreferences("Stock1", MODE_PRIVATE);
        if (sp1.contains("returns")){
            int entry = sp1.getInt("entry", 0);
            int quantity = sp1.getInt("quantity", 0);
            int ISL = sp1.getInt("ISL", 0);
            int CSL = sp1.getInt("CSL", 0);
            int CMP = sp1.getInt("CMP", 0);
            int returns = sp1.getInt("returns", 0);

            editTextEntry1.setText(entry + "", TextView.BufferType.EDITABLE);
            editTextQuantity1.setText(quantity + "", TextView.BufferType.EDITABLE);
            editTextISL1.setText(ISL + "", TextView.BufferType.EDITABLE);
            editTextCSL1.setText(CSL + "", TextView.BufferType.EDITABLE);
            editTextCMP1.setText(CMP + "", TextView.BufferType.EDITABLE);
            textViewOpenReturns1.setText(returns + "");
        }

//        Intent intent1 = getIntent();
//        String stock1 = intent1.getStringExtra("keystock1");
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        final DatabaseReference myRef = database.getReference("OpenTrades").child(stock1 + "'s");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if (snapshot.hasChild("returns")) {
//                    String entry = snapshot.child("entry").getValue().toString();
//                    String quantity = snapshot.child("quantity").getValue().toString();
//                    String ISL = snapshot.child("ISL").getValue().toString();
//                    String CSL = snapshot.child("CSL").getValue().toString();
//                    String CMP = snapshot.child("CMP").getValue().toString();
//                    String returns = snapshot.child("returns").getValue().toString();
//
//                    editTextEntry1.setText(entry, TextView.BufferType.EDITABLE);
//                    editTextQuantity1.setText(quantity, TextView.BufferType.EDITABLE);
//                    editTextISL1.setText(ISL, TextView.BufferType.EDITABLE);
//                    editTextCSL1.setText(CSL, TextView.BufferType.EDITABLE);
//                    editTextCMP1.setText(CMP, TextView.BufferType.EDITABLE);
//                    textViewOpenReturns1.setText(returns);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        btnClose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stock1 = textViewStock1.getText().toString();
                String openDate1 = textViewDate1Opened.getText().toString();
                String closeDate1 = editTextClose1.getText().toString();
                int ret1 = Integer.parseInt(textViewOpenReturns1.getText().toString());

                SharedPreferences sp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);
                final SharedPreferences.Editor edit = sp.edit();
                edit.putString("cstock1", stock1);
                edit.putString("copenDate1", openDate1);
                edit.putString("cclosedDate1", closeDate1);
                edit.putInt("cret1", ret1);
                edit.apply();

//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                database.getReference("OpenTrades").child(stock1 + "'s").removeValue();

                SharedPreferences sp1 = getSharedPreferences("Stock1", MODE_PRIVATE);
                final SharedPreferences.Editor edit1 = sp1.edit();
                edit1.remove("entry");
                edit1.remove("quantity");
                edit1.remove("ISL");
                edit1.remove("CSL");
                edit1.remove("CMP");
                edit1.remove("returns");
                edit1.apply();

                editTextEntry1.setText("");
                editTextQuantity1.setText("");
                editTextISL1.setText("");
                editTextCSL1.setText("");
                editTextCMP1.setText("");
                textViewOpenReturns1.setText("");

                Intent intent = new Intent(openTrade1.this, closedTradesList.class);
                startActivity(intent);
            }
        });

    }
}

//btnClose should delete stock1