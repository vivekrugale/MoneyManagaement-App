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

public class MainActivity extends AppCompatActivity {
    TextView textViewWCS, textViewRiskPtrade, textViewOpenPL, textViewCCapital;
    EditText editTextCapital, editTextPrisk;
    Button bntCalculate, btnPossibleTrades, btnOpenTrades, btnClosedTrades, btnCAD;
    Integer ccapital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCapital = findViewById(R.id.editTextCapital);
        editTextPrisk = findViewById(R.id.editTextPrisk);
        textViewWCS = findViewById(R.id.textViewWCS);
        textViewRiskPtrade = findViewById(R.id.textViewRiskPtrade);
        textViewOpenPL = findViewById(R.id.textViewOpenPL);
        textViewCCapital = findViewById(R.id.textViewCCapital);
        bntCalculate = findViewById(R.id.btnCalculate1);
        btnPossibleTrades = findViewById(R.id.btnPossibleTrades);
        btnOpenTrades = findViewById(R.id.btnOpenTrades);
        btnClosedTrades = findViewById(R.id.btnClosedTrades);
        btnCAD = findViewById(R.id.btnCAD);

        SharedPreferences psp = getSharedPreferences("PortfolioData", MODE_PRIVATE);
        final SharedPreferences.Editor pEdit = psp.edit();

        if (psp.contains("stCapital")){
            int SPcap = psp.getInt("stCapital", 0);
            int SPprisk = psp.getInt("pRisk", 0);
            float SPwcsloss = psp.getFloat("wcsLoss", 0);
            int SPrpt = psp.getInt("rpt", 0);

            editTextCapital.setText(SPcap + "", TextView.BufferType.EDITABLE);
            editTextPrisk.setText(SPprisk + "", TextView.BufferType.EDITABLE);
            textViewWCS.setText(SPwcsloss + "");
            textViewRiskPtrade.setText(SPrpt + "");
        }

        SharedPreferences osp = getSharedPreferences("OpenTradesData", MODE_PRIVATE);
        int returns1 = osp.getInt("returns1", 0);
        int returns2 = osp.getInt("returns2", 0);
        int returns3 = osp.getInt("returns3", 0);

        int openPL = returns1 + returns2 + returns3;
        textViewOpenPL.setText(openPL + "");

        bntCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cap = Integer.parseInt(editTextCapital.getText().toString());
                int prisk = Integer.parseInt(editTextPrisk.getText().toString());
                float wcsloss = (float) (prisk/100.0)*cap;
                float riskptrade = (float) (0.01*ccapital);
                int rpt = Math.round(riskptrade);

                pEdit.putInt("stCapital", cap);
                pEdit.putInt("pRisk", prisk);
                pEdit.putFloat("wcsLoss", wcsloss);
                pEdit.putInt("rpt", rpt);
                pEdit.apply();

                textViewWCS.setText(wcsloss+ "");
                textViewRiskPtrade.setText(rpt + "");

                SharedPreferences csp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);
                int absret1 = csp.getInt("absret1", 0);
                int absret2 = csp.getInt("absret2", 0);
                int absret3 = csp.getInt("absret3", 0);

                int stcap = Integer.parseInt(editTextCapital.getText().toString());
                ccapital = stcap + absret1 + absret2 + absret3;
                textViewCCapital.setText(ccapital + "");
            }
        });

        SharedPreferences csp = getSharedPreferences("ClosedTradesData", MODE_PRIVATE);
        int absret1 = csp.getInt("absret1", 0);
        int absret2 = csp.getInt("absret2", 0);
        int absret3 = csp.getInt("absret3", 0);

        int stcap = Integer.parseInt(editTextCapital.getText().toString());
        ccapital = stcap + absret1 + absret2 + absret3;
        textViewCCapital.setText(ccapital + "");

        btnPossibleTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PossibleTrades.class));
            }
        });

        btnOpenTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), openTradesList.class));
            }
        });

        btnClosedTrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), closedTradesList.class));
            }
        });

        btnCAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pEdit.putInt("stCapital", 0);
                pEdit.remove("pRisk");
                pEdit.remove("wcsLoss");
                pEdit.remove("rpt");
                pEdit.apply();

                editTextCapital.setText("0", TextView.BufferType.EDITABLE);
                editTextPrisk.setText("0", TextView.BufferType.EDITABLE);
                textViewWCS.setText("");
                textViewRiskPtrade.setText("");
                textViewOpenPL.setText("");
                textViewCCapital.setText("");
            }
        });

    }
}