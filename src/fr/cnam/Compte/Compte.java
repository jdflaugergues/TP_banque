package fr.cnam.Compte;

import fr.cnam.Journal.Journal;
import fr.cnam.Proprietaire.*;

import java.util.*;

/**
 * Classe d'un compte en banque permettant d'effectuer un virement sur un autre compte,
 * un crédit ou un débit.
 * @author Jonathan de Flaugergues
 * @version 8.0 ${01/06/2015}
 */
public class Compte {

    private String numero;
    private float solde;
    private int montantDecouvert;
    private Journal journal;
    private ProprietaireI proprietaire;
    private List<Operation> operations;
    private int nbOperations = 0; // Nombre d'operations dans le tableau

    /**
     * Constructeur par défaut
     */
    public Compte(){}

    /**
     * Constructeur par initialisation du numéro de compte et du montant du découvert
     * @param numero Le numéro de compte
     * @param montantDecouvert Le montant du découvert
     */
    public Compte(ProprietaireI proprietaire, String numero, int montantDecouvert){
        this.numero = numero;
        this.solde = 1000;
        this.montantDecouvert = montantDecouvert;
        this.proprietaire = proprietaire;
        this.journal = Journal.getInstance();
        this.operations = new ArrayList<>();
    }

    //region getter/setter

    /**
     * Obtient le propriétaire du compte
     * @return Le propriétaire
     */
    public ProprietaireI getProprietaire() {
        return this.proprietaire;
    }

    /**
     * Obtient le numéro de compte en banque.
     * @return Le numéro de compte
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Obtient le solde du compte en banque.
     * @return Le solde.
     */
    public float getSolde() {
        return solde;
    }

    /**
     * Met à jour le solde du compte en banque.
     * @param solde Solde
     */
    private void setSolde(float solde) {
        this.solde = solde;
    }

    /**
     * Obtient le montant du découvert autorisé.
     * @return Le montant du découvert
     */
    public int getMontantDecouvert() {
        return montantDecouvert;
    }

    /**
     * Met à jour le montant du découvert autorisé
     * @param montantDecouvert Le montant du découvert autorisé
     */
    private void setMontantDecouvert(int montantDecouvert) {
        if (montantDecouvert > 0)
            this.montantDecouvert = montantDecouvert;
    }

    /**
     * Obtient la liste des opération
     * @return La liste des opérations du compte
     */
    public List<Operation> getOperations() {
        return this.operations;
    }

    //endregion

    /**
     * Récupère les dernières opérations sous la forme d'une chaine
     * @return Les dernières opérations
     */
    public String getHistorique(String filter){

        switch(filter){
            case "operation" :
                Collections.sort(this.getOperations(), (Operation o1, Operation o2) -> {
                            if (o1 == null && o2 == null) {
                                return 0;
                            }
                            if (o1 == null) {
                                return 1;
                            }
                            if (o2 == null) {
                                return -1;
                            }
                    return Float.compare(o1.getMontant(),o2.getMontant());}
                );
                break;

            case "date" :
            default:
                Collections.sort(this.getOperations(), (Operation o1, Operation o2) -> {
                        if (o1 == null && o2 == null) {
                            return 0;
                        }
                        if (o1 == null) {
                            return 1;
                        }
                        if (o2 == null) {
                            return -1;
                        }
                        return o1.getDate().compareTo(o2.getDate());
                });
        }

        String historique = "";

        for (Operation operation : this.getOperations()) {
            if (operation != null)
                historique += operation + "\n";
        }
        return historique;
    }

    /**
     * Débite le compte d'un montant.
     * @param montant Montant à débiter
     * @return Vrai si le compte est débité; faux sinon.
     */
    public boolean debiter(float montant){
        boolean debit = false;

        if (montant > 0) {
            if (this.solde > 0) {
                if (this.solde - montant >= -this.montantDecouvert) {
                    this.solde -= montant;
                    debit = true;
                    this.operations.add(new Operation(TypeOperation.DEBIT, montant));
                } else {
                    journal.add( this.getNumero() + " : Débit impossible car cela entrenerait un découvert non autorisé.");
                }
            }else{
                journal.add( this.getNumero() + " : Le compte doit être approvisionné.");
            }
        }else{
            journal.add( this.getNumero() + " : Le montant à débiter doit être supérieur à 0.");
        }
        return debit;
    }

    /**
     * Créditer le compte d'un montant.
     * @param montant Montant à créditer.
     * @return Vrai si le crédit est effectué; faux sinon.
     */
    public boolean crediter(float montant){
        boolean credit = false;

        if (montant > 0) {
            this.solde += montant;
            credit = true;
            this.operations.add(new Operation(TypeOperation.CREDIT, montant));
        }else{
            journal.add(this.getNumero() + " : Le montant à créditer doit être positif.");
        }
        return credit;
    }

    /**
     * Effectue le virement d'un montant du compte vers un autre compte.
     * @param montant Le montant du virement
     * @param compte Le compte sur lequel effectuer le virement.
     * @return Vrai si le virement est effectué; faux sinon.
     */
    public boolean virement(float montant, Compte compte){

        boolean virer = false;

        if (this.debiter(montant)){
            virer = compte.crediter(montant);

        }else{
            journal.add(this.getNumero() + " : Virement impossible.");
        }
        return virer;
    }

    /**
     * Représente le compte sous forme d'une chaine de caractère.
     * @return Le compte sous forme d'une chaine de caractère.
     */
    public String toString(){
        return "Numéro de compte : " + this.getNumero() + "\n" +
                "Solde : " + this.getSolde() + "\n" +
                "Découvert autorisé : " + this.getMontantDecouvert() + "\n" +
                "Propriétaire : " + this.proprietaire.toString();
    }
}
