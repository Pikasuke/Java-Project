import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {

	private final int WIDTH = 50;
	private Deque<SnakePart> snake = new ArrayDeque<>(); // duplicage de la tete suppression de la queue;
	public int offset = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Snake");
		Main panel = new Main();
		frame.setContentPane(panel);// mettre le Main ds la frame
		frame.setSize(12 * 50, 12 * 50);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);// centrage ecran
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermer le programme qd on ferme la fenetre
		frame.setVisible(true);
	}

	public Main() {
		snake.add(new SnakePart(0, 0, 39)); // ajout de la 1er partie du snake au depart qui va vers la droite (39 =
											// fleche droite)
		setBackground(Color.WHITE);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					repaint();
					try {
						Thread.sleep(1000 / 60);// 60 exe par seconde
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void paintComponent(Graphics g) { // Component pas Componenents
		super.paintComponent(g);
		
		offset++;
		if(offset == WIDTH ) offset = 0;

		g.setColor(Color.DARK_GRAY);
		for (SnakePart s : snake) {
			if (s.direction == 37 || s.direction == 39) {
				g.fillRect(s.x*WIDTH + ((s.direction == 37) ? -offset : offset), s.y*WIDTH, WIDTH, WIDTH);
			} else {
				g.fillRect(s.x*WIDTH, s.y*WIDTH + ((s.direction == 37) ? -offset : offset), WIDTH, WIDTH);
			}
			
		}
	}

	class SnakePart {
		public int x, y, direction;

		public SnakePart(int x, int y, int direction) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new SnakePart(x, y, direction);
		}

	}

}
