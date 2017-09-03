package com.example.lenovo.augustothello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Lenovo on 03-09-2017.
 */

public class CustomButton extends Button{
    Boolean visited;
    private int player;

    public CustomButton(Context context) {
        super(context);
        setBackgroundResource(R.drawable.bg_box);
        player = MainActivity.NO_PLAYER;
        visited = false;
    }
    public void setPlayer(int player) {
        this.player = player;
        if(player == MainActivity.PLAYER_1){
            setBackgroundResource(R.drawable.bg_black);
        }
        else if(player == MainActivity.PLAYER_2){
            setBackgroundResource(R.drawable.bg_white);

        }
        else {
            setText("");
        }
    }

    public int getPlayer() {
        return player;
    }

    public boolean isEmpty(){
        return player == MainActivity.NO_PLAYER;
    }

}


