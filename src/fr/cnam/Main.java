package fr.cnam;

import fr.cnam.Compte.*;
import fr.cnam.interfaceUser.*;
import fr.cnam.Journal.*;

/**
 * @author Jonathan de Flaugergues
 * @version 2.0
 */
public class Main {

    public static void main(String[] args) {

        System.setProperty("file.encoding", "UTF-8");

        System.out.println("**************************************************");
        System.out.println("*        TP 06 - Structure et déploiement        *");
        System.out.println("*                      v 2.0                     *");
        System.out.println("**************************************************");

        Journal journal = Journal.getInstance();
        Compte compteA = new Compte("001",100);
        String choice;

        do{
            choice = IhmTextCompte.lireChoix();
            if (!choice.equals("X")) {
                float somme = IhmTextCompte.lireSomme();

                switch (choice) {
                    case "D":
                        compteA.debiter(somme);
                        break;
                    case "C":
                        compteA.crediter(somme);
                }
            }

        }while(!choice.equals("X"));


        System.out.println("**************************************************");
        System.out.println("*             Contenu du journal                 *");
        System.out.println("**************************************************");

        System.out.println(journal.getJournal());
    }
}
