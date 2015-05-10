package fr.cnam.Personne;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Classe d'une personne étant propriétaire d'une compte et étant marié.
 * @author Jonathan de Flaugergues
 * @version 3.0
 */
public class Personne {

    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Personne mariOuFemme;

    private static DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, Locale.FRANCE);

    /**
     * Constructeur par défaut
     */
    public Personne(){}

    /**
     * Constructeur par initialisation
     * @param nom Nom de famille
     * @param prenom Prénom
     * @param email Adresse e-mail
     * @param dateNaissance Date de naissance
     */
    public Personne(String nom, String prenom, String email, String dateNaissance){
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setEmail(email);
        this.setDateNaissance(Personne.getDateFromString("dd/MM/yyyy",dateNaissance));
    }

    /**
     * Obtient une date à partir d'une chaine de caractère
     * @param format Le format de date attendu
     * @param stringDate La date sous forme d'une string
     * @return La date parsée ou null si le format est invalide.
     */
    public static Date getDateFromString(String format, String stringDate) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(stringDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * Calcule l'âge à partir de la date de naissance de la personne
     * @return L'âge de la personne
     */
    public int getAge(){

        Calendar birthDay = Calendar.getInstance();
        birthDay.setTime(this.getDateNaissance());
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR))
            age--;

        return age;
    }

    /**
     * Représente la personne sous forme d'une chaine de caractère.
     * @return La personne sous forme d'une chaine de caractère.
     */
    public String toString(){

        return  "Nom : " + this.getNom() + "\n" +
                "Prénom : " + this.getPrenom() + "\n" +
                "E-Mail : " + this.getEmail() + "\n" +
                "Date de naissance : " + Personne.df.format(this.getDateNaissance()) + "\n" +
                "Mari ou femme : " + this.getMariOuFemme();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null)
            throw new IllegalArgumentException("Argument nom must not br null");

        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null)
            throw new IllegalArgumentException("Argument prenom must not be null");
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException("Argument email must not be null");

        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        if (dateNaissance == null)
            throw new IllegalArgumentException("Argument dateNaissance must not be null");

        this.dateNaissance = dateNaissance;
    }

    public Personne getMariOuFemme() {
        return this.mariOuFemme;
    }

    public void setMariOuFemme(Personne mariOuFemme) {
        this.mariOuFemme = mariOuFemme;
    }

    /**
     * Marie la personne à une autre personne.
     * @param mariOuFemme Le mari ou la femme avec qui la personne se mari.
     */
    public void marier(Personne mariOuFemme) throws Exception {
        if (mariOuFemme == null)
            throw new IllegalArgumentException("Argument personne must not be null");

        if (this.mariOuFemme != null)
            throw new Exception("La personne ne peut pas se marier car elle est déjà marier avec " + this.getPrenom() + " "  + this.mariOuFemme.getNom());

        if (mariOuFemme.getMariOuFemme() != null)
            throw new Exception("La personne ne peut pas se marier avec quelqu'un qui est déjà marié.");

        if (this.equals(mariOuFemme))
            throw new IllegalArgumentException("La personne ne peut pas se marier avec elle-même");

        this.setMariOuFemme(mariOuFemme);
        mariOuFemme.setMariOuFemme(this);
    }

    /**
     * Divorce la personne a qui elle était mariée.
     * @throws Exception
     */
    public void divorcer() throws Exception {
        if (this.mariOuFemme != null) {
            this.mariOuFemme = null;
            mariOuFemme.mariOuFemme = null;
        } else
            throw new Exception("La personne ne peut pas divorcer car elle n'est pas mariée.");
    }
}
