package fr.rodez3il.a2022.mrmatt.sources;

import fr.rodez3il.a2022.mrmatt.sources.objets.Rocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;
import fr.rodez3il.a2022.mrmatt.sources.objets.Pomme;
import fr.rodez3il.a2022.mrmatt.sources.objets.Vide;
import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;

import java.util.ArrayList;
import java.util.List;

public class Niveau {
	
	// Les objets sur le plateau du niveau
	private ObjetPlateau[][] plateau;
	// Position du joueur

	private int nombrePommesRestant;
	private int positionJoueurX;
	private int positionJoueurY;
	private int nombreDeplacements;
	private static int TAILLE_HORIZONTALE=0;
	private static int TAILLE_VERTICALE=0;
	private boolean estIntermediaire=false;
	private boolean gagner = false;
	private boolean perdu = false;


  // Autres attributs que vous jugerez nécessaires...

	/**
	 * Constructeur public : crée un niveau depuis un fichier.
	 * mise à 0 des param pommes restantes, nombredeplacements et la position x y à 0
	 * chargement du niveau avec le param chemin
	 * @param chemin lien vers le fichier utiliser pourle jeu
	 * @author JULIENADAMI
	 */
	public Niveau(String chemin) {
		this.nombrePommesRestant=0;
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

	public boolean isEstIntermediaire() {
		return estIntermediaire;
	}

	public void setEstIntermediaire(boolean estIntermediaire) {
		this.estIntermediaire = estIntermediaire;
	}

	public boolean estPerdu(){
		return this.perdu;
	}

	public boolean estGagne(){
		return this.gagner;
	}

	/**
	 *Fonction split qui retourne un tableau de string spliter pour chaque retour à la ligne
	 * sans les deux valeurs de taille pour le plateau
	 * initialisation de la valeur de la taille horizontale et vertiale du plateau
	 *
	 * @param chemin
	 * @return String[]
	 */
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
	 * @author JULIENADAMI
	 */
	private void chargerNiveau(String chemin) {

		 String[] splitedNiveauWithoutNumbers = this.recupererListeLigneJeu(chemin);

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
						this.nombrePommesRestant++;
					}
				}

				compteurOccurenceSplitedNiveau++;

			}

			compteurOccurenceSplitedNiveau=0;

		}
	}



	/**
	 * Produit une sortie du niveau sur la sortie standard.
	 * affichage du nombre de pommes restant et du nombre de déplacements
	 * @author ADAMIJULIEN
	 */
	public void afficher() {

		for(int xVertical=0; xVertical<=TAILLE_VERTICALE-1; xVertical++){
			String ligne ="";
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE; yHorizontal++) {

					ligne+=this.getObjetPlateau()[xVertical][yHorizontal].afficher();
			}
			System.out.println(ligne);
		}

		System.out.println("Il reste "+this.nombrePommesRestant+" pommes en jeu et vous avez réalisé "+this.nombreDeplacements+" dépalcements.");
	}

	/**
	 *Retourne vrai si le déplacement est possible sinon faux
	 * @param dx pos de l'objet ou le joeur de déplace X
	 * @param dy pos de l'objet ou le joeur de déplace Y
	 * @return boolean
	 * @author ADAMIJULIEN
	 */
	private Boolean booleandeplacementPossible(int dx, int dy){

		if (dx<=17&&dx>=0&&dy<=30&&dy>=0&&this.getObjetPlateau()[dx][dy].estMarchable()){
			return true;
		}
		return false;
	}

	/**
	 *échange l'objet joueur avec l'objet défini à la position x,y du plateau
	 * @param deltaX
	 * @param deltaY
	 * @author ADAMIJULIEN
	 */
	public void deplacer(int deltaX, int deltaY){

		if((deltaY==this.positionJoueurY-1||deltaY==this.positionJoueurY+1)&&this.positionJoueurX==deltaX&&this.getObjetPlateau()[deltaX][deltaY].estMarchable()){

		}
		this.echanger(this.positionJoueurX,positionJoueurY,deltaX,deltaY);
		this.positionJoueurX = deltaX;
		this.positionJoueurY = deltaY;
	}


	/**
	 * Traite l'état suivant, si le rocher est à FIXE, on vérifie que sous lui, il n'y a rien
	 * si oui il passe à l'état CHUTE et on réalise les traitements est vide, est glissant
	 * @param r rocher
	 * @param x position x
	 * @param y position y
	 * @author ADAMIJULIEN
	 */
	public void etatSuivantVisiteur(Rocher r, int x, int y) {

		switch (r.getEtatRocher()){
			case CHUTE:
				if(x==this.getObjetPlateau().length-1){
					r.setEtatRocher(EtatRocher.FIXE);
					break;
				}
				if(this.getObjetPlateau()[x+1][y].estVide()){
					if(y==this.positionJoueurY && x+1< this.positionJoueurX){
						this.echanger(x,y,x+1,y);
						this.perdu=true;
						break;
					}
					this.echanger(x,y,x+1,y);

				}

				if(this.getObjetPlateau()[x+1][y].estGlissant()){
					if(x>0&&this.getObjetPlateau()[x+1][y-1].estVide()){
						this.echanger(x,y,x+1,y-1);
					}else if(y>0&&this.getObjetPlateau()[x+1][y+1].estVide()){
						this.echanger(x,y,x+1,y+1);
					}
					else {
						r.setEtatRocher(EtatRocher.FIXE);
					}

				}else{
					r.setEtatRocher(EtatRocher.FIXE);
				}

				break;
			case FIXE:
				if(x+1<TAILLE_VERTICALE&&this.getObjetPlateau()[x+1][y].estVide()){
					r.setEtatRocher(EtatRocher.CHUTE);
				}
				break;
		}
		if(r.getEtatRocher()==EtatRocher.CHUTE){
			this.setEstIntermediaire(true);

		}

	}


	/**
	 * balaie le plateau et ajoute une pomme si l'instance de l'objet est Pomme
	 * @param p Pomme
	 * @param x position x
	 * @param y position y
	 * @author ADAMIJULIEN
	 */
	public void etatSuivantVisiteur(Pomme p,int x, int y){
				nombrePommesRestant+=1;
	}


	/**
	 * Calcule l'état suivant du niveau.
	 * balaie le plateau et applique la méthode
	 * @author ADAMIJULIEN
	 */
	public void etatSuivant() {

		this.setEstIntermediaire(false);
		this.nombrePommesRestant=0;
		for(int xVertical=0; xVertical<=TAILLE_VERTICALE-1; xVertical++){
			for(int yHorizontal=0; yHorizontal<TAILLE_HORIZONTALE-1; yHorizontal++) {

					this.getObjetPlateau()[xVertical][yHorizontal].visiterPlateauCalculEtatSuivant
							(this,xVertical,yHorizontal);


			}
		}

		this.gagner = this.nombrePommesRestant ==0;


	}

  // Joue la commande C passée en paramètres

	/**
	 * retourne vrai si la commande est correscte sinon retourne faux
	 * @param c
	 * @return boolean
	 * @author ADAMIJULIEN
	 */
	public boolean jouer(Commande c) {

		switch (c){
			case QUITTER:
				this.perdu = true;
				 break;
			case ANNULER:
				this.perdu = true;
				break;
			case HAUT:
				if(this.booleandeplacementPossible(this.positionJoueurX-1,this.positionJoueurY)){
					this.deplacer(this.positionJoueurX-1,this.positionJoueurY);
					this.nombreDeplacements++;
					return true;
				}
				c=Commande.ERREUR;
				break;

			case BAS:
				if(this.booleandeplacementPossible(this.positionJoueurX+1,this.positionJoueurY)){
					this.deplacer(this.positionJoueurX+1,this.positionJoueurY);
					this.nombreDeplacements++;
					return true;
				}
				break;
			case GAUCHE:
				if(this.booleandeplacementPossible(this.positionJoueurX,this.positionJoueurY-1)){
					this.deplacer(this.positionJoueurX,this.positionJoueurY-1);
					this.nombreDeplacements++;
					return true;
				}
				break;
			case DROITE:
				if(this.booleandeplacementPossible(this.positionJoueurX,this.positionJoueurY+1)){
					this.deplacer(this.positionJoueurX,this.positionJoueurY+1);
					this.nombreDeplacements++;
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
	 *  @author Julien ADAMI
	 */
	public void afficherEtatFinal() {
		if(this.nombrePommesRestant==0){
			this.gagner = true;
		}
		if(this.gagner){
			System.out.println("Bravo c'est gagné");
		}else {
			System.out.println("Vous avez perdu");
		}

	}

	/**
	 * renvoie vrai si le jeu est en état intermédiaire et si encours renvoie true
	 * @return boolean estIntermediaire vrai?
	 *  @author Julien ADAMI
	 */
	public boolean estIntermediaire() {

		return this.isEstIntermediaire()&&this.enCours();
	}


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
	  if(this.getObjetPlateau()[destinationX][destinationY].estMarchable()&&
			  !this.getObjetPlateau()[destinationX][destinationY].estVide()){
		  if(this.getObjetPlateau()[destinationX][destinationY].afficher()=='+'){
			  this.plateau[sourceX][sourceY]=new Vide();
			  this.plateau[destinationX][destinationY]=objetPlateau;
		  }
		  this.plateau[sourceX][sourceY]=new Vide();
		  this.plateau[destinationX][destinationY]=objetPlateau;

	  }else{

		  this.plateau[sourceX][sourceY]=this.plateau[destinationX][destinationY];
		  this.plateau[destinationX][destinationY]=objetPlateau;
	  }


  }

	public boolean enCours() {
		return !this.perdu&&!this.gagner;
	}
}
