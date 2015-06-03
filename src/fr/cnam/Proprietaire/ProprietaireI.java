package fr.cnam.Proprietaire;

/**
 * Interface d'un Propriétaire
 *
 * @author Jonathan de Flaugergues
 * @version 9.0 ${03/06/2015}
 */
public interface ProprietaireI {

    /**
     * Récupère l'identifiant du propriétaire.
     * @return L'identifiant du propriétaire
     */
    <T extends Object> T getIdentifiant();

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
