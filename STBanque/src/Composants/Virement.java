package Composants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Virement extends Flux{
	
	private int numeroCompteEmetteur;

	public Virement(String commentaire, int identifiant, double montant, int numeroCompteCible, 
			boolean effectue, java.util.Date dateDuFlux, int numeroCompteEmetteur) {
		
		super(commentaire, identifiant, montant, numeroCompteCible, effectue, dateDuFlux);
		this.numeroCompteEmetteur = numeroCompteEmetteur;
	}

	public int getNumeroCompteEmetteur() {
		return this.numeroCompteEmetteur;
	}

	public void setNumeroCompteEmetteur(int numeroCompteEmetteur) {
		this.numeroCompteEmetteur = numeroCompteEmetteur;
	}
	
}

