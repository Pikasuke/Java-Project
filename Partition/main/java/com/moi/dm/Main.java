package com.moi.dm;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.moi.dm.models.Partition;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int tableauEntier[] = { 1, 2, 3, 4, 5 };
		List listes = new ArrayList<Integer>();
		listes.add(1);
		listes.add(2);
		listes.add(3);
		listes.add(4);
		listes.add(5);

		//partitione une Liste d'entier
		// => return < <1, 2, 3>, <4, 5> >
		Partition.partitionList(listes, 2);
		
		//partitione un tableau d'entier
		// => return [ [ 1, 2, 3], [4, 5] ]
		Partition.partitionTableau(tableauEntier, 2);
		
		//partitione une Liste d'objet
		// => return < <O1, O2 >,< O3, O4 >, < O5> >
		Partition.partitionAtomic(listes, 2);
		

	}
	
	

	
	

	

}
