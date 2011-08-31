/**
 * 
 */
package com.nojco.triskelionfight;

import com.nojco.triskelionfight.Move.MoveTypes;
import com.nojco.triskelionfight.Move.pos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
	private Context context;
	public Canvas canvas;
	
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
	
	private MoveTypes previousMove;
	public ImageView icon;
	private pos uc;
	private pos rc;
	private pos lc;
	private pos lwp;
	private pos lp;
	private pos rp;
	private pos mp;

	private float x_off;
	private float y_off;
	
	/**
	 * 
	 */
	public ClickableBitmap(Context c, int imageId, int backgroundImageId) {
		// TODO Auto-generated constructor stub
		mDetector = new GestureDetector(new MyGestureDetector());
		
		context = c;
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
        
        frame.addView(clickable);
        frame.addView(image);

    	// Calculate relative offset from position
    	x_off = (float) ((clickable.getWidth()  - bitmap.getWidth() )/2.0 + clickable.getLeft());
    	y_off = (float) ((clickable.getHeight() - bitmap.getHeight())/2.0 + clickable.getTop());
    	
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
		    String hex = null;
		    if (bitmap != null)
		    {
		    	
		    	if (x-x_off >= 0 && x-x_off <= bitmap.getWidth() && y-y_off >=0 && y-y_off <=bitmap.getHeight())
		    	{
		    		
			    	color = bitmap.getPixel((int)(x-x_off), (int)(y-y_off));
			    	hex = Integer.toHexString(color);
			    	if (hex.equals("ffe5e5e5"))
			    	{
			    		// Left CPU
			    		Toast.makeText(context, "Left CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ffcccccc"))
			    	{
			    		// Upper CPU
			    		Toast.makeText(context, "Upper CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ffffffff"))
			    	{
			    		// Right CPU
			    		Toast.makeText(context, "Right CPU", Toast.LENGTH_SHORT).show();
			    	}
			    	else if (hex.equals("ff7f7f7f"))
			    	{
			    		// Middle CPU
			    		Toast.makeText(context, "Middle CPU", Toast.LENGTH_SHORT).show();
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
			    		Toast.makeText(context, "Elsewhere", Toast.LENGTH_SHORT).show();
			    		
			    	}
		    	}
		    }
		    return true;
	
		}
	}
	

	public void setOrigin (float x_off2, float y_off2, double d) {
		uc = new pos (163*d - x_off2, 53*d - y_off2);
		rc = new pos (255*d - x_off2, 230*d - y_off2);
		lc = new pos (75*d - x_off2, 230*d - y_off2);
		lwp = new pos (165*d - x_off2, 250*d - y_off2);
		lp = new pos (88*d - x_off2, 137*d - y_off2);
		rp = new pos (210*d - x_off2, 110*d - y_off2);
		mp = new pos (158*d - x_off2, 160*d - y_off2);
		 
	}
	
	public class pos {
		public int x;
		public int y;
		
		public pos (double d, double e) {
			x = (int) d;
			y = (int) e;
		}
		
		public pos () {
			x = 0;
			y = 0;
		}
	}
	
}
