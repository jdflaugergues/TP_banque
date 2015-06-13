package fr.cnam.Proprietaire;

import java.io.Serializable;

/**
 * Classe société possédant une adresse, implémentant l'interface ProprietaireI
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${19/05/2015}
 */
public class Societe implements ProprietaireI, Serializable {

    private String adresse;
    private String nom;

    /**
     * Constructeur par défaut
     */
    public Societe(){}

    /**
     * Constructeur par initialisation du nom de la société et de son adresse
     * @param nom
     * @param adresse
     */
    public Societe(String nom, String adresse){
        this.setNom(nom);
        this.setAdresse(adresse);
    }

    //region getter/setter

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("Argument nom must not be null.");

        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        if (adresse == null)
            throw new IllegalArgumentException("Argument adresse must not be null.");
        this.adresse = adresse;
    }
    //endregion

    /**
     * Authentifie la société à partir de son nom.
     * @return Le nom de la société
     */
    public String getIdentifiant(){
        return this.getNom();
    }

    /**
     * Localise la société à partir de son adresse.
     * @return L'adresse de la société.
     */
    public String getContact(){
        return this.getAdresse();
    }

    /**
     * Représente la société sous forme d'une chaine de caractère.
     * @return La société sous forme d'une chaine de caractère.
     */
    public String toString(){

        return  "Nom : " + this.getNom() + "\n" +
                "Adresse : " + this.getAdresse();
    }


}
