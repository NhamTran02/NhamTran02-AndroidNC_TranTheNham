package com.example.bai3_129;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    EditText etDelay;
    Button btnStart, btnStop;
    Timer timer;
    TimerTask timerTask;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timePicker);
        etDelay = findViewById(R.id.etDelay);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        timer = new Timer();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAlarm();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAlarm();
            }
        });
    }

    private void startAlarm() {
        int delay = Integer.parseInt(etDelay.getText().toString()) * 60 * 1000; // Delay in milliseconds

        // Create new TimerTask
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Phát âm thanh báo thức
                        if (mediaPlayer == null) {
                            mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.emcuaquakhu);
                            mediaPlayer.start();
                        } else {
                            mediaPlayer.start();
                        }
                    }
                });
            }
        };
        // Schedule the TimerTask
        timer.schedule(timerTask,delay);
    }

    private void stopAlarm() {
        if (timerTask != null) {
            timerTask.cancel();
            Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
        }
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
