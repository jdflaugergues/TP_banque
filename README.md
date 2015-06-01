## TP 12 - Generics et collections

La classe Compte contient une liste d'opération stockée dans une ArrayList. Les opérations sont ajoutées à cette liste dans les méthodes crediter et debiter via la méthode add de la ArrayList. La méthode getHistorique prend une String en paramètre ayant pour valeur "operation" ou "date" représentant le critère de tri des opérations.
Les comptes possèdent désormais un nombre illimité d'opération.
=>Les tests de ces évolutions sont présents dans la méthode testGetHistorique de la classe CompteTest.

La classe Banque contient une liste de Compte stockée dans une HashMap avec pour clé le numéro du compte. Les méthodes createCompte de la classe Banque utilise la méthode put de la classe HashMap pour rajouter un compte à la banque. La méthode de recherche d'un compte (searchCompte) effectue la recherche sur la HashMap à partir de la méthode get avec le numéro de compte en paramètre. Les méthodes updateInterest et toString parcours la liste des comptes avec une boucle for each.
La méthode deleteCompte supprime un Compte à partir de la méthode remove de la classe HashMap qui prend en paramètre le numéro du compte à Supprimer. 
=> Les tests concernant ces évolutions n'ont pas changés, et se trouvent toujours dans la classe BanqueTest.

###Diagramme de classe de l'application : 

![alt tag](https://raw.githubusercontent.com/jdflaugergues/TP_banque/master/img/DiagrammeClasse.jpg)
