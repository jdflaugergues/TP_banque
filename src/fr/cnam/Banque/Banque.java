package fr.cnam.Banque;

import fr.cnam.Compte.Compte;
import fr.cnam.Compte.CompteEpargne;
import fr.cnam.Personne.Personne;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 6.0 ${11/05/2015}
 */
public class Banque {

    private static final int MAX_COMPTE = 10;

    private String nom;
    private Compte[] listeCompte;
    private int nbCompte;

    /**
     * Constructeur par défaut
     */
    public Banque(){}

    /**
     * Constructeur par initialisation du nom de la banque.
     * @param nom Nom de la banque
     */
    public Banque(String nom){
        this.setNom(nom);
        this.listeCompte = new Compte[MAX_COMPTE];
        this.nbCompte = 0;
    }

    /**
     * Retourne le prochain numéro de compte disponible.
     * @return Le numéro de compte.
     */
    private String getNextCompteNumber(){
        return String.format("%04d",this.nbCompte+1);
    }

    /**
     * Crée un compte bancaire et l'ajoute à la liste des comptes.
     * @param proprietaire Propriétaire du compte
     * @param montantDecouvert Montant du découvert autorisé du compte
     * @return Le compte créé; null sinon
     */
    public Compte createCompte(Personne proprietaire, int montantDecouvert){

        Compte compte = null;

        if (this.nbCompte >= MAX_COMPTE) {
            System.out.println("Impossible de créer un compte supplémentaire, la limite fixée à " + this.MAX_COMPTE + "est atteinte.");
        }else{
            String numero = getNextCompteNumber();
            compte = new Compte(proprietaire,numero,montantDecouvert);
            this.listeCompte[this.nbCompte] = compte;
            this.nbCompte++;
        }
        return compte;
    }

    /**
     * Crée un compte épargne et l'ajoute à la liste des comptes.
     * @param proprietaire Propriétaire du compte épargne
     * @param montantDecouvert Montant du découvert autorisé du compte épargne
     * @param tauxInterets Taux intérêt du compte épargne.
     * @return Le compte épargne créé; null sinon
     */
    public Compte createCompte(Personne proprietaire, int montantDecouvert,float tauxInterets){

        Compte compte = null;

        if (this.nbCompte >= MAX_COMPTE) {
            System.out.println("Impossible de créer un compte supplémentaire, la limite fixée à " + this.MAX_COMPTE + "est atteinte.");
        }else{
            String numero = getNextCompteNumber();
            compte = new CompteEpargne(proprietaire,numero,montantDecouvert,tauxInterets);
            this.listeCompte[this.nbCompte] = compte;
            this.nbCompte++;
        }
        return compte;
    }

    /**
     * Recherche un compte à partir de son numéro dans la liste des comptes.
     * @param numero Numéro du compte à rechercher
     * @return Le compte trouvé; null sinon
     */
    public Compte searchCompte(String numero){
        Compte compte = null;

        for (Compte currentCompte : this.listeCompte){
            if (currentCompte == null)
                break;

            if (currentCompte.getNumero().equals(numero)) {
                compte = currentCompte;
                break;
            }
        }
        return compte;
    }

    /**
     * Met a jour les intérêts des comptes Epargne
     */
    public void updateInterest(){
        for (Compte currentCompte : this.listeCompte){
            if (currentCompte == null)
                break;

            if (currentCompte instanceof CompteEpargne) {
                ((CompteEpargne)currentCompte).calculerInteretQuinzaine();
                break;
            }
        }
    }

    /**
     * Supprime un compte de la banque
     * @param numero Le numéro du compte a supprimer
     * @return Vrai si le compte est supprimé, faux sinon.
     */
    public boolean deleteCompte(String numero){

        boolean compteDeleted = false;

        for (int indexCurrentCompte = 0;indexCurrentCompte < this.nbCompte;indexCurrentCompte++){

            if (this.listeCompte[indexCurrentCompte].getNumero().equals(numero)) {
                this.listeCompte[indexCurrentCompte] = this.listeCompte[nbCompte-1];
                this.listeCompte[nbCompte-1] = null;
                compteDeleted = true;
                break;
            }
        }

        return compteDeleted;
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
