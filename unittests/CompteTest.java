
import fr.cnam.Compte.Compte;
import fr.cnam.Exception.DebitException;
import fr.cnam.Proprietaire.Personne;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe de test d'un compte en banque
 * @author Jonathan de Flaugergues
 * @version 8.0
 */
public class CompteTest extends junit.framework.TestCase{

    private Compte compteA;
    private Compte compteB;
    private Personne johnDoe;
    private Personne janeRoe;
    public CompteTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        janeRoe = new Personne("ROE","Jane","jane@roe.com","01/01/1984");
        compteA = new Compte(johnDoe,"001",100);
        compteB = new Compte(janeRoe,"002",200);
    }

    public void tearDown() throws Exception{
        super.tearDown();
        compteA = null;
        compteB = null;
    }

    public void testCompte(){
        assertNotNull("L'instance est créée",compteA);
        assertNotNull("L'instance est créée",compteB);
    }

    public void testgetNumero(){
        assertEquals("Test du nom de compte", "001", compteA.getNumero());
        assertEquals("Test du nom de compte", "002", compteB.getNumero());
    }

    public void testgetSolde(){
        assertEquals("Test du solde",1000f,compteA.getSolde());
        assertEquals("Test du solde",1000f,compteB.getSolde());
    }

    public void testgetMontantDecouvert(){
        assertEquals("Test du découvert",100,compteA.getMontantDecouvert());
        assertEquals("Test du découvert",200,compteB.getMontantDecouvert());
    }

    public void testDebiter(){

        try {
            compteA.debiter(-100);
            fail("Débit montant négatif");
        } catch (DebitException e) {}

        try {
            compteA.debiter(1000);
        } catch (DebitException e) {
            fail("Débit autorisé");
        }

        try {
            compteA.debiter(100);
            fail("Débit compte non approvisionné");
        } catch (DebitException e) {}

        compteA.crediter(500);

        try {
            compteA.debiter(700);
            fail("Découvert dépassé");
        } catch (DebitException e) {}

        try {
            compteA.debiter(200);
        } catch (DebitException e) {
            fail("Débit autorisé");
        }

        assertEquals("Solde après débit", 300f, compteA.getSolde());
    }

    public void testCrediter()throws Exception{
        assertFalse("Crédit montant négatif",compteA.crediter(-400f));
        assertFalse("Crédit montant nulle",compteA.crediter(0f));
        assertTrue("Crédit montant positif",compteA.crediter(100f));
        assertEquals("Solde compteA après crédit",1100f,compteA.getSolde());
    }

    public void testVirement()throws Exception{
        try {
            compteA.debiter(1000);
        } catch (DebitException e) {
            fail("Débit autorisé");
        }

        assertFalse("Virement avec solde nulle",compteA.virement(100f,compteB));
        assertFalse("Virement avec montant négatif",compteA.virement(-100f,compteB));
        compteA.crediter(500);
        assertTrue("Virement autorisé",compteA.virement(200f,compteB));
        assertEquals("Solde Compte A après Virement",300f,compteA.getSolde());
        assertEquals("Solde Compte B après Virement",1200f,compteB.getSolde());
    }

    public void testGetHistorique() throws InterruptedException,DebitException {
        compteA.crediter(4000);
        Thread.sleep(100);  // Sleep pour ne pas avoir une date identique entre 2 opérations
        compteA.debiter(1000);
        Thread.sleep(100);
        compteA.crediter(1500);

        Pattern pattern = Pattern.compile("Montant : \\d+\\.\\d+");

        Matcher matcher = pattern.matcher(compteA.getHistorique("operation"));
        matcher.find();
        assertEquals("Tri par montant croissant", "Montant : 1000.0", matcher.group());

        matcher = pattern.matcher(compteA.getHistorique("date"));
        matcher.find();
        assertEquals("Tri par date croissante", "Montant : 4000.0", matcher.group());
    }
}
