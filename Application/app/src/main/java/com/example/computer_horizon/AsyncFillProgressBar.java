package com.example.computer_horizon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class AsyncFillProgressBar extends AsyncTask<Integer,Integer,Void> {

    private WeakReference<CallBack> callBackWeakReference;

    public AsyncFillProgressBar(CallBack callBack) {
        this.callBackWeakReference = new WeakReference<>(callBack);
    }

    public interface CallBack{
        void onUpdate(int currentValue);
        void onFinished();
    }
    @Override
    protected Void doInBackground(Integer... integers) {
        int max = integers[0];

        for(int i=0;i<max;i++){
            publishProgress(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //instructions pour mettre à jour la progress bar
        callBackWeakReference.get().onUpdate(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        callBackWeakReference.get().onFinished();
    }
}
