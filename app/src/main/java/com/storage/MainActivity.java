package com.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;
    EditText editText, editText1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        editText1 = findViewById(R.id.editText1);

        try{

            SQLiteDatabase myDataBase = this.openOrCreateDatabase("Users", MODE_PRIVATE,null);
            myDataBase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name1 = editText.getText().toString();
                    String age1 = editText1.getText().toString();

                    myDataBase.execSQL("INSERT INTO users (name, age) VALUES ('"+name1+"','"+age1+"')");
                    textView.setText(name1);
                    textView2.setText(age1);

                }
            });

            Cursor c = myDataBase.rawQuery("SELECT * FROM users", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();

            while(c!= null){
                textView.setText(c.getString(nameIndex));
                textView2.setText(c.getString(ageIndex));
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();

            }

        }
        catch(Exception e){

        }
    }
}