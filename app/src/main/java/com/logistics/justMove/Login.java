package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView next_btn;
    TextView login_header;
    TextView login_txt;
    EditText txt_input;
    TextView res_btn;
    ImageView arrow_bk;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        next_btn = findViewById(R.id.login_next);
        login_header = findViewById(R.id.login_header);
        login_txt = findViewById(R.id.login_text);
        txt_input = findViewById(R.id.login_text_input);
        res_btn = findViewById(R.id.login_resend);
        arrow_bk = findViewById(R.id.login_back_arrow);

        intent = new Intent(Login.this, User_login.class);

        next_btn.setOnClickListener(e -> nextBtnClick());

        arrow_bk.setOnClickListener( e -> arrowBkClick());



    }

    private void nextBtnClick () {
        if(next_btn.getTransitionName().equals("next")) {
            //if otp was succesfull sent
            login_header.setText("Verify Code");
            login_txt.setText("You're receiving a text on the number you just\n" +
                    "entered with a verification code.");
            txt_input.setHint("Code");
            next_btn.setText("verify");
            next_btn.setTransitionName("verify");
            res_btn.setVisibility(View.VISIBLE);
            intent.putExtra("mobile", txt_input.getText());
            txt_input.setText("");

        }
        else {
            //if verification is successful
            startActivity(intent);
        }
    }

    private  void arrowBkClick () {
        if(next_btn.getTransitionName().equals("verify")) {

            login_header.setText("Phone Number");
            login_txt.setText("We need your phone number so we can give you\n" +
                    "updates on your moves.");
            txt_input.setHint("Enter Phone Number");
            next_btn.setText("next");
            next_btn.setTransitionName("next");
            res_btn.setVisibility(View.INVISIBLE);
            txt_input.setText(intent.getExtras().getString("mobile"));
        }
        else {
            startActivity(new Intent(Login.this, Introduction.class));
        }

    }
}