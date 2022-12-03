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

	public int getPositionJoueurX() {
		return positionJoueurX;
	}

	public int getPositionJoueurY() {
		return positionJoueurY;
	}

	private String[] recupererListeLigneJeu(String chemin){
		String niveauBrut = Utils.lireFichier(chemin);
		String[] splitedNiveau = niveauBrut.split("\n");
		String caractereAssemblageTH = splitedNiveau[0].charAt(0) +""+ splitedNiveau[0].charAt(1);
		String caractereAssemblageTV = splitedNiveau[1].charAt(0) +""+ splitedNiveau[1].charAt(1);
		int TAILLE_HORIZONTALE = Integer.valueOf(caractereAssemblageTH);
		int TAILLE_VERTICALE =  Integer.valueOf(caractereAssemblageTV);
		this.TAILLE_HORIZONTALE = TAILLE_HORIZONTALE;
		this.TAILLE_VERTICALE = TAILLE_VERTICALE;

		String[] splitedNiveauWithoutNumbers = new String[TAILLE_VERTICALE];
		int compteur =0;
		int x=0;
		for(String charactGame : splitedNiveau){

			if (compteur>1 && !charactGame.startsWith(" ")){

				splitedNiveauWithoutNumbers[x]=charactGame;
				x++;
			}
			compteur++;
		}

		return splitedNiveauWithoutNumbers;
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

		 String[] splitedNiveauWithoutNumbers = new String[TAILLE_VERTICALE];
		 splitedNiveauWithoutNumbers = this.recupererListeLigneJeu(chemin);

		this.setObjetPlateau(new ObjetPlateau[TAILLE_VERTICALE][TAILLE_HORIZONTALE]);
		ObjetPlateau[][] plateau = this.getObjetPlateau();

		int compteurOccurenceSplitedNiveau = 0;
		for(int xVertical=0; xVertical<TAILLE_VERTICALE; xVertical++){

			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++){


				char caractereCourrant = splitedNiveauWithoutNumbers[xVertical].charAt(compteurOccurenceSplitedNiveau);

				ObjetPlateau objetCourrant;

				if(caractereCourrant=='*'||caractereCourrant=='+'||caractereCourrant=='H'
						||caractereCourrant=='-'||caractereCourrant=='#'||caractereCourrant==' '){
					plateau[xVertical][yHorizontal]= ObjetPlateau.depuisCaractere(caractereCourrant);
					if(caractereCourrant=='H'){
						this.positionJoueurX=xVertical;
						this.positionJoueurY=yHorizontal;
					}

					if(caractereCourrant=='+'){
						this.nombrePommes++;
					}
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

		for(int xVertical=0; xVertical<=TAILLE_VERTICALE-1; xVertical++){
			String ligne ="";
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++) {

					ligne+=this.getObjetPlateau()[xVertical][yHorizontal].afficher();
			}
			System.out.println(ligne);
		}
	}

	/**
	 *
	 * @param dx
	 * @param dy
	 * @return
	 */
	private Boolean booleandeplacementPossible(int dx, int dy){
		System.out.println(dx);
		System.out.println(this.getObjetPlateau()[dx][dy-1]);
		if (dx<=17&&dx>=0&&dy<=30&&dy>=0&&this.getObjetPlateau()[dx][dy].estMarchable()){
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param deltaX
	 * @param deltaY
	 */
	public void deplacer(int deltaX, int deltaY){
		this.echanger(this.positionJoueurX,positionJoueurY,deltaX,deltaY);
		this.positionJoueurX = deltaX;
		this.positionJoueurY = deltaY;
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
		for(int xVertical=0; xVertical<=TAILLE_VERTICALE-1; xVertical++){
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++) {
				if(this.getObjetPlateau()[xVertical][yHorizontal].afficher()=='*'){

				}

			}
		}



	}


  // Illustrez les Javadocs manquantes lorsque vous coderez ces méthodes !
  
	public boolean enCours() {return false;}

  // Joue la commande C passée en paramètres
	public boolean jouer(Commande c) {

		switch (c){
			case QUITTER:
				return false;
			case ANNULER:
				break;
			case HAUT:
				if(this.booleandeplacementPossible(this.positionJoueurX-1,this.positionJoueurY)){
					this.deplacer(this.positionJoueurX-1,this.positionJoueurY);
					return true;
				}
				break;
			case BAS:
				if(this.booleandeplacementPossible(this.positionJoueurX+1,this.positionJoueurY)){
					this.deplacer(this.positionJoueurX+1,this.positionJoueurY);
					System.out.println("coucou");
					return true;
				}
				break;
			case GAUCHE:
				if(this.booleandeplacementPossible(this.positionJoueurX,this.positionJoueurY-1)){
					this.deplacer(this.positionJoueurX+1,this.positionJoueurY-1);
					return true;
				}
				break;
			case DROITE:
				if(this.booleandeplacementPossible(this.positionJoueurX,this.positionJoueurY+1)){
					this.deplacer(this.positionJoueurX,this.positionJoueurY+1);
					return true;
				}
				break;
			case ERREUR:
				break;

		}

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

}
