package com.bytoaster.example.widget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;

import com.bytoaster.widget.LetterCircleView;
import com.bytoaster.widget.example.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by baptiste on 12/19/14.
 */
public class JavaBasedExample extends Activity {

    @InjectView(R.id.circle2) LetterCircleView circle2;
    @InjectView(R.id.circle3) LetterCircleView circle3;
    @InjectView(R.id.circle4) LetterCircleView circle4;
    @InjectView(R.id.circle5) LetterCircleView circle5;
    @InjectView(R.id.circle6) LetterCircleView circle6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.java_based_example);
        ButterKnife.inject(this);

        circle2.setContentColor(getResources().getColor(R.color.dark_red));

        circle3.setContentColor(getResources().getColor(R.color.dark_green));
        circle3.setBorderColor(getResources().getColor(R.color.white));
        circle3.setBorderSize(2);

        circle4.setContentColor(getResources().getColor(R.color.dark_purple));
        circle4.setBorderColor(getResources().getColor(R.color.white));
        circle4.setBorderSize(2);
        circle4.setShadowColor(getResources().getColor(R.color.grey_B4));
        circle4.setShadowSize(2);

        circle5.setText("B");
        circle5.setTextColor(getResources().getColor(R.color.white));
        circle5.setTextSize(25);
        circle5.setGravity(Gravity.CENTER);
        circle5.setContentColor(getResources().getColor(R.color.dark_yellow));
        circle5.setBorderColor(getResources().getColor(R.color.white));
        circle5.setBorderSize(2);
        circle5.setShadowColor(getResources().getColor(R.color.grey_B4));
        circle5.setShadowSize(2);

        circle6.setText("M");
        circle6.setTextColor(getResources().getColor(R.color.white));
        circle6.setTextSize(25);
        circle6.setLetterAlpha(122);
        circle6.setGravity(Gravity.CENTER);
        circle6.setContentColor(getResources().getColor(R.color.dark_blue));
        circle6.setBorderColor(getResources().getColor(R.color.white));
        circle6.setBorderSize(2);
        circle6.setShadowColor(getResources().getColor(R.color.grey_B4));
        circle6.setShadowSize(2);
    }
}
