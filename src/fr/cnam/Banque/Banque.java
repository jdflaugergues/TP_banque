package fr.cnam.Banque;

import fr.cnam.Compte.Compte;
import fr.cnam.Compte.CompteEpargne;
import fr.cnam.Proprietaire.ProprietaireI;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 9.0 ${13/06/2015}
 */
public class Banque {

    private String nom;
    private Map<String, Compte> listeCompte;
    private int nbCompte;

    /**
     * Constructeur par d�faut
     */
    public Banque() {
        this.listeCompte = new HashMap<>();
    }

    /**
     * Constructeur par initialisation du nom de la banque.
     *
     * @param nom Nom de la banque
     */
    public Banque(String nom) {
        this();
        this.setNom(nom);
        this.nbCompte = 0;
    }

    /**
     * Retourne le prochain num�ro de compte disponible.
     *
     * @return Le num�ro de compte.
     */
    private String getNextCompteNumber() {
        return String.format("%04d", this.nbCompte + 1);
    }

    /**
     * Cr�e un compte bancaire et l'ajoute � la liste des comptes.
     *
     * @param proprietaire     Propri�taire du compte
     * @param montantDecouvert Montant du d�couvert autoris� du compte
     * @return Le compte cr��; null sinon
     */
    public Compte createCompte(ProprietaireI proprietaire, int montantDecouvert) {

        Compte compte = null;

        String numero = getNextCompteNumber();
        compte = new Compte(proprietaire, numero, montantDecouvert);
        this.listeCompte.put(numero, compte);
        this.nbCompte++;

        return compte;
    }

    /**
     * Cr�e un compte �pargne et l'ajoute � la liste des comptes.
     *
     * @param proprietaire     Propri�taire du compte �pargne
     * @param montantDecouvert Montant du d�couvert autoris� du compte �pargne
     * @param tauxInterets     Taux int�r�t du compte �pargne.
     * @return Le compte �pargne cr��; null sinon
     */
    public Compte createCompte(ProprietaireI proprietaire, int montantDecouvert, float tauxInterets) {

        Compte compte = null;

        String numero = getNextCompteNumber();
        compte = new CompteEpargne(proprietaire, numero, montantDecouvert, tauxInterets);
        this.listeCompte.put(numero, compte);
        this.nbCompte++;

        return compte;
    }

    /**
     * Recherche un compte � partir de son num�ro dans la liste des comptes.
     *
     * @param numero Num�ro du compte � rechercher
     * @return Le compte trouv�; null sinon
     */
    public Compte searchCompte(String numero) {
        return this.listeCompte.get(numero);
    }

    /**
     * Met a jour les int�r�ts des comptes Epargne
     */
    public void updateInterest() {
        for (Map.Entry<String, Compte> currentCompte : this.listeCompte.entrySet()) {
            if (currentCompte.getValue() instanceof CompteEpargne) {
                ((CompteEpargne) currentCompte.getValue()).calculerInteretQuinzaine();
            }
        }
    }

    /**
     * Supprime un compte de la banque
     *
     * @param numero Le num�ro du compte a supprimer
     * @return Vrai si le compte est supprim�, faux sinon.
     */
    public boolean deleteCompte(String numero) {

        boolean compteDeleted = false;

        Compte compteRemoved = this.listeCompte.remove(numero);

        return compteRemoved != null;
    }

    /**
     * Repr�sente la banque sous forme d'une chaine de caract�re.
     *
     * @return La banque sous forme d'une chaine de caract�re.
     */
    public String toString() {

        String banque = "";

        for (Map.Entry<String, Compte> currentCompte : this.listeCompte.entrySet()) {
            banque += currentCompte.toString() + "\n";
        }

        return banque;
    }

    /**
     * Stocke l'ensemble des comptes dans un fichier.
     */
    public boolean sauvegarderComptes() {

        boolean sauvegarde = false;
        FileOutputStream ficSortie = null;
        ObjectOutputStream oSortie = null;
        try {
            ficSortie = new FileOutputStream("comptes.ser");
            oSortie = new ObjectOutputStream(ficSortie);
            oSortie.writeObject(listeCompte);
            sauvegarde = true;
        } catch (FileNotFoundException e) {
            System.out.println("erreur cr�ation de fichier " + e.getMessage());
        } catch (IOException e) {
            System.out.println("erreur ecriture fichier " + e.getMessage());
        } finally {
            try {
                oSortie.close();
            } catch (IOException e) {
                System.out.println("erreur fermeture de fichier");
            }
        }
        return sauvegarde;
    }

    /**
     * Restitue les comptes du fichier.
     */
    public boolean restituerComptes() {

        boolean restitue = false;
        FileInputStream ficEntree = null;
        ObjectInputStream oEntree = null;
        try {
            ficEntree = new FileInputStream("comptes.ser");
            oEntree = new ObjectInputStream(ficEntree);
            listeCompte = (Map<String, Compte>) oEntree.readObject();
            restitue = true;
        } catch (FileNotFoundException e) {
            System.out.println("erreur ouverture de fichier " + e.getMessage());
        } catch (Exception e) {
            System.out.println("erreur " + e.getMessage());
        } finally {
            try {
                oEntree.close();
            } catch (IOException e) {
                System.out.println("erreur fermeture de fichier");
            }
        }
        return restitue;
    }

    //region getter/setter

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    //endregion
}
