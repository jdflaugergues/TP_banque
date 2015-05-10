package fr.cnam.interfaceUser;

import java.util.Scanner;

/**
 * @author Jonathan de Flaugergues
 * @version 2.0
 */
public class IhmTextCompte {

    /**
     * Permet de lire une somme au clavier.
     * @return La somme lue.
     */
    public static float lireSomme(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Veuillez saisir une somme : ");
        float somme = sc.nextFloat();
        //sc.close();

        return somme;
    }

    /**
     * Permet de lire le choix de l'opération à faire sur le compte en banque.
     * Créditer ou Débiter.
     * @return L'opération à effectuer.
     */
    public static String lireChoix(){
        Scanner sc = new Scanner(System.in);

        System.out.print("\nVoulez-vous créditer ou débiter le compte (D/C) ? ");
        String response = sc.next();
        while (response == null || ((!response.equals("D")) && (!response.equals("C") && (!response.equals("X"))))){
            System.out.println("Choix incorrect !" );
            System.out.print("Voulez-vous créditer ou débiter le compte (D/C) ?");
            response = sc.next();
        }
        return response;
    }
}
