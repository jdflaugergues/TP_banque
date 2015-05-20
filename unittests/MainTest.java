import fr.cnam.Compte.CompteEpargne;
import fr.cnam.Proprietaire.Personne;

import java.util.Calendar;

/**
 * Classe de test d'un compte en banque et d'une Opération
 * @author Jonathan de Flaugergues
 * @version 5.0
 */
public class MainTest extends junit.framework.TestCase{

    private Personne johnDoe;
    private CompteEpargne compteDoe;

    public MainTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        compteDoe = new CompteEpargne(johnDoe,"0001",1000,1.5f/100);
    }

    public void tearDown() throws Exception{
        super.tearDown();
        johnDoe = null;
        compteDoe = null;
    }

    /**
     * Test Unitaire de la classe CompteEpargne
     */
    public void testCompteEpargne(){
        assertEquals("Taux initial du compte epargne",0.015f,compteDoe.getTauxInterets());

        assertEquals("Interet de la quinzaine courante",0.625f,compteDoe.getInterets()[getIndexFortNight()]);
    }

    /**
     * Test Unitaire du calcul des interets d'une quinzaine.
     */
    public void testInteret(){

        compteDoe.crediter(500);
        assertEquals("Les intérêts de la quinzaine courante n'ont pas augmenté", 0.625f, compteDoe.getInterets()[getIndexFortNight()]);

        compteDoe.debiter(1000);
        assertEquals("Les intérêts de la quinzaine courante ont diminué", 0.3125f, compteDoe.getInterets()[getIndexFortNight()]);

        compteDoe.debiter(10000);
        assertEquals("Si le débit n'est pas autorisé, le nouveau taux d'intérêt ne doit pas être calculé", 0.3125f, compteDoe.getInterets()[getIndexFortNight()]);

        compteDoe.debiter(600);
        assertEquals("Les intérêts d'un solde négatif doivent être null",0f,compteDoe.getInterets()[getIndexFortNight()]);

        System.out.println(compteDoe);
    }

    /**
     * Récupère l'index dans le tableau d'intérêt correspondant à la quinzaine en cours.
     * @return L'index de la quinzaine en cours
     */
    private int getIndexFortNight(){
        Calendar today = Calendar.getInstance();
        int fortnight = (today.get(Calendar.MONTH)) * 2;
        fortnight += (today.get(Calendar.DAY_OF_MONTH) < 16 ) ? 1 : 2;

        return fortnight-1;
    }
}
