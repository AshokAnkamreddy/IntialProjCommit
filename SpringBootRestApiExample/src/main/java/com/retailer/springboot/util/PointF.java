package com.retailer.springboot.util;

// TODO: Auto-generated Javadoc
/**
 * The Class PointF.
 */
public class PointF
    {
	
	/** The x. */
	public double x;
	
	/** The y. */
	public double y;

	/**
	 * Instantiates a new point F.
	 *
	 * @param lat the lat
	 * @param lang the lang
	 */
	public PointF(double lat, double lang)
	    {
		this.x = lat;
		this.y = lang;
	    }

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public double getX()
	    {
		return x;
	    }

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(double x)
	    {
		this.x = x;
	    }

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public double getY()
	    {
		return y;
	    }

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(double y)
	    {
		this.y = y;
	    }

    }
