package com.nojco.triskelionfight;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class main extends Activity {
	public Bitmap bitmap;
	public OnTouchListener touch;
	public GestureDetector mDetector;
	private ImageView image;
	private ImageView clickable;
	public FrameLayout frame;
	private Context context;
	public Canvas canvas;
	public ImageView icon;
	public ImageView badguy;
	private LinearLayout main;
	
	private media fightSong;
	
	public enum MoveTypes {
		UpperCPU,
		RightCPU,
		LeftCPU,
		MiddleCPU,
		LowerPlayer,
		LeftPlayer,
		RightPlayer,
		MiddlePlayer;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.main);
        context = this;
        init(R.drawable.background, R.drawable.background_clickable);
        
        main = (LinearLayout) findViewById(R.id.L);
        main.addView(frame);
        
        fightSong = new media(R.raw.stfight, this);
        //fightSong.start();
        
        fightSong.reconnectListeners(findViewById(R.id.button1));
    }
    
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.main);
        main = (LinearLayout) findViewById(R.id.L);
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		int orientation = display.getRotation();
		if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180)
			main.setOrientation(LinearLayout.VERTICAL);
		else
			main.setOrientation(LinearLayout.HORIZONTAL);
		//main.removeAllViews();
		//main.addView(frame);
        fightSong.reconnectListeners(findViewById(R.id.button1));
		
		reconnectListeners();
    }

    private void init(int imageId, int backgroundImageId) {
		// TODO Auto-generated constructor stub
		mDetector = new GestureDetector(new MyGestureDetector());
		
		frame = new FrameLayout(context);
		frame.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));
		
        
        image     = new ImageView(context);
        clickable = new ImageView(context);
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.background_clickable);
        image.setImageResource(R.drawable.background);
        clickable.setImageBitmap(bitmap);
        
        badguy = new ImageView(context);
        badguy.setImageResource(R.drawable.cpu_kahn_uc);
        
        frame.addView(clickable);
        frame.addView(image);

    	// Calculate relative offset from position
    	
    	//iconLayout = new AbsoluteLayout(context);
		icon = new ImageView(context);
		//setOrigin(x_off, y_off, (float)bitmap.getWidth()/350.0);
        //LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        //params.setMargins(lp.x, lp.y, 0, 0);
        
        //icon.setLayoutParams(params);
    	icon.setImageResource(R.drawable.player_red_lp);
    	
    	
    	//iconLayout.setPadding(lp.x, lp.y, 0, 0);
        //iconLayout.addView(icon);
		//frame.addView(iconLayout);
    	frame.addView(icon);
    	frame.addView(badguy);
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
	
	private void reconnectListeners () {
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
		    String hex = null;
		    if (bitmap != null)
		    {
		    	float cw = clickable.getWidth() ;
		    	float bw = bitmap.getWidth();
		    	float bxscale = (bw/cw);
		    	float ch = clickable.getHeight();
		    	float bh = bitmap.getHeight();
		    	float byscale = (bh/ch);
		    	
		    	//float scale = (bxscale < byscale)? bxscale:byscale;
		    	// determine orientation and use that scale
		    	Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		    	float scale;
		    	int orientation = display.getRotation();
		    	if (orientation == Surface.ROTATION_0 || orientation == Surface.ROTATION_180)
		    	{
		    		scale = bxscale;
		    		y -= ((ch-bh/scale)/2);
		    	}
		    	else
		    	{
		    		scale = byscale;
		    		x -= ((cw-bw/scale)/2);
		    	}
		    	int gx = (int) (x*scale);
		    	int gy = (int) (y*scale);
		    	
		    	
		    	if (gx >= 0 && gx <= bitmap.getWidth() && gy >=0 && gy <=bitmap.getHeight())
		    	{
		    		
			    	color = bitmap.getPixel((int)(gx), (int)(gy));
			    	hex = Integer.toHexString(color);
		    		//Toast.makeText(context, String.valueOf(clickable.getWidth()) + " - " + String.valueOf(x) + ":" + String.valueOf(x_off) + ":" + String.valueOf(((int)(x-x_off))) + ","  + String.valueOf(clickable.getHeight()) + " - " + String.valueOf(y) + ":" + String.valueOf(y_off) + ":" + String.valueOf(((int)(y-y_off))), Toast.LENGTH_LONG).show();
			    	if (hex.equals("ffe5e5e5"))
			    	{
			    		// Left CPU
			    		//Toast.makeText(context, "Left CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ffcccccc"))
			    	{
			    		// Upper CPU
			    		//Toast.makeText(context, "Upper CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ffffffff"))
			    	{
			    		// Right CPU
			    		//Toast.makeText(context, "Right CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ff7f7f7f"))
			    	{
			    		// Middle CPU
			    		//Toast.makeText(context, "Middle CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ff00ff00"))
			    	{
			    		// Left Player
			    		//Toast.makeText(context, "Left Player", Toast.LENGTH_SHORT).show();
			        	frame.removeView(icon);
			        	icon.setImageResource(R.drawable.player_red_lp);
			        	frame.addView(icon);
			    	}
			    	else if (hex.equals("ff00ffff"))
			    	{
			    		// Lower Player
			    		//Toast.makeText(context, "Lower Player", Toast.LENGTH_SHORT).show();
			        	frame.removeView(icon);
			        	icon.setImageResource(R.drawable.player_red_lwp);
			        	frame.addView(icon);
			    	}
			    	else if (hex.equals("ffff0000"))
			    	{
			    		// Right Player
			    		//Toast.makeText(context, "Right Player", Toast.LENGTH_SHORT).show();
			        	frame.removeView(icon);
			        	icon.setImageResource(R.drawable.player_red_rp);
			        	frame.addView(icon);
			    	}
			    	else if (hex.equals("fffff82f"))
			    	{
			    		// Middle Player
			    		//Toast.makeText(context, "Middle Player", Toast.LENGTH_SHORT).show();
			        	frame.removeView(icon);
			        	icon.setImageResource(R.drawable.player_red_mp);
			        	frame.addView(icon);
			    	}
			    	else
			    	{
			    		//Toast.makeText(context, "Elsewhere", Toast.LENGTH_SHORT).show();
			    		
			    	}
		    	}
		    }
		    return true;
	
		}
	}
}