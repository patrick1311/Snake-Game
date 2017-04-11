import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyboardInput extends KeyAdapter{

	private Snake snake;
	
	public KeyboardInput(Snake s){
		snake = s;
	}
	
	public void keyPressed(KeyEvent e){
		snake.keyPressed(e);
	}

}
