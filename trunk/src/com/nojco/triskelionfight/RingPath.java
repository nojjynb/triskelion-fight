/**
 * 
 */
package com.nojco.triskelionfight;

import android.graphics.Path;

/**
 * @author halljj
 *
 */
public class RingPath extends Path {
	private float sizeMultiplier;
	private float incomingSize;
	/**
	 * 
	 */
	public RingPath(int size) {
		// TODO Auto-generated constructor stub
		incomingSize = (float) size;
		sizeMultiplier =  (float) ((incomingSize-20) / 350.0);
		moveTo(0   * sizeMultiplier, 265 * sizeMultiplier);
        lineTo(40  * sizeMultiplier, 310 * sizeMultiplier);
        lineTo(305 * sizeMultiplier, 310 * sizeMultiplier);
        lineTo(350 * sizeMultiplier, 265 * sizeMultiplier);
        lineTo(200 * sizeMultiplier, 0   * sizeMultiplier);
        lineTo(150 * sizeMultiplier, 0   * sizeMultiplier);
        lineTo(0   * sizeMultiplier, 265 * sizeMultiplier);
        close();
	}

	public float getHeight()
	{
		return 310 * sizeMultiplier;
	}

	public float getWidth()
	{
		return 350 * sizeMultiplier;
	}
	
	/**
	 * @param src
	 */
	public RingPath(Path src) {
		super(src);
		// TODO Auto-generated constructor stub
	}

}
