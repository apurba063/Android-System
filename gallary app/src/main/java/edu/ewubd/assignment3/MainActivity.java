package edu.ewubd.assignment3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    String url,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);

        ArrayList<ImageModelClass> courseModelArrayList = new ArrayList<ImageModelClass>();

        courseModelArrayList.add(new ImageModelClass("https://muthosoft.com/univ/photos.jpeg", "This photo is of EWU taken from ground"));
        courseModelArrayList.add(new ImageModelClass("https://muthosoft.com/univ/photos/1.jpeg", "This photo is of EWU taken from north"));
        courseModelArrayList.add(new ImageModelClass("https://muthosoft.com/univ/photos/2.jpeg", "This photo is of EWU taken from south"));


        CourseGVAdapter adapter = new CourseGVAdapter(this, courseModelArrayList);
        gridView.setAdapter(adapter);
    }
    public class CourseGVAdapter extends ArrayAdapter<ImageModelClass> {
        public CourseGVAdapter(@NonNull Context context, ArrayList<ImageModelClass> courseModelArrayList) {
            super(context, 0, courseModelArrayList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View listitemView = convertView;
            if (listitemView == null) {
                listitemView = LayoutInflater.from(getContext()).inflate(R.layout.sample, parent, false);
            }
            ImageModelClass imc= getItem(position);

            ImageView impic = listitemView.findViewById(R.id.image);
            url = imc.getURL();

            Picasso.get().load(url).into(impic);
            impic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    details = imc.getDetail();
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    intent.putExtra("details",details);


                    startActivity(intent);
                }
            });

            return listitemView;
        }
    }
}