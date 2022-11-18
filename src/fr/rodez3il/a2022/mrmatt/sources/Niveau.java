package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur
	private int joueurX;
	private int joueurY;
	
  // Autres attributs que vous jugerez nécessaires...
  
	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin .....
	 * @author .............
	 */
	public Niveau(String chemin) {


	}

	private void chargerNiveau(String chemin) {

		String fichierChargé= Utils.lireFichier(chemin);

		whil


	}



	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * ................
	 */
	public void afficher() {
    // TODO
	}

  // TODO : patron visiteur du Rocher...
	public void etatSuivantVisiteur(Rocher r, int x, int y) {
    
	}

	/**
	 * Calcule l'état suivant du niveau.
	 * ........
	 * @author 
	 */
	public void etatSuivant() {
    // TODO
	}


  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {return false;}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {
		return false;
	}

	/**
	 * Affiche l'état final (gagné ou perdu) une fois le jeu terminé.
	 */
	public void afficherEtatFinal() {
	}

	/**
	 */
	public boolean estIntermediaire() {return false;}


	/**
	 *Métohde qui échange la position de deux objets selon la position souceXY et destinationXY
	 * @param sourceX
	 * @param sourceY
	 * @param destinationX
	 * @param destinationY
	 *   //car il ne faut pas qu'elle soit accessible en dehors de sa classe
	 */
  private void echanger(int sourceX, int sourceY, int destinationX, int destinationY){
	  ObjetPlateau objetPlateau = this.plateau[sourceX][sourceY];
	  this.plateau[sourceX][sourceY]=this.plateau[destinationX][destinationY];
	  this.plateau[destinationX][destinationY]=objetPlateau;

  }


}
