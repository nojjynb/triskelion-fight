package com.nojco.triskelionfight;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 
 */

/**
 * @author halljj
 *
 */
public class media {
	
	private boolean isPlaying;
	private MediaPlayer mediaPlayer;
	private OnClickListener listener;
	private OnCompletionListener complete;
	private int soundId;
	private Context context;


	
	/**
	 * 
	 */
	public media(int stfight, Context c) {
		// TODO Auto-generated constructor stub
		soundId = stfight;
		context = c;
	    
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


		createSound ();

	}

	public void reconnectListeners(View view)
	{
		view.setOnClickListener(listener);
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
}
