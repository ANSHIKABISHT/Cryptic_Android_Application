package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Button enc, dec, abt;
    ViewFlipper vf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        vf = findViewById(R.id.vf);
        int[] image = {R.drawable.alphabet, R.drawable.cipher_encryption};
        for (int i = 0; i < image.length; i++) {
            flipper(image[i]);
        }

        enc = findViewById(R.id.encd);
        dec = findViewById(R.id.decd);
        enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(MainActivity.this, Encoder.class);
                startActivity(temp);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent temp = new Intent(MainActivity.this, Decoder.class);
                startActivity(temp);
            }
        });
    }

    public void flipper(int image) {
        ImageView test = new ImageView(this);
        int width = 900; // Set the desired width in pixels
        int height = 500; // Set the desired height in pixels
        test.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        test.setBackgroundResource(image);
        vf.addView(test);
        vf.setFlipInterval(3000);
        vf.setAutoStart(true);
        vf.setInAnimation(this, android.R.anim.slide_in_left);
        vf.setOutAnimation(this, android.R.anim.slide_out_right);
    }

}
