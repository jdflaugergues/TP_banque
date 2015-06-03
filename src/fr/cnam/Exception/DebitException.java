package fr.cnam.Exception;

/**
 * Classe DebitException représentant une exception lorsque le débit sur
 * un compte n'est pas autorisé.
 *
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 9.0 ${02/06/2015}
 */
public class DebitException extends Exception {

    public DebitException(String message){
        super(message);
    }
}
