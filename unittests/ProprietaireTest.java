import fr.cnam.Banque.Banque;
import fr.cnam.Compte.Compte;
import fr.cnam.Compte.CompteEpargne;
import fr.cnam.Proprietaire.Personne;
import fr.cnam.Proprietaire.Societe;

/**
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 1.0 ${Date}
 */
public class ProprietaireTest extends junit.framework.TestCase{

    private Banque banque;
    private Personne johnDoe;
    private Societe coservit;
    private Compte compteJD;
    private CompteEpargne compteCoservit;

    public ProprietaireTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        banque = new Banque("Banque GR");
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        coservit = new Societe("Coservit","13, rue des Trembles - 38100 GRENOBLE");
    }

    public void tearDown() throws Exception{
        super.tearDown();
        banque = null;
        johnDoe = null;
        coservit = null;
    }

    public void testSociete(){
        assertNotNull("L'instance est créée", coservit);
        assertEquals("Nom de la societe","Coservit",coservit.getNom());

        assertEquals("Adresse de la société","13, rue des Trembles - 38100 GRENOBLE",coservit.getAdresse());
        assertEquals("Localisation de la société","13, rue des Trembles - 38100 GRENOBLE",coservit.localiser());
    }

    public void testPersonne(){
        assertNotNull("L'instance est créée", coservit);
        assertEquals("Nom de la societe","Coservit",coservit.getNom());

        assertEquals("Adresse de la société","13, rue des Trembles - 38100 GRENOBLE",coservit.getAdresse());
        assertEquals("Localisation de la société","13, rue des Trembles - 38100 GRENOBLE",coservit.localiser());
    }

    public void testCompte(){
        compteJD = banque.createCompte(johnDoe,200);
        compteCoservit = (CompteEpargne)banque.createCompte(coservit,100,1.5f);

        assertEquals("Compte John Doe",compteJD.getProprietaire(),johnDoe);
        assertEquals("Compte Coservit",compteCoservit.getProprietaire(),coservit);
    }

    public void testGetIdentifiant(){
       assertEquals("Identifiant Personne ","John DOE",johnDoe.getIdentifiant());
       assertEquals("Identifiant Société ","Coservit",coservit.getIdentifiant());
    }

}

