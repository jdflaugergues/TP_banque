
import fr.cnam.Compte.Compte;
import fr.cnam.Proprietaire.Personne;

/**
 * Classe de test d'un compte en banque
 * @author Jonathan de Flaugergues
 * @version 3.0
 */
class CompteTest extends junit.framework.TestCase{

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
        assertEquals("Test du solde",0f,compteA.getSolde());
        assertEquals("Test du solde",0f,compteB.getSolde());
    }

    public void testgetMontantDecouvert(){
        assertEquals("Test du découvert",100,compteA.getMontantDecouvert());
        assertEquals("Test du découvert",200,compteB.getMontantDecouvert());
    }

    public void testDebiter()throws Exception{
        assertFalse("Débit montant négatif",compteA.debiter(-100));
        assertFalse("Débit compte non approvisionné",compteA.debiter(100));
        compteA.crediter(500);
        assertFalse("Découvert dépassé",compteA.debiter(700));
        assertTrue("Débit autorisé", compteA.debiter(200));
        assertEquals("Solde après débit",300f,compteA.getSolde());
    }

    public void testCrediter()throws Exception{
        assertFalse("Crédit montant négatif",compteA.crediter(-400f));
        assertFalse("Crédit montant négatif",compteA.crediter(0f));
        assertTrue("Crédit montant positif",compteA.crediter(100f));
        assertEquals("Solde compteA après crédit",100f,compteA.getSolde());
    }

    public void testVirement()throws Exception{
        assertFalse("Virement avec solde nulle",compteA.virement(100f,compteB));
        assertFalse("Virement avec montant négatif",compteA.virement(-100f,compteB));
        compteA.crediter(500);
        assertTrue("Virement autorisé",compteA.virement(200f,compteB));
        assertEquals("Solde Compte A après Virement",300f,compteA.getSolde());
        assertEquals("Solde Compte B après Virement",200f,compteB.getSolde());
    }
}
