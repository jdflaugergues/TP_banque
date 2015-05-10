import fr.cnam.Compte.Compte;
import fr.cnam.Compte.Operation;
import fr.cnam.Compte.TypeOperation;
import fr.cnam.Personne.Personne;

/**
 * Classe de test d'un compte en banque et d'une Opération
 * @author Jonathan de Flaugergues
 * @version 4.0
 */
public class MainTest extends junit.framework.TestCase{

    private Personne johnDoe;
    private Operation debit;
    private Operation credit;
    private Compte compteDoe;

    public MainTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        compteDoe = new Compte(johnDoe,"0001",1000);
        debit = new Operation(TypeOperation.DEBIT,500f);
        credit = new Operation(TypeOperation.CREDIT,200f);
    }

    public void tearDown() throws Exception{
        super.tearDown();
        johnDoe = null;
        compteDoe = null;
    }

    /**
     * Test Unitaire de la classe Operation
     */
    public void testOperation(){
        assertNotNull("L'instance est créée", debit);
        assertNotNull("L'instance est créée", credit);

        assertEquals("Opération débit", TypeOperation.DEBIT, debit.getType());
        assertEquals("Opération crédit", TypeOperation.CREDIT, credit.getType());
        assertEquals("Opération débit montant", 500f, debit.getMontant());
        assertEquals("Opération crédit montant",200f,credit.getMontant());

    }

    /**
     * Test Unitaire de la méthode getHistorique de la classe Compte.
     * On fait un sleep après chaque seconde car sinon des opérations peuvent être traité en
     * même temps et du coup fausser les tests.
     */
    public void testGetHistorique() throws InterruptedException {

        compteDoe.crediter(10);Thread.sleep(100);
        compteDoe.crediter(20);Thread.sleep(100);
        compteDoe.crediter(30);Thread.sleep(100);
        compteDoe.crediter(40);Thread.sleep(100);
        compteDoe.crediter(50);Thread.sleep(100);
        compteDoe.debiter(1);Thread.sleep(100);
        compteDoe.debiter(2);Thread.sleep(100);
        compteDoe.debiter(3);Thread.sleep(100);
        compteDoe.debiter(4);Thread.sleep(100);
        compteDoe.debiter(5);Thread.sleep(100);
        compteDoe.debiter(6);Thread.sleep(100);
        compteDoe.crediter(500);Thread.sleep(100);

        // On vérifie dans la console que les 2 opérations les plus anciennes ne sont plus présente dans l'historique
        System.out.println(compteDoe.getHistorique());
    }

}
