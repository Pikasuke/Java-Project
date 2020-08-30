package com.moi.dm.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 
 * @author Tchong
 * 
 * La classe Partition possede trois methodes statiques qui permettent de subdiviser le contenant de l'objet mere dans plusieurs conteneur à l'interieur de celui ci.
 * le nombre d'objet contenu dans les conteneurs filles peuvent etre definis dans le deuxieme argument des fontcions.
 * Le premier argument étant l'objet mere à subdiviser.
 * La valeur de retour est de type de l'objet mere en deux dimensions
 * 
 * Example: 
 * Je souhaite subdiviser mon conteneur mere [1, 2, 3, 4, 5] en conteneurs filles de 2 entiers :
 * partition([1,2,3,4,5], 2) retournera: [ [1,2], [3,4], [5] ]
 * 
 * 
 * Je souhaite subdiviser mon conteneur mere [1, 2, 3, 4, 5, 6, 7] en conteneurs filles de 3 entiers :
 * partition([1,2,3,4,5], 3) retournera: [ [1,2,3], [4,5,6], [7]
 * 
 *  Je souhaite subdiviser mon conteneur mere [1, 2, 3] en conteneurs filles de 1 entier :
 * partition([1,2,3,4,5], 1) retournera: [ [1], [2], [3] ]
 * 
 * 3 Fonctions sont implémentées :
 *	- partitionList ()
 *	- partitionTableau ()
 *	- partitionAtomic ()
 *
 *	Chacune etant adapté au type d'entrée à subdiviser
 *
 * 	Les methodes retourneront null en cas de taille à zéro , négative ou de tableau vide.
 *
 * 
 */
public class Partition {

	/**
	 * 
	 * partitionList() est une methode qui prends en premier parametre une Liste d'entier, et en second un entier.
	 * Les entiers de la liste mere vont etre intégrée dans des listes filles de la taille du second argument.
	 * La liste mere sera composée des listes filles d'entier.
	 * 
	 * Example : 
	 * partitionList(<1 ,2 ,3 ,4 ,5 ,6 ,7>, 3 ) => return < <1, 2, 3>, <4, 5, 6>, <7> >
	 * 
	 * @param liste  List<Integer>   La liste mere à subdiviser
	 * @param taille  int			 La taille que l'on souhaite des listes filles
	 * @return ArrayList<ArrayList<Integer>>	 
	 */
	public static ArrayList<ArrayList<Integer>> partitionList(List<Integer> liste, int taille) {
		if (taille <= 0 || liste.size()<= 0) {
			return null;
		}

		ArrayList<ArrayList<Integer>> grandeList = new ArrayList<>();
		ArrayList<Integer> petitList = new ArrayList<>();
		for (int index = 0; index < liste.size(); index++) {

			if (index % taille == 0) {
				petitList = new ArrayList<Integer>();
			}

			petitList.add(liste.get(index));

			if (((index + 1) % taille == 0) || (index == liste.size() - 1)) {
				grandeList.add(petitList);
			}
		}
		return grandeList;
	}

	/**
	 * 
	 *  partitionTableau() est une methode qui prends en premier parametre un Tableau d'entier, et en second un entier.
	 * Les entiers du tableau mere vont etre intégrée dans des tableaux filles de la taille du second argument.
	 * Le tableau mere devient un tableau en deux dimension, composée des tableaux filles.
	 * 
	 * Example : 
	 * partitionTableau([1 ,2 ,3 ,4 ,5], 3 ) => return [ [ 1, 2, 3], [4, 5] ]
	 * 
	 * @param tableauInt int[]	 La liste mere à subdiviser
	 * @param taille int		 La taille que l'on souhaite des listes filles
	 * @return int[][]
	 */
	
	public static int[][] partitionTableau(int[] tableauInt, int taille) {
		if (taille <= 0 || tableauInt.length <= 0 )  return null;
		
		int reste = tableauInt.length % taille;
		int nbPtTab = tableauInt.length / taille + (reste > 0 ? 1 : 0);

		int[][] gdTab = new int[nbPtTab][];
		for (int i = 0; i < (reste > 0 ? nbPtTab - 1 : nbPtTab); i++) {
			gdTab[i] = Arrays.copyOfRange(tableauInt, i * taille, i * taille + taille);
		}
		if (reste > 0) {
			gdTab[nbPtTab - 1] = Arrays.copyOfRange(tableauInt, (nbPtTab - 1) * taille, (nbPtTab - 1) * taille + reste);
		}
		return gdTab;
	}

	/**
	 * 
	 * partitionAtomic() est une methode qui prends en premier parametre une Liste d'objet, et en second un entier.
	 * Les objets de la liste mere vont etre intégrée dans des listes filles de la taille du second argument.
	 * La liste mere sera composée des listes filles d'objet.
	 * 
	 *  Example : 
	 * partitionAtomic(<O1 ,O2 ,O3 ,O4 ,O5 ,O6 ,O7>, 2 ) => return < <O1, O2 >,< O3, O4 >, < O5, O6>, <O7> >
	 * 
	 * @param liste  List<Objet> 	 La liste mere à subdiviser
	 * @param taille  int			 La taille que l'on souhaite des listes filles
	 * @return ArrayList<List<Objet>>
	 */
	public static List partitionAtomic(List<Object> liste, int taille) {
		if (taille <= 0 || liste.size()<= 0 ) {
			return null;
		}

		AtomicInteger compteur = new AtomicInteger();
		List listResult = new ArrayList<>();
		for (Object object : liste) {
			if (compteur.getAndIncrement() % taille == 0) {
				listResult.add(new ArrayList<>());
			}
			((List<Object>) listResult.get(listResult.size() - 1)).add(object);
		}
		return listResult;
	}

}
