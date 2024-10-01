package com.bitcode.sep_24_progressdialogdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class DownloaderThread extends AsyncTask<String,Integer,Float> {
    Handler handler;
    Context context;

    ProgressDialog progressDialog;

    public DownloaderThread(Context context, Handler handler){
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Progress");
        progressDialog.setMessage("Downloading Files....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Float doInBackground(String... fileUrls) {
        for (String url: fileUrls) {
            for (int i = 0;i<=100;i++){
                try{
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Integer [] progress = new Integer[1];
                progress[0] = i;
                publishProgress(i);
                progressDialog.setProgress(i);
            }
        }
        return 34.2f;
    }

    @Override
    protected void onPostExecute(Float aFloat) {
        super.onPostExecute(aFloat);

        Message msg = new Message();
        msg.what = 1;
        msg.obj = aFloat;
        handler.sendMessage(msg);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        Message msg = new Message();
        msg.what = 2;
        msg.obj = values;
        handler.sendMessage(msg);
    }
}