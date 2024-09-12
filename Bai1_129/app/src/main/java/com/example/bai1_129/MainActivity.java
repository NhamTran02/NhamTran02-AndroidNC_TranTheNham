package com.example.bai1_129;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tv1=findViewById(R.id.tv1);

    }
    public void readWebpage(View view) {
        DownloadWebPageTask task = new DownloadWebPageTask();

        task.execute(new String[] { "https://www.javatpoint.com" });

    }
    class DownloadWebPageTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            String response = "", s = "";
            for (String url : urls) {
                try {
                    URL urlObj=new URL(url);
                    HttpURLConnection connection=(HttpURLConnection) urlObj.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream content = connection.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    while ((s = buffer.readLine()) != null)
                        response += s;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
        protected void onPostExecute(String result){
            tv1.setText(result);
        }
    }

}