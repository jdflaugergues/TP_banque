## TP 11 - Classe abstraite et interface

La classe abstraite Propriétaire représente le modèle à implémenter par les classe Société et Personne. Par conséquent, ces deux dernières classes héritent de la classe Propriétaire.

La classe Compte n'est plus associé à une Personne mais à un Propriétaire.
Les méthodes createCompte de la classe Banque ne prennent plus en argument le type Personne, mais Proprietaire.


Les tests concernant l'intégration de la classe abstraite Proprietaire et de la classe fille Societe se trouvent dans la classe JUnit : ProprietaireTest.

###Diagramme de classe de l'application : 

![alt tag](https://raw.githubusercontent.com/jdflaugergues/TP_banque/master/img/DiagrammeClasse.jpg)
