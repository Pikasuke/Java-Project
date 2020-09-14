package com.test.premier.SommeDePremier;


import java.util.ArrayList;
import java.util.List;

public class SommeDePremier {

	public static int sommeDePremier(int nbpremierasommer) {
		int result = 0;

		// liste des premier

		List<Integer> listeDesNbPremiers = listeDesPremiers(nbpremierasommer);

		// somme des premier

		for (Integer numDelaListe : listeDesNbPremiers) {

			result = result + numDelaListe;
		}

		System.out.println("resultat = " + result);

		return result;
	}

	public static List<Integer> listeDesPremiers(int taille) {
		List<Integer> listeDesPremiers = new ArrayList<Integer>();
		int nbDivisable = 0;
		int i = 2;

		while (listeDesPremiers.size() < taille) {
			for (int j = 1; j < 10; j++) {
				if (i % j == 0) {
					nbDivisable++;
				}
			}
			if (nbDivisable <= 2) listeDesPremiers.add(i);
			
			nbDivisable = 0;
			i++;
		}
		for (Integer integer : listeDesPremiers) {
			System.out.println("list num " + integer);
		}
		return listeDesPremiers;
	}
}
