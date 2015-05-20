package fr.cnam.Proprietaire;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${19/05/2015}
 */
public abstract class Proprietaire {

    private String nom;

    /**
     * Constructeur par d�faut
     */
    public Proprietaire(){}

    /**
     * Constructeur par initialisation du nom du propri�taire.
     * @param nom Nom du propri�taire
     */
    public Proprietaire(String nom){
        this.setNom(nom);
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
    //endregion

    /**
     * Permet d'authentifier le propri�taire.
     * @return L'authentification du propri�taire
     */
    abstract public String sauthentifier();

    /**
     * Permet de localiser le propri�taire pour le contacter.
     * @return La localisation du propri�taire.
     */
    abstract public String localiser();

}
