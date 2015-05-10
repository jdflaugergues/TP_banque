package fr.cnam.Compte;

/**
 * Enumérateur de type d'opération bancaire.
 *
 * @author Jonathan de Flaugergues
 * @version 4.0
 */
public enum TypeOperation {
    CREDIT ("Crédit"),
    DEBIT ("Débit");

    private String name = "";

    /**
     * Constructeur par initialisation du nom de l'opération
     * @param name Nom de l'opération
     */
    TypeOperation(String name){
        this.name = name;
    }

    /**
     * Représente le type d'opération sous forme d'une chaine de caractère.
     * @return Le type d'opération sous forme d'une chaine de caractère.
     */
    public String toString(){
        return this.name;
    }
}
