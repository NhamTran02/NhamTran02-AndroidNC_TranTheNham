package com.example.bai2_129;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    Button btn1,btn2,btn3;
    private boolean isRunningThread1=true;
    private boolean isRunningThread2=true;
    private boolean isRunningThread3=true;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv3);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);

        startThreads();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunningThread1 = !isRunningThread1;
                btn1.setText(isRunningThread1 ? "Stop":"Start");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunningThread2 = !isRunningThread2;
                btn2.setText(isRunningThread2 ? "Stop":"Start");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunningThread3 = !isRunningThread3;
                btn3.setText(isRunningThread3 ? "Stop":"Start");
            }
        });

    }
    private void startThreads(){
        //thread 1: random 50->100
        handler.postDelayed(new Runnable(){

            @Override
            public void run() {
                if (isRunningThread1){
                    Random random=new Random();
                    int n=random.nextInt(51)+50;
                    tv1.setText(String.valueOf(n));
                }
                handler.postDelayed(this, 1000);
            }
        },0);
        //thread 2: số lẻ tăng dần từ 1
        final int[] oddNumber={1};
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunningThread2){
                    tv2.setText(String.valueOf(oddNumber[0]));
                    oddNumber[0]+=2;
                }
                handler.postDelayed(this, 2500);
            }
        },0);
        //thread 3: tăng dần các số nguyên bắt đầu từ 0
        final int[] number={0};
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isRunningThread3){
                    tv3.setText(String.valueOf(number[0]));
                    number[0]++;
                }
                handler.postDelayed(this, 2000);
            }
        }, 0);

    }

}