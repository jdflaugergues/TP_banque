## TP 13 - Les Exceptions


La méthode débiter de la classe Compte génère une exception de type DebitException si le débit n'est pas autorisé. La méthode ne retourne donc plus un booléen pour indiquer si l'opération s'est bien effectuer.
La méthode virement de la classe Compte capture cette exception si elle est levée durant l'exécution de la méthode débiter, et le cas échéant, stocke l'erreur dans le journal.
La méthode de test testGetHistorique de la classe CompteTest effectue les tests de ces exceptions.

L'interface ProprietaireI possède un type générique sur sa méthode getIdentifiant ce qui permet aux classes l'implémentant de retourner n'importe quel type (non primaire) sur l'implémentation de cette fonction.
La méthode de test testGetIdentifiant de la classe ProprietaireTest teste ce type générique.

###Diagramme de classe de l'application : 

![alt tag](https://raw.githubusercontent.com/jdflaugergues/TP_banque/master/img/DiagrammeClasse.jpg)
