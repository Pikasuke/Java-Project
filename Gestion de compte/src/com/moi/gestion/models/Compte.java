package com.moi.gestion.models;

import java.util.ArrayList;
import java.util.List;

public class Compte {

	private int id;
	private String numero ;
	private double solde;
	private Client client;
	private List<Operation> operations = new ArrayList<>();
	
	
	
	@Override
	public String toString() {
		return "Compte [id=" + id + ", numero=" + numero + ", solde=" + solde + ", client=" + client.getNom() + " , " + client.getPrenom() + "]";
	}


	public Compte() {
		
	}
	
	
	public Compte(int id, String numero, int solde, Client client) {
		super();
		this.id = id;
		this.numero = numero;
		this.solde = solde;
		this.client = client;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public void addOperation(Operation ope) {
		operations.add(ope);
		calculCompte(ope);
	}
	
	public double calculCompte(Operation ope) {
		double result = 0;
		if(ope.getType().equals("RETR")) 
			 solde = solde-ope.getMontant();
		if(ope.getType().equals("VERS")) 
			 solde = solde+ope.getMontant();
		return solde ;
	}
	
	
	
}
