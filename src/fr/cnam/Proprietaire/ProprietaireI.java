package fr.cnam.Proprietaire;

/**
 * Interface d'un Propri�taire
 *
 * @author Jonathan de Flaugergues
 * @version 9.0 ${03/06/2015}
 */
public interface ProprietaireI {

    /**
     * R�cup�re l'identifiant du propri�taire.
     * @return L'identifiant du propri�taire
     */
    <T extends Object> T getIdentifiant();

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
