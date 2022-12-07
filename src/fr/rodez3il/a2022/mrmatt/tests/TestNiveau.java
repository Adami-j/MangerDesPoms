package fr.rodez3il.a2022.mrmatt.tests;

import fr.rodez3il.a2022.mrmatt.sources.Commande;
import fr.rodez3il.a2022.mrmatt.sources.Niveau;
import jdk.jfr.StackTrace;

public class TestNiveau {

    //ok
    public static void testInitNiveaux()throws ArrayIndexOutOfBoundsException{
        try {
            Niveau n = new Niveau("src/niveaux/AppleTown/1-the-market.txt");
            Niveau n1 = new Niveau("src/niveaux/AppleTown/2-apple-bins.txt");
            Niveau n2 = new Niveau("src/niveaux/AppleTown/3-the-attic.txt");
            Niveau n3 = new Niveau("src/niveaux/AppleTown/5-no-sweat-charlie.txt");

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
            System.out.println("Test init niveaux KO");
        }
        System.out.println("Test init niveaux OK");
    }

    /**
     *
     */
    public static void testJouerCommandeVersMur(){
        Niveau n = new Niveau("src/niveaux/AppleTown/3-the-attic.txt");


        try {

            n.jouer(Commande.BAS);

            n.jouer(Commande.BAS);
            n.jouer(Commande.BAS);
            n.jouer(Commande.BAS);

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e + "Test Jouer commande vers mur KO");
        }
        System.out.println("Test Jouer commande vers mur OK");

    }

    public static void testPositionPostJouer(){
        Niveau n = new Niveau("src/niveaux/AppleTown/1-the-market.txt");

        int testx = n.getPositionJoueurX();
        int testy = n.getPositionJoueurY();

        n.jouer(Commande.HAUT);
        n.jouer(Commande.DROITE);

        if(testx == n.getPositionJoueurX()+1&&testy==n.getPositionJoueurY()-1){
            System.out.println("TestPositionPostJouer OK");
        }else {
            System.out.println("TestPositionPostJouer KO");
        }


    }

    public static void deplacementPossibleEtDeplacer(){
        Niveau n = new Niveau("src/niveaux/1rocher.txt");
        int testPosX = n.getPositionJoueurX();
        int testPosY= n.getPositionJoueurY();
        n.jouer(Commande.HAUT);

        n.deplacer(n.getPositionJoueurX(),n.getPositionJoueurY()+1);
        System.out.println(testPosX +" "+n.getPositionJoueurX()+" "+n.getPositionJoueurY()+" "+testPosY);
        if(n.getPositionJoueurX()==testPosX && testPosY+1 == n.getPositionJoueurY()){
            System.out.println("deplacementPossible OK");
        }else{
            System.out.println("deplacementPossible KO");
        }


    }

    //pas fonctionnelle car il faut tester avec la classe jeu avec encours est intermédiare etc
    public static void testGagner(){
        Niveau n = new Niveau("src/niveaux/1rocher.txt");
        n.jouer(Commande.DROITE);
        n.etatSuivant();
        n.jouer(Commande.BAS);
        n.etatSuivant();
        n.afficherEtatFinal();
        System.out.println(n.estGagne());
    }


    public static void main(String[] args) {
        testInitNiveaux();
        testJouerCommandeVersMur();
        testPositionPostJouer();
        deplacementPossibleEtDeplacer();
        testGagner();


    }
}
