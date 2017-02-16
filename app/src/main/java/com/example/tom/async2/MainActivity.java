package com.example.tom.async2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //在畫面上按go1執行Job1Task
    public void go1(View v){
        textView = (TextView)findViewById(R.id.info);
            new Job1Task().execute();
    }
    //工作五秒後 在TextView顯示DONE
    class Job1Task extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView = (TextView) findViewById(R.id.info);
            textView.setText("DONE");

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    //產生Job2Task物件並執行execute方法 將秒數3傳入
    public void go2(View v) {
        new Job2Task().execute(3);
    }
    //工作三秒後 在TextView顯示DONE
    class Job2Task extends AsyncTask <Integer,Void,Void>{

        @Override
        //執行doInBackground後自動執行onPostExecute
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView)findViewById(R.id.info);
            info.setText("DONE");
        }

        @Override
        //非同步工作執行的工作方法 因參數宣告integer 傳入的值
        //放在參數params 資料型態為整數陣列 傳入的3即放在 params[0]
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(params[0]*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }
    //產生Job3Task物件並執行execute方法 將秒數6傳入
    public void go3(View v) {
        new Job3Task().execute(6);
    }
    class Job3Task extends AsyncTask<Integer,Integer,Void>{
        TextView info;
        public Job3Task(){
            info = (TextView)findViewById(R.id.info);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE");
        }
        //使用for迴圈 由傳入值遞增到6
        @Override
        protected Void doInBackground(Integer... params) {
            for (int i=0;params[0]>i;i++){
                publishProgress(i);
                try{
                    //每次回圈執行停頓一秒
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
