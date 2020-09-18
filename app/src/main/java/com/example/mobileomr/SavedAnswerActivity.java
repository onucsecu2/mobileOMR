package com.example.mobileomr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class SavedAnswerActivity extends AppCompatActivity {
    private RecyclerView ans_RecycleView;
    private AnswerListRecycleviewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_answer);
        ans_RecycleView=(RecyclerView)findViewById(R.id.answer_recycleview);


    }
}
