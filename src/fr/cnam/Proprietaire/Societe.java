package fr.cnam.Proprietaire;

/**
 * Classe société possédant une adresse, héritant de la classe abstraite Proprietaire.
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${19/05/2015}
 */
public class Societe extends Proprietaire{

    private String adresse;

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
        super(nom);
        this.setAdresse(adresse);
    }

    //region getter/setter
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
    public String sauthentifier(){
        return this.getNom();
    }

    /**
     * Localise la société à partir de son adresse.
     * @return L'adresse de la société.
     */
    public String localiser(){
        return this.getAdresse();
    }

    /**
     * Représente la société sous forme d'une chaine de caractère.
     * @return La société sous forme d'une chaine de caractère.
     */
    public String toString(){

        return  "Nom : " + this.getNom() + "\n" +
                "Prénom : " + this.getAdresse();
    }


}
