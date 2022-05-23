package edu.ewubd.assignment3;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    String details;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        details=getIntent().getStringExtra("details");
        textView =findViewById(R.id.textView);
        textView.setText(details);
    }
}