package edu.ewubd.quiz3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    String URL = "https://muthosoft.com/univ/attendance/report.php";
    GridView listcourse;
    ArrayList<CourseModel> courseModelArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webattendence);
        String[] keys = {"my_courses","sid"};
        String[] values = {"true","2018360063"};
        httpRequest(keys, values);
        listcourse =findViewById(R.id.grid);
        courseModelArrayList = new ArrayList<CourseModel>();
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
                        courseModelArrayList.add(new CourseModel(data));

                        CourseGVAdapter adapter = new CourseGVAdapter(MainActivity.this, courseModelArrayList);
                        listcourse.setAdapter(adapter);
                        // webView.loadDataWithBaseURL(null,data,"text/html","UTF-8",null);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

        }.execute();
    }
    public class CourseGVAdapter extends ArrayAdapter<CourseModel> {
        public CourseGVAdapter(@NonNull Context context, ArrayList<CourseModel> courseModelArrayList) {
            super(context, 0, courseModelArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listitemView = convertView;
            if (listitemView == null) {
                // Layout Inflater inflates each item to be displayed in GridView.
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.gridlist, parent, false);
            }
            CourseModel courseModel = getItem(position);
            TextView courseTV = listitemView.findViewById(R.id.textView);

            courseTV.setText(courseModel.getName());
            courseTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent4 = new Intent(MainActivity.this, AttendanceActivity.class);
                    startActivity(intent4);
                }
            });

            return listitemView;
        }
    }
}