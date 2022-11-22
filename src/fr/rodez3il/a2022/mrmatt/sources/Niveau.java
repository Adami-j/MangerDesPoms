package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur
	private int nombrePommes;
	private int positionJoueurX;
	private int positionJoueurY;
	private int nombreDeplacements;
	private static int TAILLE_HORIZONTALE=0;
	private static int TAILLE_VERTICALE=0;
	
  // Autres attributs que vous jugerez nécessaires...

	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * @param chemin .....
	 * @author .............
	 */
	public Niveau(String chemin) {
		this.nombrePommes=0;
		this.nombreDeplacements=0;
		this.positionJoueurX=0;
		this.positionJoueurY=0;

		this.chargerNiveau(chemin);

	}

	public ObjetPlateau[][] getObjetPlateau(){
		return this.plateau;
	}
	public void setObjetPlateau(ObjetPlateau[][] plateau){
		this.plateau = plateau;
	}


	/**
	 * Fonction qui lit le fichier provenant du lien du paramètre chemin
	 * Place dans un string le niveau brut
	 * ensuite on split à chaque \n du fichier
	 * 1ere valeur split taille horizontale ensuite verticale et enfin
	 * ligne par ligne du fichier on obtient le terrain
	 * enfin on assigne chaque caractère à un emplacement dans le tableau du terrain
	 * @param chemin
	 */
	private void chargerNiveau(String chemin) {
		String niveauBrut = Utils.lireFichier(chemin);
		String[] splitedNiveau = niveauBrut.split("\n");
		String caractereAssemblageTH = splitedNiveau[0].charAt(0) +""+ splitedNiveau[0].charAt(1);
		String caractereAssemblageTV = splitedNiveau[1].charAt(0) +""+ splitedNiveau[1].charAt(1);
		 int TAILLE_HORIZONTALE = Integer.valueOf(caractereAssemblageTH);
		 int TAILLE_VERTICALE =  Integer.valueOf(caractereAssemblageTV);
		 this.TAILLE_HORIZONTALE = TAILLE_HORIZONTALE;
		 this.TAILLE_VERTICALE = TAILLE_VERTICALE;
		this.setObjetPlateau(new ObjetPlateau[TAILLE_HORIZONTALE][TAILLE_VERTICALE]);
		ObjetPlateau[][] plateau = this.getObjetPlateau();
		System.out.println(splitedNiveau[5]);
		int compteurOccurenceSplitedNiveau = 0;
		for(int xVertical=0; xVertical<TAILLE_VERTICALE; xVertical++){
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++){

				char caractereCourrant = splitedNiveau[xVertical].charAt(splitedNiveau[xVertical].length()-1);
				ObjetPlateau objetCourrant;
				if(caractereCourrant=='*'||caractereCourrant=='+'||caractereCourrant=='H'
						||caractereCourrant=='-'||caractereCourrant=='#'||caractereCourrant==' '){
					 objetCourrant = ObjetPlateau.depuisCaractere(caractereCourrant);
				}

				if(caractereCourrant=='H'){
					this.positionJoueurX=yHorizontal;
					this.positionJoueurY=xVertical;
				}

				if(caractereCourrant=='+'){
					this.nombrePommes++;
				}


				compteurOccurenceSplitedNiveau++;
			}
			compteurOccurenceSplitedNiveau=0;

		}
	}



	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * ................
	 */
	public void afficher() {
		System.out.println(TAILLE_HORIZONTALE+TAILLE_VERTICALE);
		for(int xVertical=0; xVertical<TAILLE_HORIZONTALE; xVertical++){
			for(int yHorizontal=0; yHorizontal<TAILLE_VERTICALE; yHorizontal++) {
				System.out.println(this.getObjetPlateau()[xVertical][yHorizontal]);
			}

		}
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
	 * @author Julien ADAMI
	 */
  private void echanger(int sourceX, int sourceY, int destinationX, int destinationY){
	  ObjetPlateau objetPlateau = this.plateau[sourceX][sourceY];
	  this.plateau[sourceX][sourceY]=this.plateau[destinationX][destinationY];
	  this.plateau[destinationX][destinationY]=objetPlateau;

  }

	public static void main(String[] args) {
		Niveau n = new Niveau("src/niveaux/AppleTown/1-the-market.txt");
		n.afficher();
	}

}
