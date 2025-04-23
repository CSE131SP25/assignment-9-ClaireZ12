package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<>();
		segments.add(new BodySegment(0.5, 0.5, SEGMENT_SIZE));
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		if (deltaX == 0 && deltaY == 0) return;  // 没按键就不动
		//“头进尾出”机制：前端插入新头，尾部删除，实现移动效果；deltaX, deltaY 由 changeDirection(dir)控制
		BodySegment head = segments.getFirst();
	    double newX = head.getX() + deltaX;
	    double newY = head.getY() + deltaY;

	    // Add the new head
	    segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));

	    // Remove the tail to maintain length
	    segments.removeLast();
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		 for (BodySegment seg : segments) {
		        seg.draw();
		    }
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		// 计算蛇头到食物中心的距离
		BodySegment head = segments.getFirst();
	    double distance = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + Math.pow(head.getY() - f.getY(), 2));
	    // 检测到“圆心距离 <= 直径”后，链表前端插入新 BodySegment，绘制时蛇身长度 +1
	    if (distance <= SEGMENT_SIZE) {
	    // Add a new head immediately to grow the snake
	    double newX = head.getX() + deltaX;
	    double newY = head.getY() + deltaY;
	    segments.addFirst(new BodySegment(newX, newY, SEGMENT_SIZE));
	    return true;
	    }
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		 BodySegment head = segments.getFirst();
	      if (head.getX() >= SEGMENT_SIZE / 2.0 && head.getX() <= 1 - SEGMENT_SIZE / 2.0 && head.getY() >= SEGMENT_SIZE / 2.0 && head.getY() <= 1 - SEGMENT_SIZE / 2.0) {
	          return true;
	      }
		return false;
	}
}
