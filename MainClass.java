import javax.swing.JFrame;


public class MainClass {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int TOPBORDER = 22;
	
	public MainClass(){
		JFrame frame = new JFrame("Snake Game");
		frame.setSize(WIDTH, HEIGHT + TOPBORDER);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(new Game());
		frame.setVisible(true);
	}
	
	public static void main(String args[]){
		new MainClass();
	}
	
	
}
