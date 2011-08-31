/**
 * 
 */
package com.nojco.triskelionfight;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author halljj
 *
 */
public class Move {
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
	private ImageView icon;
	private pos uc;
	private pos rc;
	private pos lc;
	private pos lwp;
	private pos lp;
	private pos rp;
	private pos mp;
	
	/**
	 * 
	 */
	public Move(Context c) {
		// TODO Auto-generated constructor stub
		icon = new ImageView (c);
		icon.setImageResource(R.drawable.player_gold);
		
	}
	
	public void setOrigin (int _x, int _y, int scale) {
		uc = new pos (163*scale + _x, 53*scale + _y);
		rc = new pos (255*scale + _x, 230*scale + _y);
		lc = new pos (75*scale + _x, 230*scale + _y);
		lwp = new pos (165*scale + _x, 250*scale + _y);
		lp = new pos (88*scale + _x, 137*scale + _y);
		rp = new pos (210*scale + _x, 110*scale + _y);
		mp = new pos (158*scale + _x, 160*scale + _y);
		
	}
	
	public class pos {
		public int x;
		public int y;
		
		public pos (int _x, int _y) {
			x = _x;
			y = _y;
		}
		
		public pos () {
			x = 0;
			y = 0;
		}
	}
	

}
