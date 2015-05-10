import fr.cnam.Compte.Compte;
import fr.cnam.Personne.Personne;

/**
 * Classe de test d'un compte en banque
 * @author Jonathan de Flaugergues
 * @version 3.0
 */
public class MainTest extends junit.framework.TestCase{

    private Personne johnDoe;
    private Personne johnnieDoe;
    private Personne janeRoe;
    private Personne janieDoe;

    private Compte compteDoe;

    public MainTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        johnnieDoe = new Personne("DOE","Johnnie","johnnie@doe.com","28/04/1986");
        janeRoe = new Personne("ROE","Jane","jane@roe.com","01/01/1984");
        janieDoe = new Personne("DOE","Janie","janie@doe.com","01/01/1983");
        compteDoe = new Compte(johnDoe,"0001",1000);
    }

    public void tearDown() throws Exception{
        super.tearDown();
        johnDoe = null;
        johnnieDoe = null;
        janeRoe = null;
        janieDoe = null;
        compteDoe = null;
    }

    /**
     * Test Unitaire de la classe Compte
     */
    public void testCompte(){
        assertEquals("Compte de John Doe",johnDoe,compteDoe.getProprietaire());
    }

    /**
     * Test Unitaire de la classe Personne
     */
    public void testPersonne(){
        assertNotNull("L'instance est créée", johnDoe);
        assertNotNull("L'instance est créée", johnnieDoe);
        assertNotNull("L'instance est créée", janeRoe);
        assertNotNull("L'instance est créée", janieDoe);
    }

    /**
     * Test Unitaire de la méthode marier de la classe Personne
     */
    public void testMarier(){

        try {
            johnDoe.marier(null);
            fail("L'argument personne doit être obligatoire");
        }catch(Exception e){}

        try {
            johnDoe.marier(janeRoe);
            johnDoe.marier(johnDoe);
            fail("Il ne doit pas être possible de se marier avec soi-même");
        }catch(Exception e){}

        try {
            johnDoe.marier(janieDoe);
            fail("Il ne doit pas être possible de se marier avec une 2ème personne");
        }catch(Exception e){}

        try {
            janieDoe.marier(johnnieDoe);
            johnDoe.marier(janieDoe);
            fail("Il ne doit pas être possible de se marier avec une personne déja mariée.");
        }catch(Exception e){}

        assertEquals("John Doe est bien marié avec Jane Roe", janeRoe, johnDoe.getMariOuFemme());
        assertEquals("Jane Roe est bien mariée avec John Doe", johnDoe, janeRoe.getMariOuFemme());
    }

    /**
     * Test Unitaire de la méthode divorcer de la classe Personne
     */
    public void testDivorcer(){
        try {
            johnDoe.divorcer();
            assertNull("John Doe est célibataire", johnDoe.getMariOuFemme());
            assertNull("Jane Roe est célibataire", janeRoe.getMariOuFemme());

            johnDoe.divorcer();
            fail("John Doe ne doit pas pouvoir divorcer si il n'est pas marié.");
        }catch(Exception e){}
    }

    /**
     * Test unitaire de la méthode getAge de la classe Personne
     */
    public void testGetAge(){

        assertEquals("John Doe a 30 ans",30,johnDoe.getAge());
        assertEquals("Johnnie Doe a 29 ans",29,johnnieDoe.getAge());
        assertEquals("Jane ROE a 31 ans",31,janeRoe.getAge());
        assertEquals("Janie DOE a 32 ans",32,janieDoe.getAge());
    }


}
