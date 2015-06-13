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
     * Constructeur par défaut
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
     * Retourne le prochain numéro de compte disponible.
     *
     * @return Le numéro de compte.
     */
    private String getNextCompteNumber() {
        return String.format("%04d", this.nbCompte + 1);
    }

    /**
     * Crée un compte bancaire et l'ajoute à la liste des comptes.
     *
     * @param proprietaire     Propriétaire du compte
     * @param montantDecouvert Montant du découvert autorisé du compte
     * @return Le compte créé; null sinon
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
     * Crée un compte épargne et l'ajoute à la liste des comptes.
     *
     * @param proprietaire     Propriétaire du compte épargne
     * @param montantDecouvert Montant du découvert autorisé du compte épargne
     * @param tauxInterets     Taux intérêt du compte épargne.
     * @return Le compte épargne créé; null sinon
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
     * Recherche un compte à partir de son numéro dans la liste des comptes.
     *
     * @param numero Numéro du compte à rechercher
     * @return Le compte trouvé; null sinon
     */
    public Compte searchCompte(String numero) {
        return this.listeCompte.get(numero);
    }

    /**
     * Met a jour les intérêts des comptes Epargne
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
     * @param numero Le numéro du compte a supprimer
     * @return Vrai si le compte est supprimé, faux sinon.
     */
    public boolean deleteCompte(String numero) {

        boolean compteDeleted = false;

        Compte compteRemoved = this.listeCompte.remove(numero);

        return compteRemoved != null;
    }

    /**
     * Représente la banque sous forme d'une chaine de caractère.
     *
     * @return La banque sous forme d'une chaine de caractère.
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
            System.out.println("erreur création de fichier " + e.getMessage());
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
