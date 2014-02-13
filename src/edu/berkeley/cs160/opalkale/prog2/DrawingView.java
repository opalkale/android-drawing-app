package edu.berkeley.cs160.opalkale.prog2;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
	private Path mPath;
	private Paint mPaint;
	
	private Canvas mCanvas;
	private Canvas shadowCanvas;
	
	private Bitmap colorBitmap;
	private Bitmap grayscaleBitmap;
	private Bitmap shadowBitmap;
	private Bitmap mBitmap;
	private Paint mBitmapPaint;
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	
	public DrawingView(Context context, AttributeSet attrs){
	    
		super(context, attrs);
		
		mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.crayon);
	    
	    mBitmapPaint = new Paint(Paint.DITHER_FLAG);
	    mPath = new Path();
	    
	    mPaint = new Paint();
	    mPaint.setColor(Color.GRAY);
	    mPaint.setStyle(Paint.Style.STROKE);
	    mPaint.setStrokeWidth(3);
	    
	    mCanvas = new Canvas();
	    paths.add(mPath);
	}
	

	@Override
	protected void onDraw(Canvas canvas) {
	canvas.drawBitmap(mBitmap,0,0,mBitmapPaint);
	
	    for (Path p : paths) {
	        canvas.drawPath(p, mPaint);
	    }
	}

	private float mX, mY;
	private static final float TOUCH_TOLERANCE = 4;

	private void touch_start(float x, float y) {
	    mPath.reset();
	    mPath.moveTo(x, y);
	    mX = x;
	    mY = y;
	}

	private void touch_move(float x, float y) {
	    float dx = Math.abs(x - mX);
	    float dy = Math.abs(y - mY);
	    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
	        mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
	        mX = x;
	        mY = y;
	    }
	}

	private void touch_up() {
	    mPath.lineTo(mX, mY);
	    // commit the path to our offscreen
	    mCanvas.drawPath(mPath, mPaint);
	    // kill this so we don't double draw
	    mPath = new Path();
	    paths.add(mPath);
	}


	public boolean onTouch(View arg0, MotionEvent event) {
	    float x = event.getX();
	    float y = event.getY();

	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            touch_start(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_MOVE:
	            touch_move(x, y);
	            invalidate();
	            break;
	        case MotionEvent.ACTION_UP:
	            touch_up();
	            invalidate();
	            break;
	    }
	    return true;
	}
}