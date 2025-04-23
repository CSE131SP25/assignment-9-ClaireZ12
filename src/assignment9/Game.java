package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake snake;
    private Food food;
    private int score;
    
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake ();
		food = new Food ();	
		score = 0;  
	}
	
	public void play() {
		while (snake.isInbounds()) { //只要蛇头圆心超出 [r,1−r] 范围就判定撞墙，跳出循环并结束游戏。
			int dir = getKeypress();
			System.out.println("Keypress: " + dir);
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
			snake.changeDirection(dir); //Pass direction to your snake
			snake.move();//Tell the snake to move
			//每次 eatFood() 返回 true 时，新建 Food()（构造器内部随机生成 x,y），让食物跳到新位置。
			if (snake.eatFood(food)) { //If the food has been eaten, make a new one
				food = new Food();
				score++;
			}
			updateDrawing();//Update the drawing
		}
		System.out.println("Game Over!" + "Your score: " + score);
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
        StdDraw.clear();// Clear screen
 
        snake.draw();//Draw snake and food
        food.draw();
        
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(0.10, 0.95, "Score: " + score);
        
        StdDraw.pause(50);//Pause (50 ms is good)

        StdDraw.show();//Show
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
