package fr.rodez3il.a2022.mrmatt.sources;


import fr.rodez3il.a2022.mrmatt.sources.objets.EtatRocher;
import fr.rodez3il.a2022.mrmatt.sources.objets.ObjetPlateau;

public class Rocher extends ObjetPlateau {
    private EtatRocher etatRocher=EtatRocher.FIXE;
    public Rocher() {
        this.etatRocher = etatRocher;
    }



    @Override
    public char afficher() {

        return '*';
    }

    public EtatRocher getEtatRocher() {
        return etatRocher;
    }

    public void setEtatRocher(EtatRocher etatRocher) {
        this.etatRocher = etatRocher;
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
