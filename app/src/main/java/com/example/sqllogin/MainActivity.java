package com.example.sqllogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Phone;
    EditText password;
    Button signup;
    Button  signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Phone = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNum = Phone.getText().toString();
                String pass = password.getText().toString();


                if(PhoneNum.equals("")||pass.equals(""))
                        Toast.makeText(MainActivity.this, "Complete all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkusername(PhoneNum);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(PhoneNum, pass);
                        if(insert==true){
                            Toast.makeText(MainActivity.this, "Registered ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "User already exists.", Toast.LENGTH_SHORT).show();
                    }

            } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}