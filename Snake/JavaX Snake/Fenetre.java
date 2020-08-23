import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements  KeyListener{

	public static int largeurFenetre = 200;
	public static int hauteurFenetre = 150;
	Serpent anaconda;
	
	JLabel gameOver = new JLabel();
	
	JPanel container = new JPanel();
	
	public Fenetre () {
		this.setTitle("Snake");
		this.setSize(largeurFenetre, hauteurFenetre);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		container.setBackground(Color.LIGHT_GRAY);
		
		gameOver.setBounds(largeurFenetre/2-40, 0, 100, 20);
		
		this.addKeyListener(this);
		
		this.setContentPane(container);
		this.setVisible(true);
		
		anaconda = new Serpent(this.getGraphics(), 4);
	}
	
	public void startGame() {
		anaconda.jouer();
		gameOver();
	}
	
	public void gameOver() {
		gameOver.setText("Game Over !");
		container.add(gameOver);
		container.repaint();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode()== KeyEvent.VK_UP && anaconda.direction !=3)
			anaconda.direction = 1;
		
		if(k.getKeyCode()== KeyEvent.VK_RIGHT && anaconda.direction !=4)
			anaconda.direction = 2;
		
		if(k.getKeyCode()== KeyEvent.VK_DOWN && anaconda.direction !=1)
			anaconda.direction = 3;
		
		if(k.getKeyCode()== KeyEvent.VK_LEFT && anaconda.direction !=2)
			anaconda.direction = 4;
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
