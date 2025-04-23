package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Food {

	public static final double FOOD_SIZE = 0.02;
	private double x, y;
	
	/**
	 * Creates a new Food at a random location
	 */
	public Food() {
		double min = FOOD_SIZE / 2.0;         // radius
		double max = 1.0 - FOOD_SIZE / 2.0;   // 1 – radius
		x = min + Math.random() * (max - min); // [min, max)
		y = min + Math.random() * (max - min); // [min, max)
	}
	
	/**
	 * Draws the Food
	 */
	public void draw() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.filledCircle(x, y, FOOD_SIZE / 2.0);
	}

	 public double getX() {
		 return x; 
		 }
	 
	 public double getY() { 
		 return y; 
		 }
	
}
