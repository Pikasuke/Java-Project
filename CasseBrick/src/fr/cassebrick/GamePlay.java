package fr.cassebrick;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;
	private int score = 0;

	private int totalBrick = 21;

	private Timer timer;
	private int delay = 8;

	private int playerX = 310;
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;

	private MapGenerator mG;

	public GamePlay() {
		mG = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}

	public void paint(Graphics g) {
		// Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(0, 0, 3, 592);

		// Scores 
		g.setColor(Color.blue);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString("Score "+score, 580, 30);
		
		// The Paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);

		// The Ball
		g.setColor(Color.yellow);
		g.fillOval(ballPosX, ballPosY, 20, 30);

		// The brick Wall
		mG.draw((Graphics2D) g);
		
		if (totalBrick<=0) {
			play = false;
			ballXdir= 0;
			ballYdir= 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("YOU WIN ! ! ! ", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart ! ", 230, 350);
			
		}
		
		if(ballPosY > 570 ) {
			play = false;
			ballXdir= 0;
			ballYdir= 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over ! Score : "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart ! ", 230, 350);
		}

		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		if (play) {
			// Mouvement de la bale sur le plateau
			if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8)))
				ballYdir = -ballYdir;

			// brick collision
			A: for (int i = 0; i < mG.map.length; i++) {
				for (int j = 0; j < mG.map[0].length; j++) {
					if (mG.map[i][j] > 0) {
						int brickX = j * mG.brickWidth + 80;
						int brickY = i * mG.brickHeight + 50;
						int brickWidth = mG.brickWidth;
						int brickHeight = mG.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							mG.setBrickValue(0, i, j);
							totalBrick--;
							score += 5;

							if (ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			// Mouvement de la balle et des bordures
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if (ballPosX < 0)
				ballXdir = -ballXdir;
			if (ballPosY < 0)
				ballYdir = -ballYdir;
			if (ballPosX > 670)
				ballXdir = -ballXdir;
		}

		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600)
				playerX = 600;
			else
				moveRigth();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX <= 10)
				playerX = 10;
			else
				moveLeft();
		}
		
		if(e.getKeyCode()== KeyEvent.VK_ENTER) {
			if(!play) {
				play=true;
				ballPosX= 120;
				ballPosY=350;
				ballXdir=-1;
				ballYdir=-2;
				playerX= 310;
				score = 0;
				totalBrick= 21;
				mG = new MapGenerator(3, 7);
				
				repaint();
				
			}
		}

	}

	private void moveLeft() {
		play = true;
		playerX -= 20;

	}

	private void moveRigth() {
		play = true;
		playerX += 20;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
