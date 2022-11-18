package fr.rodez3il.a2022.mrmatt.sources.objets;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;

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

    @Override
    public void visiterPlateauCalculEtatSuivant(Niveau plateau, int x, int y) {
        plateau.etatSuivantVisiteur(this, x, y);
    }



}
