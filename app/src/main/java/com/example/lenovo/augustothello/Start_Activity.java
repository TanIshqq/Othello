package com.example.lenovo.augustothello;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Start_Activity extends AppCompatActivity {

    EditText edittext1;
    EditText edittext2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);
        edittext1 = (EditText)findViewById(R.id.editplayer1);
        edittext2 = (EditText) findViewById(R.id.editplayer2);
        b = (Button)findViewById(R.id.button);
        final SharedPreferences sharedPreferences = getSharedPreferences("Othello",MODE_PRIVATE);
        String name1 = sharedPreferences.getString("player1","");
        String name2 = sharedPreferences.getString("player2","");
        edittext1.setText(name1);
        edittext2.setText(name2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = edittext1.getText().toString();
                String name2 = edittext2.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("player1",name1);
                editor.putString("player2",name2);
                editor.commit();


                    Intent intent = new Intent(Start_Activity.this,MainActivity.class);
                    Bundle b = new Bundle();
                    b.putString("player1",name1);
                    b.putString("player2",name2);
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();


            }
        });
    }
}
