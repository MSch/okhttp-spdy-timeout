package msch.okhttpbug;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        String url = "https://s.auspiel.de/sounds/effects/mischen-1.mp3";
        OkHttpClient httpClient = new OkHttpClient();
        httpClient.setReadTimeout(10, TimeUnit.SECONDS);
        Call call = httpClient.newCall(new Request.Builder().
                url(url). tag(url). get().build());
        call.enqueue(new com.squareup.okhttp.Callback() {
            @Override public void onFailure(Request request, Throwable throwable) {
                System.out.println("Failure");
            }

            @Override public void onResponse(Response response) throws IOException {
                System.out.println("GOT RESPONSE");
                byte[] responseData = response.body().bytes();
                System.out.println("READ BODY"); // this is never reached
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
