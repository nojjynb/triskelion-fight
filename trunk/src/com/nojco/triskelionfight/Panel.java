package com.nojco.triskelionfight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

class Panel extends SurfaceView implements SurfaceHolder.Callback {
	private CanvasThread canvasthread;
	
    public Panel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	    getHolder().addCallback(this);
	    canvasthread = new CanvasThread(getHolder(), this);
	    setFocusable(true);
	}
    public Panel(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        // TODO Auto-generated constructor stub
	    getHolder().addCallback(this);
	    canvasthread = new CanvasThread(getHolder(), this);
	    setFocusable(true);
	}@Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
            int height) {
    // TODO Auto-generated method stub
   
}
		
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	    // TODO Auto-generated method stub
	canvasthread.setRunning(true);
	canvasthread.start();
	
	   
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	    // TODO Auto-generated method stub
	    boolean retry = true;
	    canvasthread.setRunning(false);
	    while (retry) {
	            try {
	                    canvasthread.join();
	                    retry = false;
	            } catch (InterruptedException e) {
	                    // we will try it again and again...
	            }
	    }
	
	}
	
	@Override
    public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        
        int w = this.getWidth();
        int h = this.getHeight();
        int s = (w<h)? w:h;
        
        float dx;
        float dy;
        RingPath ring = new RingPath(s);
        
        if (w>h)
        {
        	dx = (float) ((w-ring.getHeight())/2.0);
        	dy = 10;
        }
        else
        {
        	dy = (float) ((h-ring.getWidth())/2.0);
        	dx = 10;
        }
        ring.offset(dx, dy);
        //path.offset(60, 40);
        canvas.drawPath(ring, paint);
        
        //RingPath innerRing = new RingPath((int) ring.getWidth() - 30);
        //innerRing.offset(dx+15, dy+15);
		//paint.setColor(Color.GREEN);
        //canvas.drawPath(innerRing, paint);
        
           
    }
}

