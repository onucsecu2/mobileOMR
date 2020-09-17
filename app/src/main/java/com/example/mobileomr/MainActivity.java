package com.example.mobileomr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CardView scan_card,set_ans_card;
    public static List<Integer> ans = new ArrayList<Integer>();
    public static  int num_of_q=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scan_card=(CardView)findViewById(R.id.scan);
        set_ans_card=(CardView)findViewById(R.id.set_answer);

        set_ans_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_ans_card.setEnabled(false);
                Intent intent = new Intent(MainActivity.this, BasicOMRActivity.class);
                startActivity(intent);
            }
        });
        scan_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scan_card.setEnabled(false);
                Intent intent = new Intent(MainActivity.this, CameraScanActivity.class);
                startActivity(intent);
            }
        });
    }
    private void toast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        set_ans_card.setEnabled(true);
        scan_card.setEnabled(true);
    }
}
