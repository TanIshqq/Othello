package com.example.lenovo.augustothello;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final public static int size = 8;
    public static final int PLAYER_BLACK = 1;
    public static final int PLAYER_WHITE = 2;
    public int currentPlayer = PLAYER_BLACK;
    boolean change = false;
    int flag1=0;
    int flag2=0;
    int result=0;
    TextView tv1;
    TextView tv2;
    TextView score1;
    TextView score2;
    LinearLayout rootLayout;
    LinearLayout[] rows = new LinearLayout[size];
    CustomButton[][] board = new CustomButton[size][size];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String playerfirst = i.getStringExtra("player1");
        String playersecond = i.getStringExtra("player2");
        rootLayout = (LinearLayout)findViewById(R.id.rootLayout);
        tv1 = (TextView)findViewById(R.id.playerone);
        tv2 = (TextView)findViewById(R.id.playertwo);
        score1 = (TextView)findViewById(R.id.scoreone);
        score2 = (TextView)findViewById(R.id.scoretwo);
        tv1.setText(playerfirst);
        tv2.setText(playersecond);
        score1.setText("" + result);
        score2.setText("" + result);
        setUpBoard();
    }

    public void setUpBoard() {
        result=0;
        change=false;
        flag1=0;
        flag2=0;
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

        board[3][3].setBackgroundResource(R.drawable.bg_black);board[3][3].visited=true;board[3][3].colorStatus=PLAYER_BLACK;
        board[4][4].setBackgroundResource(R.drawable.bg_black);board[4][4].visited=true;board[4][4].colorStatus=PLAYER_BLACK;
        board[3][4].setBackgroundResource(R.drawable.bg_white);board[3][4].visited=true;board[3][4].colorStatus=PLAYER_WHITE;
        board[4][3].setBackgroundResource(R.drawable.bg_white);board[4][3].visited=true;board[4][3].colorStatus=PLAYER_WHITE;
    }

    @Override
    public void onClick(View v) {
        int x=0,y=0;
        CustomButton current = (CustomButton) v;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==current){
                    x=i;
                    y=j;
                }
            }
        }
        if(current.visited==true){
            Toast.makeText(this,"Invalid Move",Toast.LENGTH_SHORT).show();
        }
        else if(currentPlayer==PLAYER_BLACK){
            int[] dx = {-1, -1, -1, 0, 0, +1, +1, +1};
            int[] dy = {-1, 0, +1, -1, +1, -1, 0, +1};
            for(int i=0;i<8;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || nx>7 || ny<0 || ny>7){
                    continue;
                }
                if(board[nx][ny].colorStatus==PLAYER_WHITE){
                    RecursiveCallBlack(nx-dx[i],ny-dy[i],i);
                    if(flag1==1){
                        board[nx-dx[i]][ny-dy[i]].setBackgroundResource(R.drawable.bg_black);
                        board[nx-dx[i]][ny-dy[i]].colorStatus=PLAYER_BLACK;
                        board[nx-dx[i]][ny-dy[i]].visited=true;
                        flag1=0;
                        if(change==false){
                            TogglePlayer();
                            change=true;
                        }
                    }

                }

            }
            score_count();



        }
        else if(currentPlayer==PLAYER_WHITE){
            int[] dx = {-1, -1, -1, 0, 0, +1, +1, +1};
            int[] dy = {-1, 0, +1, -1, +1, -1, 0, +1};
            for(int i=0;i<size;i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<0 || nx>7 || ny<0 || ny>7){
                    continue;
                }
                else if(board[nx][ny].colorStatus==PLAYER_BLACK){
                    RecursiveCallWhite(nx-dx[i],ny-dy[i],i);
                    if(flag2==1){
                        board[nx-dx[i]][ny-dy[i]].setBackgroundResource(R.drawable.bg_white);
                        board[nx-dx[i]][ny-dy[i]].colorStatus=PLAYER_WHITE;
                        board[nx-dx[i]][ny-dy[i]].visited=true;
                        flag2=0;
                        if(change==false){
                            TogglePlayer();
                            change=true;
                        }
                    }

                }

            }
            score_count();

        }

       win_status();
        change=false;

    }

    public void win_status() {
        int score_black = Integer.parseInt(score1.getText().toString());
        int score_white = Integer.parseInt(score2.getText().toString());
        if(score_black+score_white==64){
            String a = tv1.getText().toString();
            String b = tv2.getText().toString();
            if(score_black>score_white){
                Toast.makeText(this,a + " " + "Wins",Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this,b + " " + "Wins",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void score_count() {
        int count1 =0;
        int count2 =0;
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if(board[i][j].colorStatus==PLAYER_BLACK){
                    count1++;
                }
                if(board[i][j].colorStatus==PLAYER_WHITE){
                    count2++;
                }
            }
        }
        score1.setText("" + count1);
        score2.setText(""  + count2);
    }

    public void TogglePlayer(){
        currentPlayer = currentPlayer == PLAYER_BLACK?PLAYER_WHITE: PLAYER_BLACK;
    }

    public void RecursiveCallBlack(int x , int y ,int a){
        int[] dx = {-1, -1, -1, 0, 0, +1, +1, +1};
        int[] dy = {-1, 0, +1, -1, +1, -1, 0, +1};

            int nx = x + dx[a];
            int ny = y + dy[a];
            if(nx<0 || nx>7 || ny<0 || ny>7){
                return;
            }
            if(board[nx][ny].visited==false) {
                return;
            }
            if(board[nx][ny].colorStatus==PLAYER_BLACK) {
                flag1=1;
                return;
            }
            if(board[nx][ny].colorStatus==PLAYER_WHITE){
                RecursiveCallBlack(nx,ny,a);
                if(flag1==1){
                    board[nx][ny].setBackgroundResource(R.drawable.bg_black);
                    board[nx][ny].colorStatus=PLAYER_BLACK;
                    return;
                }
            }

    }

    private void RecursiveCallWhite(int x, int y, int a) {
        int[] dx = {-1, -1, -1, 0, 0, +1, +1, +1};
        int[] dy = {-1, 0, +1, -1, +1, -1, 0, +1};

        int nx = x + dx[a];
        int ny = y + dy[a];
        if(nx<0 || nx>7 || ny<0 || ny>7){
            return;
        }
        if(board[nx][ny].visited==false) {
            return;
        }
        if(board[nx][ny].colorStatus==PLAYER_WHITE) {
            flag2=1;
            return;
        }
        if(board[nx][ny].colorStatus==PLAYER_BLACK){
            RecursiveCallWhite(nx,ny,a);
            if(flag2==1){
                board[nx][ny].setBackgroundResource(R.drawable.bg_white);
                board[nx][ny].colorStatus=PLAYER_WHITE;
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu main_menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, main_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.reset){
            score1.setText("0");
            score2.setText("0");
            setUpBoard();
        }


        return super.onOptionsItemSelected(item);
    }

}
