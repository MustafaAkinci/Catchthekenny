package com.akinci.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeText= (TextView) findViewById(R.id.timeText);
        scoreText= (TextView) findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImage();
        score=0;
        new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("time: "+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("time off");
                handler.removeCallbacks(runnable);
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("restart ?");
                alert.setMessage("are you sure to restart game ?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"game over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void increaseScore(View view){
        score=score+1;
        scoreText.setText("score: "+score);
    }
    public void hideImage(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image :imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random rnd=new Random();
                int a=rnd.nextInt(9);
                imageArray[a].setVisibility(View.VISIBLE);
                handler.postDelayed(this,1000);


            }
        };
        handler.post(runnable);

    }
}