import fr.cnam.Banque.Banque;
import fr.cnam.Compte.Compte;
import fr.cnam.Compte.CompteEpargne;
import fr.cnam.Personne.Personne;

/**
 * Classe de test unitaire de la classe Compte
 * @author <a href="mailto:time_has_come_260@hotmail.com">Jonathan de Flaugergues</a>
 * @version 6.0 ${11/05/2015}
 */
public class BanqueTest extends junit.framework.TestCase{

    private Banque banque;
    private Personne johnDoe;
    private Personne janeRoe;

    public BanqueTest(String testMethodName){
        super(testMethodName);
    }

    public void setUp() throws Exception{
        super.setUp();
        banque = new Banque("Banque GR");
        johnDoe = new Personne("DOE","John","john@doe.com","28/04/1985");
        janeRoe = new Personne("ROE","Jane","jane@roe.com","01/01/1984");
    }

    public void tearDown() throws Exception{
        super.tearDown();
        banque = null;
        johnDoe = null;
        janeRoe = null;
    }

    public void testBanque(){
        assertNotNull("L'instance est créée", banque);
        assertEquals("Nom de la banque","Banque GR",banque.getNom());
    }

    public void testCreateCompte(){
        // Compte bancaire
        Compte compte = banque.createCompte(johnDoe,100);
        assertEquals("Numéro de compte","0001",compte.getNumero());
        assertEquals("Propriétaire du compte", johnDoe, compte.getProprietaire());
        assertEquals("Montant découvert",100,compte.getMontantDecouvert());
        assertEquals("Classe Compte",Compte.class,compte.getClass());

        // Compte Epargne
        Compte compteEpargne = banque.createCompte(janeRoe,200,1.5f);
        assertEquals("Numéro de compte","0002",compteEpargne.getNumero());
        assertEquals("Propriétaire du compte", janeRoe, compteEpargne.getProprietaire());
        assertEquals("Montant découvert",200,compteEpargne.getMontantDecouvert());
        assertEquals("Taux intérêt", 1.5f, ((CompteEpargne) compteEpargne).getTauxInterets());
        assertEquals("Classe CompteEpargne",CompteEpargne.class,compteEpargne.getClass());
    }

    public void testSearchCompte(){
        Compte compte = banque.createCompte(johnDoe,100);
        Compte compteEpargne = banque.createCompte(janeRoe,200,1.5f);

        Compte compteFound = banque.searchCompte("0001");
        Compte compteEpargneFound = banque.searchCompte("0002");
        Compte compteNotFound = banque.searchCompte("0003");

        assertEquals("Compte trouvé",compte,compteFound);
        assertEquals("Classe Compte trouvé",Compte.class,compteFound.getClass());
        assertEquals("Compte Epargne trouvé",compteEpargne,compteEpargneFound);
        assertEquals("Classe Compte Epargne trouvé",CompteEpargne.class,compteEpargneFound.getClass());
        assertNull("Compte non trouvé", compteNotFound);
    }

    public void testUpdateInterest(){
        Compte compte = banque.createCompte(johnDoe,100);
        Compte compteEpargne = banque.createCompte(janeRoe,200,1.5f);

        banque.updateInterest();
    }

    public void testDeleteCompte(){
        Compte compte = banque.createCompte(johnDoe,100);
        Compte compteEpargne = banque.createCompte(janeRoe,200,1.5f);
        boolean compteDeleted = banque.deleteCompte("0003");
        boolean compteDeleted2 = banque.deleteCompte("0001");

        assertFalse("Compte inexistant",compteDeleted);
        assertTrue("Compte existant", compteDeleted2);

        Compte compteSearched = banque.searchCompte("0001");
        Compte compteBancaireSearched = banque.searchCompte("0002");
        assertNull("Compte introuvable",compteSearched);
        assertEquals("Compte Bancaire", compteEpargne, compteBancaireSearched);

        boolean compteBancaireDeleted = banque.deleteCompte("0002");
        assertTrue("Compte existant", compteBancaireDeleted);
        compteBancaireSearched = banque.searchCompte("0002");
        assertNull("Compte introuvable", compteSearched);

    }
}
