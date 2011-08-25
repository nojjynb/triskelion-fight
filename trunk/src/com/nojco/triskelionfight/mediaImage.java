package com.nojco.triskelionfight;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 */

/**
 * @author halljj
 *
 */
public class mediaImage {
	
	private boolean isPlaying;
	private MediaPlayer mediaPlayer;
	private OnClickListener listener;
	private OnCompletionListener complete;
	private OnTouchListener touch;
	private int soundId;
	private Context context;
	private GestureDetector mDetector;


	
	/**
	 * 
	 */
	public mediaImage(ImageView image, int stfight, Context c) {
		// TODO Auto-generated constructor stub
		soundId = stfight;
		context = c;
	    mDetector = new GestureDetector(new MyGestureDetector());
	    
		listener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startStop();
			}
		};	

		complete = new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				stop ();
			}
		};
		
		touch = new OnTouchListener()
        {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				mDetector.onTouchEvent(event);

				return true;
			}
       };


		createSound ();

		reconnectListeners(image);
	}

	public void reconnectListeners(ImageView image)
	{
		image.setOnClickListener(listener);
		//image.setOnTouchListener(touch);
	}
	
	public void startStop() {
		if (isPlaying) {
			stop();
		}
		else {
			start();
		}
	}
	
	
	public void start() {
	    
		if (mediaPlayer != null && mediaPlayer.isPlaying())
		{
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		
		createSound();
	    mediaPlayer.start();
	    isPlaying = true;
	    
	}
	
	public void stop() {
    	
		if (mediaPlayer != null && mediaPlayer.isPlaying())
		{
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
		}
		isPlaying = false;
	}
	
	public void createSound() {
		mediaPlayer = MediaPlayer.create(context, soundId);
		mediaPlayer.setLooping(true);
		mediaPlayer.setOnCompletionListener(complete);
	}
	
	class MyGestureDetector extends GestureDetector.SimpleOnGestureListener
		{
	
		@Override
		 public boolean onDown(MotionEvent e)
		 {
		    float x = e.getX();
		    float y = e.getY();
			Toast.makeText(context, String.valueOf(x) + ", " + String.valueOf(y), Toast.LENGTH_SHORT) ;
		    return true;
	
		}
	}
}
