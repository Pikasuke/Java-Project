package fr.cassebrick;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame obj = new JFrame();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Casse Brick !");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePlay gP = new GamePlay();
		obj.add(gP);
		
	}

}
