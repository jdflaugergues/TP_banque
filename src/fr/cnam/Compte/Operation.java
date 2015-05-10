package fr.cnam.Compte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe d'une opération bancaire de type crédit ou débit possédant
 * un montant ainsi qu'une date.
 *
 * @author Jonathan de Flaugergues
 * @version 5.0
 */
public class Operation {

    private static final DateFormat DATE_FORMAT_FRANCE = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRANCE);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_FRANCE = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");

    private TypeOperation type;
    private float montant;
    private Date date;

    /**
     * Constructeur par initialisation du type de l'opération et du montant.
     * @param type Type de l'opération
     * @param montant Montant de l'opération
     */
    public Operation(TypeOperation type, float montant){
        this.setType(type);
        this.setMontant(montant);
        this.setDate(new Date());
    }

    //region getter/setter

    public TypeOperation getType() {
        return type;
    }

    private void setType(TypeOperation type) {
        this.type = type;
    }

    public float getMontant() {
        return montant;
    }

    private void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }
    //endregion

    /**
     * Représente l'opération sous forme d'une chaine de caractère.
     * @return L'opération sous forme d'une chaine de caractère.
     */
    public String toString(){
        return  "Type de l'opération : " + this.getType().toString() + "\n" +
                "Montant : " + this.getMontant() + "\n" +
                "Date : " + SIMPLE_DATE_FORMAT_FRANCE.format(this.getDate());
    }
}
