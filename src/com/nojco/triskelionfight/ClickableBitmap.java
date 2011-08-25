/**
 * 
 */
package com.nojco.triskelionfight;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * @author halljj
 *
 */
public class ClickableBitmap {
	
	public Bitmap bitmap;
	public OnTouchListener touch;
	public GestureDetector mDetector;
	private ImageView image;
	private ImageView clickable;
	public FrameLayout frame;
	
	/**
	 * 
	 */
	public ClickableBitmap(Context context, int imageId, int backgroundImageId) {
		// TODO Auto-generated constructor stub
		mDetector = new GestureDetector(new MyGestureDetector());
		
		frame = new FrameLayout(context);
		frame.setLayoutParams();
		
        
        image     = new ImageView(context);
        clickable = new ImageView(context);
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.background_clickable);
        image.setImageResource(R.drawable.background);
        clickable.setImageBitmap(bitmap);
        
        frame.addView(clickable);
        frame.addView(image);
		
		touch = new OnTouchListener()
        {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mDetector.onTouchEvent(event);

				return true;
			}
       };
       
       connectListeners(image);
	}

	private void connectListeners(ImageView v) {
		v.setOnTouchListener(touch);
	}
	
	public void reconnectListeners () {
		connectListeners(image);
	}
	class MyGestureDetector extends GestureDetector.SimpleOnGestureListener
		{
	
		@Override
		 public boolean onDown(MotionEvent e)
		 {
		    float x = e.getX();
		    float y = e.getY();
		    int color = 0;
		    if (bitmap != null)
		    {
		    	// Calculate relative offset from position
		    	float x_off = (float) ((clickable.getWidth()  - bitmap.getWidth() )/2.0 + clickable.getLeft());
		    	float y_off = (float) ((clickable.getHeight() - bitmap.getHeight())/2.0 + clickable.getTop());
		    	
		    	color = bitmap.getPixel((int)(x-x_off), (int)(y-y_off));
		    	switch(color)
		    	{
		    	default:
		    		break;
		    	}
		    }
		    return true;
	
		}
	}
	
}
