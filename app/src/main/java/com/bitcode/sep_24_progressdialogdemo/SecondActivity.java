package com.bitcode.sep_24_progressdialogdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button btnDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] fileUrls = {
                        "file1","file2","file3","file4"
                };
                new DownloaderThread(SecondActivity.this, new Message()).execute(fileUrls);
            }
        });
    }

    class Message extends Handler{
        @Override
        public void handleMessage(@NonNull android.os.Message msg) {
            super.handleMessage(msg.getTarget().obtainMessage());
            if(msg == null || msg.obj == null){
                return;
            }

            if(msg.getTarget().hasMessages(1)){
                Float result = (Float) msg.getTarget().obtainMessage().obj;
                btnDownload.setText("result" + result);
            }

            if (msg.getTarget().hasMessages(2)){
                Integer [] progress = (Integer[]) msg.getTarget().obtainMessage().obj;
                btnDownload.setText("progress " + progress);
            }
        }
    }
}
