//1.1.1 La classe client
package Composants;

public class Client {
	
	  private String nomClient;
	  private String prenomClient;
	  private int numeroClient;
	  private static int nombreClient = 0;
	  
	  public Client(String cNom, String cPrenom)
	  {
	    nomClient = cNom;
	    prenomClient = cPrenom;
	    nombreClient++;
	    setNumeroClient(nombreClient);

	  }
	  
	  public String getNom() {
		  return nomClient;
	  }
	  
	  public void setNom(String cNom) {
		  nomClient = cNom;
	  }
	  
	  public String getPrenom() {
		  return prenomClient;
	  }
	  
	  public void setPrenom(String cPrenom) {
		  prenomClient = cPrenom;
	  }
	  
	  public int getNumero() {
		  return numeroClient;
	  }
	  
	  public void setNumeroClient(int cNumero) {
		  numeroClient = cNumero;
	  }
	  
	  public String toString() {
		  return  " Nom : "+ this.nomClient + " Prénom : " + this.prenomClient + " Numéro de compte : " + this.numeroClient;
	  }
	  
}
