package com.example.mobileomr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button savebtn;
    private Button imagebtn;
    public static List<Integer> ans = new ArrayList<Integer>();
    public static  int num_of_q=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savebtn=(Button)findViewById(R.id.save_ans);
        imagebtn=(Button)findViewById(R.id.image_button);
        toast(String.valueOf(ans.isEmpty()));
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BasicOMRActivity.class);
                startActivity(intent);
            }
        });
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CameraScanActivity.class);
                startActivity(intent);
            }
        });
    }
    private void toast(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
}
