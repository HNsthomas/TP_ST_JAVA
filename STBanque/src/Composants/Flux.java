package Composants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Flux {
	private String commentaire;
	private int identifiant;
	private	double montant;
	private int numeroCompteCible;
	private boolean effectue;
	java.util.Date dateDuFlux;
	
	public Flux(String commentaire, int identifiant, double montant, int numeroCompteCible, 
			boolean effectue, java.util.Date dateDuFlux ) {
		
		this.commentaire = commentaire;
		this.identifiant = identifiant;
		this.montant = montant;
		this.numeroCompteCible = numeroCompteCible;
		this.effectue = effectue;
		this.dateDuFlux = dateDuFlux;
	}
	
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getNumeroCompteCible() {
		return numeroCompteCible;
	}
	public void setNumeroCompteCible(int numeroCompteCible) {
		this.numeroCompteCible = numeroCompteCible;
	}
	public boolean isEffectue() {
		return effectue;
	}
	public void setEffectue(boolean effectue) {
		this.effectue = effectue;
	}
	public java.util.Date getDateDuFlux() {
		return dateDuFlux;
	}
	public void setDateDuFlux(java.util.Date dateDuFlux) {
		this.dateDuFlux = dateDuFlux;
	} 
	
	public int getNumeroCompteEmetteur() {
		return 0;
	}
	
	public String toString() {
		String str = new String("comment : " + this.commentaire + " id : " + this.identifiant + " mount : " + this.montant + " compte cible : " + this.numeroCompteCible + " done : " + this.effectue + " date : " + this.dateDuFlux);
		return str;
	}
	
}
