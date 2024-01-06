package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class User_login extends AppCompatActivity {

    EditText  fname;
    EditText lname;
    EditText phone_no;
    EditText email;
    TextView save;
    ImageView arrow_bk;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        fname = findViewById(R.id.user_login_fname);
        lname = findViewById(R.id.user_login_lname);
        phone_no = findViewById(R.id.user_login_phone_number);
        email = findViewById(R.id.user_login_email);

        save = findViewById(R.id.user_login_save);
        arrow_bk = findViewById(R.id.user_login_back_arrow);

 save.setOnClickListener(e -> startActivity(new Intent(User_login.this, Home_page.class)));


    }
}