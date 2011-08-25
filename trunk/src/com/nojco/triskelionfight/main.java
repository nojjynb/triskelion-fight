package com.nojco.triskelionfight;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class main extends Activity {
	
	private mediaImage media;
	private ClickableBitmap clickableBitmap;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        clickableBitmap = new ClickableBitmap(this, R.drawable.background, R.drawable.background_clickable);
        
        LinearLayout main = (LinearLayout) findViewById(R.layout.main);
        main.addView(clickableBitmap.frame);
        setContentView(R.layout.main);
        
    }
    
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      setContentView(R.layout.main);
      clickableBitmap.reconnectListeners();
    }

}