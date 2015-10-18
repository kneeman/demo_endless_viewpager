package com.kneem.endlessviewpager;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ColorPageView extends RelativeLayout {

    private static final int LAYOUT = R.layout.custom_layout;
    private Context mContext;
    View mView;

    public ColorPageView(Context context, PageColor pageColor, int number){
        this(context, null, 0, pageColor, number);
    }


    private ColorPageView(Context context, AttributeSet attrs, int defStyleAttr, PageColor pageColor, int number) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(LAYOUT, this);
        setPageNumber(number);
        setPageBackgroundColor(pageColor);
    }

    private void setPageNumber(int number) {
        TextView numberTextView = (TextView) mView.findViewById(R.id.edittext_numeric);
        numberTextView.setText(String.valueOf(number));
    }

    private void setPageBackgroundColor(PageColor pageColor) {
        switch (pageColor){
            case BLACK:
                setBackgroundColor(Color.parseColor("#000000"));
                break;
            case WHITE:
                setBackgroundColor(Color.parseColor("#ffffff"));
                break;
            case LIGHT_GREY:
                setBackgroundColor(Color.parseColor("#d3d3d3"));
        }
    }

    public enum PageColor{
        BLACK,
        WHITE,
        LIGHT_GREY
    }

}

