package Composants;

public abstract class Compte {
	protected String libelle;
	protected double solde;
	protected int numeroCompte;
	protected Client cli;
	private static int nombreCompte = 1;
	
	public Compte(String libelle, Client cli, double solde) {
		this.libelle = libelle;
		this.solde = solde;
		setNumeroCompte(nombreCompte);
		nombreCompte ++;
		this.cli = cli;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde, Flux operation, int numeroCompte) {
		
		switch (operation.getClass().getName()) {
			
		case "Composants.Credit" :
			this.solde = solde + operation.getMontant();
			break;
		
		case "Composants.Debit" : 
			this.solde = solde - operation.getMontant();
			break;
			
		case "Composants.Virement" : 
			if (operation.getNumeroCompteCible() == numeroCompte) {

				this.solde = solde + operation.getMontant();

			} else if (operation.getNumeroCompteEmetteur() == numeroCompte) {
				this.solde = solde - operation.getMontant();
			}
			break;
		} 
	}

	public int getNumeroCompte() {
		return numeroCompte;
	}

	public void setNumeroCompte(int numeroCompte) {
		this.numeroCompte = numeroCompte;
	}
	
	public String toString() {
		  return  " Nom : "+ this.cli.getNom() + " Prénom : " + this.cli.getPrenom()  + " Numéro de client : " 
				  + this.cli.getNumero() + " / Compte : " + this.libelle + " numero : " + this.numeroCompte +
				  " Solde :" + this.solde;
	}
	
}
