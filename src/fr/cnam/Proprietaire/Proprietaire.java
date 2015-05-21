package fr.cnam.Proprietaire;

/**
 * Interface d'un Propri�taire.
 *
 * @author Jonathan de Flaugergues
 * @version 7.0 ${21/05/2015}
 */
public interface Proprietaire {

    /**
     * Permet d'authentifier le propri�taire.
     * @return L'authentification du propri�taire
     */
    String sauthentifier();

    /**
     * Permet de localiser le propri�taire pour le contacter.
     * @return La localisation du propri�taire.
     */
    String localiser();

    /**
     * Repr�sente le propri�taire sous forme d'une chaine de caract�re.
     * @return Le propri�taire sous forme d'une chaine de caract�re.
     */
    String toString();
}
