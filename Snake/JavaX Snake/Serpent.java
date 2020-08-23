import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

public class Serpent extends JPanel {

	Graphics g;
	public int longueur;
	public int nbPommes = 3;
	public boolean jouer = true;
	Scanner sc;
	ArrayList<SerpentPart> corps = new ArrayList<>();
	ArrayList<Pomme> pommes = new ArrayList<>();

	public int direction = 4;

	public Serpent(Graphics g, int longueur) {
		this.g = g;
		this.longueur = longueur;
		sc = new Scanner(System.in);
	}

	public void jouer() {
		creationSerpent();

		while (jouer) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, Fenetre.largeurFenetre, Fenetre.hauteurFenetre);

			creationPomme();
			dessinePomme();
			dessineSerpent();

			sleep(200);
			move();
			checkCollision();
		}
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

	}

	public void creationPomme() {
		int randX, randY;
		Boolean creation = true;

		while (pommes.size() < nbPommes) {
			creation = true;

			int withFenetre = ((Fenetre.largeurFenetre - 20) / 10) - 2;
			int heightFenetre = ((Fenetre.hauteurFenetre - 20) / 10) - 2;

			randX = (int) (Math.random() * (withFenetre) + 3);
			randY = (int) (Math.random() * (heightFenetre) + 3);

			randX = randX * 10;
			randY = randY * 10;

			for (int i = 0; i < corps.size(); i++) {
				SerpentPart siExiste = corps.get(i);
				if (randX == siExiste.posX && randY == siExiste.posY) {
					creation = false;
				}
			}

			if (creation) {
				pommes.add(new Pomme(randX, randY, Color.GREEN));
			}
		}
	}

	public void creationSerpent() {
		for (int j = 0; j < this.longueur; j++) {
			int hauteur;
			hauteur = (Fenetre.hauteurFenetre / 2) / 10;
			hauteur *= 10;
			if (j == 0) {
				corps.add(new SerpentPart(Fenetre.largeurFenetre / 2 + ((j) * 10), hauteur, Color.RED));
			} else if (j > 0) {

				corps.add(new SerpentPart(Fenetre.largeurFenetre / 2 + ((j) * 10), hauteur, Color.BLACK));
			}

		}
	}

	public void dessinePomme() {
		for (int i = 0; i < pommes.size(); i++) {
			Pomme p = pommes.get(i);

			g.setColor(p.couleur);
			g.fillOval(p.posX, p.posY, 10, 10);
		}
	}

	public void dessineSerpent() {
		for (int i = 0; i < corps.size(); i++) {
			SerpentPart s = corps.get(i);

			g.setColor(s.couleur);
			g.fillOval(s.posX, s.posY, 10, 10);
			showScore();
		}
	}

	public void showScore() {
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		g.drawString(Integer.toString(corps.size()), 10, Fenetre.hauteurFenetre - 10);

	}

	public void checkCollision() {

		// Collision Pomme
		for (int i = 0; i < pommes.size(); i++) {

			Pomme checkPomme = pommes.get(i);
			SerpentPart checkSerpent = corps.get(0);
			SerpentPart lastPosition = corps.get(corps.size() - 1);

			if (checkPomme.posX == checkSerpent.posX && checkPomme.posY == checkSerpent.posY) {
				pommes.remove(i);
				corps.add(new SerpentPart(200 + ((lastPosition.posX) + 10), 0, Color.BLACK));
			}
		}

		// Collision serpent

		for (int i = 1; i < corps.size(); i++) {

			SerpentPart corpSerpent = corps.get(i);
			SerpentPart teteSerpent = corps.get(0);

			if (teteSerpent.posX == corpSerpent.posX && teteSerpent.posY == corpSerpent.posY)
				jouer = false;

		}

		// Collision Bords de fenetre
		SerpentPart teteSerpent = corps.get(0);
		if (teteSerpent.posX < 10)
			jouer = false;

		if (teteSerpent.posX > (Fenetre.largeurFenetre - 20))
			jouer = false;

		if (teteSerpent.posY < 30)
			jouer = false;

		if (teteSerpent.posY > (Fenetre.hauteurFenetre - 20))
			jouer = false;

	}

	public void move() {
		int px, py;

		for (int i = corps.size() - 1; i > 0; i--) {
			SerpentPart p;
			p = corps.get(i - 1);
			px = p.posX;
			py = p.posY;
			p = corps.get(i);
			p.posX = px;
			p.posY = py;
		}
		SerpentPart p0 = corps.get(0);

		if (direction == 1)
			p0.posY -= 10;

		if (direction == 2)
			p0.posX += 10;

		if (direction == 3)
			p0.posY += 10;

		if (direction == 4)
			p0.posX -= 10;
	}

}
