package com.raj.fibermonitorapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by RAJ on 20-03-2018.
 */

public class CustomView extends View{

    Float startX,startY,endX,endY;
    Integer lineThickness,lineColor;
    Paint recPaint;
    RectF rectF;
    Path linePath;
    MotionEvent event1;

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


    //////init written by me and its not the default one
    void init(@Nullable AttributeSet set)
    {

        if(set==null)
            return;
        linePath=new Path();
        rectF=new RectF();
        recPaint=new Paint();
        recPaint.setStyle(Paint.Style.STROKE);
        TypedArray ta=getContext().obtainStyledAttributes(set, R.styleable.CustomView);

        lineColor=ta.getColor(R.styleable.CustomView_lineColor,Color.GREEN);
        startX=ta.getFloat(R.styleable.CustomView_startX,0);
        startY=ta.getFloat(R.styleable.CustomView_startY,0);
        endX=ta.getFloat(R.styleable.CustomView_endX,0);
        endY=ta.getFloat(R.styleable.CustomView_endY,0);
        lineThickness=ta.getInteger(R.styleable.CustomView_lineThickness,8);

        ta.recycle();

        recPaint.setStrokeWidth(lineThickness);


    }

    public void setLineColor(Integer i)
    {
        lineColor=i;
        recPaint.setColor(lineColor);
        refreshLine();
    }

    public Integer getLineColor()
    {
        return lineColor;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        linePath.moveTo(startX,startY);
        linePath.lineTo(endX,endY);
        canvas.drawPath(linePath,recPaint);
        linePath.computeBounds(rectF,true);
    }

    public void refreshLine()
    {
        invalidate();
    }

}
