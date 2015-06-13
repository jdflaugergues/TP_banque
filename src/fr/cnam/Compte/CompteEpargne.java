package fr.cnam.Compte;

import fr.cnam.Exception.DebitException;
import fr.cnam.Proprietaire.ProprietaireI;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 7.0 ${20/05/2015}
 */
public class CompteEpargne extends Compte implements Serializable {

    private float tauxInterets; // Taux d'intérêt du compte épargne
    private float[] interets;   // Tableau des intérêts

    /**
     * Constructeur par défaut.
     */
    public CompteEpargne(){}

    /**
     * Constructeur par initialisation du propriétaire, du numéro de compte, du montant de découvert
     * autorisé et du taux d'intérêt.
     * @param proprietaire Propriétaire du compte.
     * @param numero Numéro de compte
     * @param montantDecouvert Montant du découver autorisé
     * @param tauxInterets Taux d'intérêt du compte epargne
     */
    public CompteEpargne(ProprietaireI proprietaire, String numero, int montantDecouvert, float tauxInterets){
        super(proprietaire,numero,montantDecouvert);

        this.setTauxInterets(tauxInterets);
        this.setInterets(new float[24]);
        this.initializeTabTauxInterets();
        this.calculerInteretQuinzaine();
    }

    /**
     * On initialise les cases du tableau à -1 pour différencier les quinzaines
     * sans intérêts des quinzaine avec un intérêt non calculé.
     */
    private void initializeTabTauxInterets(){
        for (int j=0;j<24;j++){
            this.getInterets()[j] = -1;
        }
    }

    /**
     * Calcule les intérêts de la quinzaine en cours.
     * @return
     */
    public void calculerInteretQuinzaine(){

        Calendar today = Calendar.getInstance();
        int fortnight = (today.get(Calendar.MONTH)) * 2;
        fortnight += (today.get(Calendar.DAY_OF_MONTH) < 16 ) ? 1 : 2;

        float interet = (this.getTauxInterets() * this.getSolde()) / 24;
        if (interet < 0)
            interet = 0;

        if (this.getInterets()[fortnight -1] == -1 || this.getInterets()[fortnight -1 ] > interet)
            this.getInterets()[fortnight -1] = interet;
    }

    /**
     * Surcharge de la méthode débiter de la classe mére.
     * Calcul le nouveau taux d'intérêts de la quinzaine après le débit.
     * @param montant Montant à débiter
     * @return
     */
    @Override
    public void debiter(float montant) throws DebitException {
        super.debiter(montant);
        this.calculerInteretQuinzaine();
    }

    /**
     * Surcharge de la méthode créditer de la classe mère Compte.
     * Calcul le taux d'intérêt avant crédit dans le cas ou il n'y a que
     * des opération de crédit durant la quinzaine afin de ne pas avoir -1 dans
     * le tableau des intérêts de l'année.
     * @param montant Montant à créditer.
     * @return
     */
    @Override
    public boolean crediter(float montant){
        this.calculerInteretQuinzaine();

        return super.crediter(montant);
    }
    /**
     * Représente le compte epargne sous forme d'une chaine de caractère.
     * @return Le compte épargne sous forme d'une chaine de caractère.
     */
    @Override
    public String toString(){

        String interets = "";

        for (int j=0;j<24;j++) {
            String month = new DateFormatSymbols().getMonths()[j/2];
            interets += (j%2 ==0) ? "\n" + String.format("%10s", month) + "\t : " : " | ";

            interets += (this.getInterets()[j] == -1) ? "     - " : String.format("%7s", this.getInterets()[j]);
        }

        return super.toString() + '\n' +
                "Taux d'intérêts : " + this.getTauxInterets() + '\n' +
                "Interets : " + interets ;
    }

    //region getter/setter

    public float getTauxInterets() {
        return tauxInterets;
    }

    private void setTauxInterets(float tauxInterets) {
        this.tauxInterets = tauxInterets;
    }

    public float[] getInterets() {
        return interets;
    }

    private void setInterets(float[] interets) {
        this.interets = interets;
    }

    //endregion
}
