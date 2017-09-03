package com.example.lenovo.augustothello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final public static int size = 8;
    public static final int NO_PLAYER = 0;
    public static final int PLAYER_1 = 1;
    public static final int PLAYER_2 = 2;
    public static final int INCOMPLETE = 0;
    public static final int PLAYER_O_WON = 1;
    public static final int PLAYER_X_WON = 2;
    public static final int DRAW = 3;
    public int currentStatus = INCOMPLETE;
    public int currentPlayer = PLAYER_1;
    LinearLayout rootLayout;
    LinearLayout[] rows = new LinearLayout[size];
    CustomButton[][] board = new CustomButton[size][size];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
        setUpBoard();
    }

    public void setUpBoard() {
        rows = new LinearLayout[size];
        board = new CustomButton[size][size];
        rootLayout.removeAllViews();
        for(int i = 0;i<size;i++){

            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            params.setMargins(1,1,1,1);
            linearLayout.setLayoutParams(params);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            rows[i] = linearLayout;
            rootLayout.addView(linearLayout);

        }


        for(int i = 0;i<size;i++){
            for(int j = 0;j<size;j++){

                board[i][j] = new CustomButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1);
                params.setMargins(1,1,1,1);
                board[i][j].setLayoutParams(params);
                board[i][j].setOnClickListener(this);
                rows[i].addView(board[i][j]);
            }
        }

        board[3][3].setBackgroundResource(R.drawable.bg_black);
        board[4][4].setBackgroundResource(R.drawable.bg_black);
        board[3][4].setBackgroundResource(R.drawable.bg_white);
        board[4][3].setBackgroundResource(R.drawable.bg_white);
    }

    @Override
    public void onClick(View v) {

    }
}
