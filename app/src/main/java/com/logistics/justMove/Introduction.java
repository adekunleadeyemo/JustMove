package com.logistics.justMove;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {


    ImageView nav_fwd;
    ImageView nav_bk;

    ImageView intro_img;

    RelativeLayout nav_intro1;
    RelativeLayout nav_intro2;
    RelativeLayout nav_intro3;

    TextView intro_header1;
    TextView intro_header2;

    Button get_started_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        intro_img = findViewById(R.id.intro_img);
        nav_fwd = findViewById(R.id.intro_nav_fwd);
        nav_bk = findViewById(R.id.intro_nav_bk);

        nav_intro1 = findViewById(R.id.intro_nav1);
        nav_intro3 = findViewById(R.id.intro_nav3);
        nav_intro2 = findViewById(R.id.intro_nav2);

        intro_header1 = findViewById(R.id.intro_header_1);
        intro_header2 = findViewById(R.id.intro_header_2);

        get_started_btn = findViewById(R.id.intro_get_started);

        nav_fwd.setOnClickListener(e -> forwardNavigation());

        nav_bk.setOnClickListener(e -> backwardNavigation());

        get_started_btn.setOnClickListener( e -> {
            startActivity(new Intent(Introduction.this, Login.class));
        });
    }

    private void forwardNavigation () {
        if(intro_img.getTransitionName().equals("intro_img")){
            intro_header1.setText("Don't lift a finger." + "\n"+
                    "We do the work.");
            intro_header2.setText("Every Lugg comes with 1 or 2 strong," + "\n"+
                            "professional and friendly Lugger to do" + "\n"+
                    "the heavy work for you");
            intro_img.setImageResource(R.drawable.intro_img2);
            nav_intro1.setBackgroundResource(R.drawable.outline_circle);
            nav_intro2.setBackgroundResource(R.drawable.filled_cirlcle);
            nav_fwd.setVisibility(View.VISIBLE);
            nav_bk.setVisibility(View.VISIBLE);
            intro_img.setTransitionName("intro_img2");
        } else if (intro_img.getTransitionName().equals("intro_img2")) {
            intro_header1.setText("All your stuff." + "\n"+
                    "completely covered.");
            intro_header2.setText("From the moment your stuff is in" + "\n"+
                    "our hands it will be covered for any " + "\n"+
                    "damages that may occur to your items"+ "\n"+
                    "or property during your Lugg");
            intro_img.setImageResource(R.drawable.intro_img3);
            nav_intro2.setBackgroundResource(R.drawable.outline_circle);
            nav_intro3.setBackgroundResource(R.drawable.filled_cirlcle);
            nav_fwd.setVisibility(View.INVISIBLE);
            nav_bk.setVisibility(View.VISIBLE);
            intro_img.setTransitionName("intro_img3");
        }
    }

    private void backwardNavigation () {
        if(intro_img.getTransitionName().equals("intro_img3")){
            intro_header1.setText("Don't lift a finger." + "\n"+
                    "We do the work.");
            intro_header2.setText("Every Lugg comes with 1 or 2 strong," + "\n"+
                    "professional and friendly Lugger to do" + "\n"+
                    "the heavy work for you");
            intro_img.setImageResource(R.drawable.intro_img2);
            nav_intro3.setBackgroundResource(R.drawable.outline_circle);
            nav_intro2.setBackgroundResource(R.drawable.filled_cirlcle);
            nav_fwd.setVisibility(View.VISIBLE);
            nav_bk.setVisibility(View.VISIBLE);
            intro_img.setTransitionName("intro_img2");
        } else if (intro_img.getTransitionName().equals("intro_img2")) {
            intro_header1.setText("Push a button.\n" +
                    "Get anything moved");
            intro_header2.setText("Get new furniture delivered within\n" +
                    "an houror move into your new place \n" +
                    "when it works best for you.");
            intro_img.setImageResource(R.drawable.intro_img1);
            nav_intro2.setBackgroundResource(R.drawable.outline_circle);
            nav_intro1.setBackgroundResource(R.drawable.filled_cirlcle);
            nav_fwd.setVisibility(View.VISIBLE);
            nav_bk.setVisibility(View.INVISIBLE);
            intro_img.setTransitionName("intro_img");
        }
    }
}