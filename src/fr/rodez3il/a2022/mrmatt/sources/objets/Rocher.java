package fr.rodez3il.a2022.mrmatt.sources.objets;

public class Rocher extends ObjetPlateau {


    @Override
    public char afficher() {
        return '*';
    }


    @Override
    public boolean estPoussable(){
        return true;
    }

    @Override
    public boolean estGlissant(){
        return true;
    }



}
