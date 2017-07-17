package com.retailer.springboot.util;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class PositionCalculator.
 */
@Component
public class PositionCalculator
    {

	/**
	 * Calculate derived position.
	 *
	 * @param point the point
	 * @param range the range
	 * @param bearing the bearing
	 * @return the point F
	 */
	public PointF calculateDerivedPosition(PointF point, double range, double bearing)
	    {

		double EarthRadius = 6371000; // m

		double latA = Math.toRadians(point.x);
		double lonA = Math.toRadians(point.y);
		double angularDistance = range / EarthRadius;
		double trueCourse = Math.toRadians(bearing);

		double lat = Math.asin(Math.sin(latA) * Math.cos(angularDistance) + Math.cos(latA)
			* Math.sin(angularDistance) * Math.cos(trueCourse));

		double dlon = Math.atan2(Math.sin(trueCourse) * Math.sin(angularDistance) * Math.cos(latA), Math
			.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

		double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;

		lat = Math.toDegrees(lat);
		lon = Math.toDegrees(lon);

		PointF newPoint = new PointF((float) lat, (float) lon);

		return newPoint;
	    }

	/**
	 * Point is in circle.
	 *
	 * @param pointForCheck the point for check
	 * @param center the center
	 * @param radius the radius
	 * @return true, if successful
	 */
	public boolean pointIsInCircle(PointF pointForCheck, PointF center, double radius)
	    {
		if (getDistanceBetweenTwoPoints(pointForCheck, center) <= radius)
		    return true;
		else
		    return false;
	    }

	/**
	 * Gets the distance between two points.
	 *
	 * @param p1 the p 1
	 * @param p2 the p 2
	 * @return the distance between two points
	 */
	public double getDistanceBetweenTwoPoints(PointF p1, PointF p2)
	    {
		double R = 6371000; // m
		double dLat = Math.toRadians(p2.x - p1.x);
		double dLon = Math.toRadians(p2.y - p1.y);
		double lat1 = Math.toRadians(p1.x);
		double lat2 = Math.toRadians(p2.x);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2) * Math.sin(dLon / 2)
			* Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;

		return d;
	    }

    }
