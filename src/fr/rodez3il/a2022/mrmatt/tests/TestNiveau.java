package fr.rodez3il.a2022.mrmatt.tests;

import fr.rodez3il.a2022.mrmatt.sources.Niveau;
import jdk.jfr.StackTrace;

public class TestNiveau {

    //ok
    public static void test()throws ArrayIndexOutOfBoundsException{
        try {
            Niveau n = new Niveau("src/niveaux/AppleTown/1-the-market.txt");
            Niveau n1 = new Niveau("src/niveaux/AppleTown/2-apple-bins.txt");
            Niveau n2 = new Niveau("src/niveaux/AppleTown/3-the-attic.txt");
            Niveau n3 = new Niveau("src/niveaux/AppleTown/5-no-sweat-charlie.txt");

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        test();
    }
}
