import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;

public class Snake {
	
	private int[] x = new int[Game.ALL_BOXES];
	private int[] y = new int[Game.ALL_BOXES];
	private int length = 0;
	
	private int xSpeed = 0;
	private int ySpeed = 0;
	
	private Random rand = new Random();
	private int foodPosX, foodPosY;
	
	public Snake(int xPos, int yPos){
		//Initial size of snake
		x[0] = xPos;
		x[1] = xPos + Game.BOX_SIZE;
		x[2] = xPos + 2*Game.BOX_SIZE;
		x[3] = xPos + 3*Game.BOX_SIZE;
		y[0] = yPos;
		y[1] = yPos;
		y[2] = yPos;
		y[3] = yPos;
		length = 4;
		
		spawnFood();
		xSpeed = -Game.BOX_SIZE;
	}
	
	public void update(){
		
			//Update body position
			for(int i = length - 1; i > 0; i--){
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			
			//Update head position
			x[0] += xSpeed;		
			y[0] += ySpeed;
			
			//Collision with Borders
			if(x[0] < 0){
				x[0] = MainClass.WIDTH - Game.BOX_SIZE;
			}
			else if(x[0] == MainClass.WIDTH){
				x[0] = 0;
			}
			else if(y[0] < 0){
				y[0] = MainClass.HEIGHT - Game.BOX_SIZE;
			}
			else if(y[0] == MainClass.HEIGHT){
				y[0] = 0;
			}
			
			//Collision with itself
			for(int i = 1; i < length; i++){
				if(x[0] == x[i] && y[0] == y[i]){
					Game.inGame = false;
				}
			}
			
			//Collision with Food
			if(x[0] == foodPosX && y[0] == foodPosY){
				eat();
			}	
	}
	
	public void spawnFood(){
		boolean onTop = false;
		
		foodPosX = rand.nextInt(MainClass.WIDTH / Game.BOX_SIZE) * Game.BOX_SIZE;
		foodPosY = rand.nextInt(MainClass.HEIGHT / Game.BOX_SIZE) * Game.BOX_SIZE;
		
		//Check food spawn on top of snake
		for(int i = 0; i < length; i++){
			if(foodPosX == x[i] && foodPosY == y[i])
				onTop = true;
		}

		if(onTop)
			spawnFood();
	}
	
	public void eat(){
		length++;
		x[length-1] = x[length-2];	//update before redraw
		y[length-1] = y[length-2];
		spawnFood();
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT && xSpeed == 0){
			xSpeed = Game.BOX_SIZE;
			ySpeed = 0;
		}
		else if(key == KeyEvent.VK_LEFT && xSpeed == 0){
			xSpeed = -Game.BOX_SIZE;
			ySpeed = 0;
		}
		else if(key == KeyEvent.VK_DOWN && ySpeed == 0){
			ySpeed = Game.BOX_SIZE;
			xSpeed = 0;
		}
		else if(key == KeyEvent.VK_UP && ySpeed == 0){
			ySpeed = -Game.BOX_SIZE;
			xSpeed = 0;
		}
	}
	
	public void draw(Graphics2D g2d){
		
		g2d.drawImage(getFoodImage(), foodPosX, foodPosY, null);
		
		for(int i = length - 1; i >= 0; i--){
			if(i == 0){
				g2d.setColor(Color.CYAN);
				g2d.fillOval(x[i], y[i], Game.BOX_SIZE, Game.BOX_SIZE);
			}
			else{
			g2d.setColor(Color.ORANGE);
			g2d.fillRect(x[i], y[i], Game.BOX_SIZE, Game.BOX_SIZE);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x[i], y[i], Game.BOX_SIZE, Game.BOX_SIZE);
			}
		}
	}
	
	public Image getFoodImage(){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/redApple.png"));
		return image.getImage();
	}
}
