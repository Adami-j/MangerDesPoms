package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Vide extends ObjetPlateau {


    @Override
    public char afficher() {
        return ' ';
    }

    @Override
    public boolean estVide(){
        return true;
    }
    @Override
    public boolean estMarchable(){
        return true;
    }


}
