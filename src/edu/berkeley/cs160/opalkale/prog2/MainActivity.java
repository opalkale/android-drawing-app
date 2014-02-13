package edu.berkeley.cs160.opalkale.prog2;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Menu;


public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.crayon);
		//Canvas canvas = new Canvas (mBitmap);
		
		
		DrawingView drawingView = (DrawingView)findViewById(R.id.drawing);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
