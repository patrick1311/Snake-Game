import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener{
	
	public static final int ALL_BOXES = MainClass.WIDTH * MainClass.HEIGHT;
	public static final int BOX_SIZE = 20;
	public static boolean inGame = true;
	
	Timer gameLoop;
	Snake snake;
	
	public Game(){
		setFocusable(true);
		
		gameLoop = new Timer(60, this);
		gameLoop.start();
		
		snake = new Snake(MainClass.WIDTH/2, MainClass.HEIGHT/2);
		addKeyListener(new KeyboardInput(snake));
	}
	
	public void actionPerformed(ActionEvent e){
		if(inGame){
		snake.update();
		repaint();
		}
		else{
			repaint();
			gameLoop.stop();
		}
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		snake.draw(g2d);
		setBackground(Color.DARK_GRAY);
		
		if(!inGame){
			g2d.drawImage(getEndScreen(), 0, 0, null);
		}	
	}
	
	public Image getEndScreen(){
		ImageIcon image = new ImageIcon(getClass().getResource("/images/gameOver.png"));
		return image.getImage();
	}
	
}
