package fr.cnam.Proprietaire;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${19/05/2015}
 */
public abstract class Proprietaire {

    private String nom;

    /**
     * Constructeur par défaut
     */
    public Proprietaire(){}

    /**
     * Constructeur par initialisation du nom du propriétaire.
     * @param nom Nom du propriétaire
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
     * Permet d'authentifier le propriétaire.
     * @return L'authentification du propriétaire
     */
    abstract public String sauthentifier();

    /**
     * Permet de localiser le propriétaire pour le contacter.
     * @return La localisation du propriétaire.
     */
    abstract public String localiser();

}
