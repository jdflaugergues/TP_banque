package fr.cnam.Exception;

/**
 * Classe DebitException repr�sentant une exception lorsque le d�bit sur
 * un compte n'est pas autoris�.
 *
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 9.0 ${02/06/2015}
 */
public class DebitException extends Exception {

    public DebitException(String message){
        super(message);
    }
}
