package com.example.tom.async2;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    //在畫面上按go1執行Job1Task
    public void go1(View v){
        textView = (TextView)findViewById(R.id.info);
            new Job1Task().execute();



    }
    //產生Job2Task物件並執行execute方法 將秒數3傳入
    public void go2(View v) {
        new Job2Task().execute(3);
    }

    public void go3(View v) {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
}
