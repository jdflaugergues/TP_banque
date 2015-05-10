package fr.cnam.Compte;

/**
 * Classe d'un compte en banque permettant d'effectuer un virement sur un autre compte,
 * un crédit ou un débit.
 * @author Jonathan de Flaugergues
 * @version 1.0
 */
public class Compte {

    private String numero;
    private float solde;
    private int montantDecouvert;

    /**
     * Constructeur par initialisation du numéro de compte et du montant du découvert
     * @param numero Le numéro de compte
     * @param montantDecouvert Le montant du découvert
     */
    public Compte(String numero, int montantDecouvert){
        this.numero = numero;
        this.solde = 0;
        this.montantDecouvert = montantDecouvert;
    }

    public String getNumero() {
        return numero;
    }

    public float getSolde() {
        return solde;
    }

    private void setSolde(float solde) {
        this.solde = solde;
    }

    public int getMontantDecouvert() {
        return montantDecouvert;
    }

    private void setMontantDecouvert(int montantDecouvert) {
        if (montantDecouvert > 0)
            this.montantDecouvert = montantDecouvert;
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
                if (this.solde - montant >= this.montantDecouvert) {
                    this.solde -= montant;
                    debit = true;
                } else {
                    System.out.println("Débit impossible car cela entrenerait un découvert non autorisé.");
                }
            }else{
                System.out.println("Le compte doit être approvisionné.");
            }
        }else{
            System.out.println("Le montant à débiter doit être supérieur à 0.");
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
        }else{
            System.out.println("Le montant à créditer doit être positif.");
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
            System.out.println("Virement impossible.");
        }
        return virer;
    }

    /**
     * Représente le compte sous forme d'une chaine de caractère.
     * @return Le compte sous forme d'une chaine de caractère.
     */
    public String toString(){
        return "Numéro de compte : " + this.numero + "\n" +
                "Solde : " + this.solde + "\n" +
                "Découvert autorisé : " + this.montantDecouvert + "\n";
    }
}
