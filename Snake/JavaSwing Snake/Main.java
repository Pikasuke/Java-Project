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
	private Point apple = new Point(0, 0);
	private Random rand = new Random();

	private boolean gameLost = false;

	private int offset = 0;
	private int newDirection = 39;

	private boolean isEat = false;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Snake");
		Main panel = new Main();
		frame.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) { // permet l'acces au touche pressed sur le pannel
				panel.onKeyPressed(e.getKeyCode());
			}
		});

		frame.setContentPane(panel);// mettre le Main ds la frame
		frame.setSize(13 * 50, 13 * 50);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);// centrage ecran
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermer le programme qd on ferme la fenetre
		frame.setVisible(true);
	}

	public Main() {

		createApple();
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

	public void createApple() {
		boolean positionAvailable = false;
		// genere une pomme tant que la position est dispo sinon recommence
		do {
			// creation de la pomme
			apple.x = rand.nextInt(12);
			apple.y = rand.nextInt(12);
			positionAvailable = true;
			// si une des partie du snakese trouve sur la position de la pomme alors la
			// position n'est pas dispo et on regenere la pomme
			for (SnakePart s : snake) {
				if (s.x == apple.x && s.y == apple.y) {
					positionAvailable = false;
					break;
				}
			}
		} while (!positionAvailable);

	}

	@Override
	public void paintComponent(Graphics g) { // Component pas Componenents
		super.paintComponent(g);

		if (gameLost) {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", 75, 75));
			g.drawString("Partie terminé ", 13 * 50 / 2 - g.getFontMetrics().stringWidth("Partie terminé ") / 2,
					13 * 50 / 2);
			return;
		}

		offset += 5;
		SnakePart head = null;
		if (offset == WIDTH) {
			offset = 0;

			try {
				head = ((SnakePart) snake.getFirst().clone()); // duplique la tete puis l'ajoute avant puis la fait
																// bouger ds la bonne direction
				head.move();
				head.direction = newDirection;
				snake.addFirst(head);
				if (head.x == apple.x && head.y == apple.y) {
					isEat = true;
					createApple();
				}
				if (!isEat)
					snake.pollLast(); // recupere et supprime le dernier element
				else
					isEat = false; // s'il mange il grossit qu'une fois on remet false

			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// dessine la pomme de couleur rouge
		g.setColor(Color.RED);
		g.fillOval(apple.x * WIDTH + WIDTH / 4, apple.y * WIDTH + WIDTH / 4, WIDTH / 2, WIDTH / 2);

		// dessine le snake de couleur grise
		g.setColor(Color.DARK_GRAY);
		for (SnakePart s : snake) {
			if (offset == 0) {
				if (s != head) {
					if (head.x == s.x && head.y == s.y) {
						gameLost = true;
					}
				}
			}

			if (s.direction == 37 || s.direction == 39) {
				g.fillRect(s.x * WIDTH + ((s.direction == 37) ? -offset : offset), s.y * WIDTH, WIDTH, WIDTH); // création
																												// du
																												// decalage
																												// du
																												// snake
																												// ac
																												// l'offset
			} else {
				g.fillRect(s.x * WIDTH, s.y * WIDTH + ((s.direction == 38) ? -offset : offset), WIDTH, WIDTH);
			}
		}

		g.setColor(Color.BLUE);
		g.drawString("Score : " + (snake.size() - 1), 10, 20);
	}

	public void onKeyPressed(int codeKey) {
		System.out.println(codeKey); // test du codeKey sur le panel

		if (codeKey >= 37 && codeKey <= 40) {
			if (Math.abs(codeKey - newDirection) != 2) { // empeche d'aller à la direction opposé et mourrir
				newDirection = codeKey;
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

		public void move() {
			if (direction == 37 || direction == 39) {
				x += (direction == 37) ? -1 : 1;
				if (x > 13) // si le x snake sort de l'ecran à droite alors il revient à gauche et
							// inversement
					x = -1;
				else if (x < -1)
					x = 13;
			} else {
				y += (direction == 38) ? -1 : 1;
				if (y > 13) // si le y snake sort de l'ecran en bas alors il revient en haut et inversement
					y = -1;
				else if (y < -1)
					y = 13;
			}
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return new SnakePart(x, y, direction);
		}

	}

}
