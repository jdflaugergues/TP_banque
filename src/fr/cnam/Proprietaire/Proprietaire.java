package fr.cnam.Proprietaire;

/**
 * Interface d'un Propriétaire.
 *
 * @author Jonathan de Flaugergues
 * @version 7.0 ${21/05/2015}
 */
public interface Proprietaire {

    /**
     * Permet d'authentifier le propriétaire.
     * @return L'authentification du propriétaire
     */
    String sauthentifier();

    /**
     * Permet de localiser le propriétaire pour le contacter.
     * @return La localisation du propriétaire.
     */
    String localiser();

    /**
     * Représente le propriétaire sous forme d'une chaine de caractère.
     * @return Le propriétaire sous forme d'une chaine de caractère.
     */
    String toString();
}
