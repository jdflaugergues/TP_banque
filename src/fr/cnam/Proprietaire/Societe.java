package fr.cnam.Proprietaire;

/**
 * Classe soci�t� poss�dant une adresse, impl�mentant l'interface ProprietaireI
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${19/05/2015}
 */
public class Societe implements ProprietaireI{

    private String adresse;
    private String nom;

    /**
     * Constructeur par d�faut
     */
    public Societe(){}

    /**
     * Constructeur par initialisation du nom de la soci�t� et de son adresse
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
     * Authentifie la soci�t� � partir de son nom.
     * @return Le nom de la soci�t�
     */
    public String getIdentifiant(){
        return this.getNom();
    }

    /**
     * Localise la soci�t� � partir de son adresse.
     * @return L'adresse de la soci�t�.
     */
    public String localiser(){
        return this.getAdresse();
    }

    /**
     * Repr�sente la soci�t� sous forme d'une chaine de caract�re.
     * @return La soci�t� sous forme d'une chaine de caract�re.
     */
    public String toString(){

        return  "Nom : " + this.getNom() + "\n" +
                "Pr�nom : " + this.getAdresse();
    }


}
