package com.yarkov.interface_graf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenNote(View view)
    {
        setContentView(R.layout.note);
    }

    public class Note{
        public String name;
        public String text;
        public String date;
    }
    public List<Note> list_nites = new ArrayList<>();

    public void AddNote(View view){
        Note newNote = new Note();
        EditText name = findViewById(R.id.editTextTextPersonName);
        MultiAutoCompleteTextView text = findViewById(R.id.multiAutoCompleteTextView);
        newNote.name = name.getText().toString();
        newNote.text = text.getText().toString();

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        newNote.date = formatForDateNow.format(dateNow);
        if(index == -1)
            list_nites.add(newNote);
        else{
            list_nites.set(index, newNote);
            index = -1;
        }
        setContentView(R.layout.activity_main);
        onLoad();
    }

    public void onLoad()
    {
        LinearLayout parrent = findViewById(R.id.parrent);
        parrent.removeAllViews();
        System.out.print(listOfNotes.size());

        for(int i = 0; i < listOfNotes.size(); i++) {

            final int finalI = i;
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            ll.setLayoutParams(params);
            ll.setTag(i);
            ll.setOnClickListener(this::OpenNotes);

            ImageView iv = new ImageView(this);
            iv.setImageResource(R.drawable.file_icon );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
            iv.setLayoutParams(layoutParams);
            iv.setPadding(20, 20, 20, 20);

            LinearLayout ll_ver = new LinearLayout(this);
            ll_ver.setOrientation(LinearLayout.VERTICAL);
            ll_ver.setLayoutParams(params);

            TextView tv_name = new TextView(this);
            tv_name.setText(listOfNotes.get(i).name);
            LinearLayout.LayoutParams params_tv = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            tv_name.setLayoutParams(params_tv);
            tv_name.setTextColor(Color.BLACK);
            tv_name.setTextSize(18);

            TextView tv_data = new TextView(this);
            tv_data.setText(listOfNotes.get(i).date);
            tv_data.setLayoutParams(params_tv);
            tv_data.setTextColor(Color.GRAY);

            Button btn_delete = new Button(this);
            btn_delete.setText("Delete");
            LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            btn_delete.setLayoutParams(btn_params);
            btn_delete.setOnClickListener(v -> {
                listOfNotes.remove(listOfNotes.get(finalI));
                onLoad();
            });

            parrent.addView(ll);
            ll.addView(iv);
            ll.addView(ll_ver);
            ll_ver.addView(tv_name);
            ll_ver.addView(tv_data);
            ll_ver.addView(btn_delete);
        }
    }
}
