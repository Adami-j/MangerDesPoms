package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Pomme extends ObjetPlateau {


    @Override
    public char afficher() {
        return '+';
    }

    @Override
    public boolean estMarchable(){
        return true;
    }


}
