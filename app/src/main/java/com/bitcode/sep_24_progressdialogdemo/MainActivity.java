package com.bitcode.sep_24_progressdialogdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        initializeListeners();
    }

    private void initializeViews() {
        btnProgressDialog = findViewById(R.id.btnProgressDialog);
    }

    private void initializeListeners() {
        //way 1 -- anonymous class
        btnProgressDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] fileUrls = {
                        "file1",
                        "file2",
                        "file3",
                        "file4"
                };
                new Downloader().execute(fileUrls);
            }
        });
    }

    class Downloader extends AsyncTask<String, Integer, Float> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Downloading");
            progressDialog.setMessage("Files Downloading In process");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        //doInBackGround is an abstract method
        @Override
        protected Float doInBackground(String... fileUrls) {
            for (String url : fileUrls) {
                for (int i = 0; i < 100; i++) {
                    Log.e("tag", url + i + "%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    Integer[] progress = new Integer[1];
                    progress[0] = i;
                    publishProgress(i);
                    progressDialog.setProgress(i);
                }
            }
            return 20.04f;
        }

        @Override
        protected void onPostExecute(Float aFloat) {
            super.onPostExecute(aFloat);
            btnProgressDialog.setText("Float " + aFloat);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            btnProgressDialog.setText("Progress " + values[0] + "%");
        }
    }
}