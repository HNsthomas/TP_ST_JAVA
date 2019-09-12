package test;

import java.util.Date;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import Composants.Client;
import Composants.Compte;
import Composants.CompteCourant;
import Composants.CompteEpargne;
import Composants.Credit;
import Composants.Debit;
import Composants.Flux;
import Composants.Virement;

public class Main {
	

	//Création des tableaux Clients, Comptes, Flux. 
	private static Client[] collection = genererClientTab(3); 
	//Création du tableau en fonction du nombre de client
	private static Compte[] collectionCompte = genererCompteTab(3, collection);
	//Création du tableau Hashtable	
	private static Hashtable hashtable = getHashtable(collectionCompte);
	private static Flux[] flux = new Flux[0];
	
	public static void main(String[] args) {	

		
		System.out.println("Liste des clients! :");
		affichage(collection);
		
		System.out.println("\n liste des comptes! :");
		affichageCompte(collectionCompte);
		
		//1.3.4 Création du tableau des flux
		flux = addFluxInTab("debit", "debittest", 50, 1,flux);
		for(Compte tab : collectionCompte) {
			if(tab.getClass().getName() == "Composants.CompteCourant")
			 flux = addFluxInTab("credit", "blabla", 100.50, tab.getNumeroCompte(), flux);
		}
		for(Compte tab : collectionCompte) {
			if(tab.getClass().getName() == "Composants.CompteEpargne")
			 flux = addFluxInTab("credit", "blabla", 1500, tab.getNumeroCompte(), flux);
		}
	
		flux = addFluxInTab("virement", "debittest", 50, 2,flux, 1);
		

			
		gererLesOperations(collectionCompte,flux);
		
		System.out.println("\n Liste des comptes apèrs les opérations! :");
		affichageCompte(collectionCompte);
		
		readJson();
		for(Flux f : flux) {
			System.out.println(f);
		}
		readXML();
		affichageCompte(collectionCompte);
		
	}
	//-------------------Creation de la table client-------------------//
	public static Client[] genererClientTab(int nbClients) {
	
	Client[] tab = new Client[nbClients]; 
			
			for (int i=0; i < nbClients; i++) {
				tab[i] = new Client( "nom"+(i+1), "prenom"+(i+1));
			}
			return tab;
	}
	
	//-----------------Affichage de la table Client------------------//
	static void affichage(Client[] collection) {
		  for(Client c : collection)
		   System.out.println(c.toString());
		}
	
	//----------------Creation de la table compte---------------------//
	public static Compte[] genererCompteTab(int nbClients, Client[] collection) {
		Compte[] tabCompte = new Compte[nbClients*2];
		
		for (int i = 0; i < nbClients; i++ ) {
		tabCompte[2*i] = new CompteCourant("Courant", collection[i], 0);
		tabCompte[2*i+1] = new CompteEpargne("Epargne", collection[i], 0);
		}
		return tabCompte;
	}
	
	//---------------Affichage de la table Compte ------------------//
	static void affichageCompte(Compte[] collection) {
		  for(Compte c : collection)
		   System.out.println(c.toString());
		}
	
	//--------------Créer Hashtable compte--------------------------//
	public static Hashtable getHashtable(Compte[] comptes) {
		
		Hashtable <String, Compte> tab = new Hashtable<String, Compte>();
		Integer i = 0;
		for(Compte compte : comptes) {
			tab.put(i.toString(), compte);
			i++;
		}
		return tab;
	}
	//----------------Création Tableau Flux-----------------------//
	public static Flux[] addFluxInTab(String fluxType,String comment, double mount, int numeroCompteCible, Flux[] flux) {
		Flux[] tab = new Flux[flux.length + 1];
		int i = 0;
		for(Flux flu : flux) {
			tab[i] = flu;
			i++;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		if(fluxType == "debit")
			tab[i] = new Debit(comment, i, mount, numeroCompteCible, true, date);
		else if(fluxType == "credit")
			tab[i] = new Credit(comment, i, mount, numeroCompteCible, true, date);
		return tab;
	}
	public static Flux[] addFluxInTab(String fluxType,String comment, double mount, int numeroCompteCible, Flux[] flux, int numeroCompteEmetteur) {
		Flux[] tab = new Flux[flux.length + 1];
		int i = 0;
		for(Flux flu : flux) {
			tab[i] = flu;
			i++;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		if(fluxType.equals("virement")) {
			tab[i] = new Virement(comment, i, mount, numeroCompteCible, true, date, numeroCompteEmetteur);
		} else if(fluxType.equals("debit")) {
			tab[i] = new Debit(comment, i, mount, numeroCompteCible, true, date);
		}
		else if(fluxType.equals("credit")) {
			tab[i] = new Credit(comment, i, mount, numeroCompteCible, true, date);
		}
		return tab;
	}
	
	//----------------Gestion des flux et mise à jour automatique des comptes----------------------------//
	public static Compte[] gererLesOperations(Compte[] tabCompte, Flux[] flux) {
		
		for (int i=0; i < flux.length ; i++) {
			for (int j = 0; j < tabCompte.length ; j++) {
				
				if ((tabCompte[j].getNumeroCompte() == flux[i].getNumeroCompteCible()) 
						|| (tabCompte[j].getNumeroCompte() == flux[i].getNumeroCompteEmetteur()) ) { 
					
				tabCompte[j].setSolde(tabCompte[j].getSolde(), flux[i], tabCompte[j].getNumeroCompte());
				}
			}
;		}
		return tabCompte;
	}
	
	public static Path getPath() {
        Path test = Paths.get("src/flux.json");
        System.out.println(test.toAbsolutePath());
        return test;
	}
	
	public static void readJson() {
		JSONParser parser = new JSONParser();
		String path = getPath().toString();
		try (Reader reader = new FileReader(path)) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			for (Integer i = 1; i <= jsonObject.size(); i++) {
				String elName = "el" + i.toString();
				JSONObject data = (JSONObject) jsonObject.get(elName);
				String fluxType =  (String) data.get("fluxType");
				String comm =  (String) data.get("commentaire");
				double mount =  (double) data.get("montant");
				Long id = (Long) data.get("identifiant");
				flux = addFluxInTab(fluxType, comm, mount, id.intValue(), flux, 0);
		}
		
		
	} catch (Exception e) {
        e.printStackTrace();
		}
	}
	
	public static void readXML() {
		try {
	         File inputFile = new File("src/clients.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("compte");
	         System.out.println("----------------------------");
	         Compte compte;
	         Client client;
	         int i = 3;
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            i++;
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               
	               String nom = eElement.getElementsByTagName("nom").item(0).getTextContent();
	               String prenom = eElement.getElementsByTagName("prenom").item(0).getTextContent();
	               String idClient = eElement.getElementsByTagName("numeroDeClient").item(0).getTextContent();
	               client = new Client(nom, prenom);
	               client.setNumeroClient(Integer.parseInt(idClient));
	               String libelle = eElement.getElementsByTagName("libelle").item(0).getTextContent();
	               String solde = eElement.getElementsByTagName("solde").item(0).getTextContent();
	               if(eElement.getAttribute("type").equals("1"))
	            	   compte = new CompteCourant(libelle, client, Double.parseDouble(solde));
	               else
	            	   compte = new CompteEpargne(libelle, client, Double.parseDouble(solde));
	               collectionCompte = updateComptArray(collectionCompte, compte );

	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public static Compte[] updateComptArray(Compte[] comptes, Compte compte ) {
		
		Compte[] cpt = new Compte[comptes.length + 1];
		int i = 0;
		for(Compte c : comptes) {
			cpt[i] = c;
			i++;
		}
		cpt[i] = compte;
		return cpt;
	}

}
