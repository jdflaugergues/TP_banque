package fr.cnam.Journal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe journal qui permet de stocker les différentes opérations
 * refusées sur l'ensemble des comptes.
 *
 * @author Jonathan de Flaugergues
 * @version 2.0
 */
public class Journal {

    private static Journal _instance = null;
    private String journal;

    /**
     * Constructeur par défaut privé.
     */
    private Journal(){
        this.journal = "";
    }

    /**
     * Récupère l'instance unique du journal.
     * @return L'instance unique
     */
    public static Journal getInstance(){

        if (_instance == null)
            _instance = new Journal();
        return _instance;
    }

    /**
     * Ajoute une opérations refusée dans le journal.
     * @param operationRefused
     */
    public void add(String operationRefused){
        if (operationRefused != null) {

            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy - hh:mm:ss");

            this.journal += ft.format(dNow) + "  : Compte n°" +  operationRefused + "\n";
        }
    }

    /**
     * Récupère le journal d'opérations refusées.
     * @returnLe journal
     */
    public String getJournal(){
        return this.journal;
    }

}
