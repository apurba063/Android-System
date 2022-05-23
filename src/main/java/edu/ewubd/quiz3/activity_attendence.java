package edu.ewubd.quiz3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    WebView webView;
    String URL = "http://www.muthosoft.com/univ/attendance/report.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        webView = findViewById(R.id.webattendence);
        String[] keys = {"CSE489Lab", "year", "semester", "course", "section", "sid"};
        String[] values = {"true", "2022", "1", "CSE489", "1", "2018360063"};
        httpRequest(keys, values);
    }
    @SuppressLint("StaticFieldLeak")
    private void httpRequest(String[] keys, String[] values) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {

                try {
                    List<NameValuePair> voidss = new ArrayList<NameValuePair>();
                    for (int i = 0; i < keys.length; i++) {
                        voidss.add(new BasicNameValuePair(keys[i], values[i]));
                    }
                    String data = JSONParser.getInstance().makeHttpRequest(URL, "POST", voidss);
                    return data;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }
            protected void onPostExecute(String data){
                if(data != null){
                    try{
                        webView.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

        }.execute();
    }
}