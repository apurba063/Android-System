package edu.ewubd.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username =findViewById(R.id.username);
        TextView password =findViewById(R.id.password);

        Button loginbtn = findViewById(R.id.loginbtn);
        DBHelper DB = new DBHelper(this);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
                {
                    Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
                if (username.getText().toString().equals("") && password.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "PLEASE ENTER ALL THE FIELDS", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });


    }
}