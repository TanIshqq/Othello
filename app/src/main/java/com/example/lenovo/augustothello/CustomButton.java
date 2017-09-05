package com.example.lenovo.augustothello;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Lenovo on 03-09-2017.
 */

public class CustomButton extends Button{
    Boolean visited;
    int colorStatus;

    public CustomButton(Context context) {
        super(context);
        setBackgroundResource(R.drawable.bg_box);
        visited = false;
        colorStatus=0;
    }

}


