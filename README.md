## TP 09 - L'héritage

Concernant le sujet du TP, celui-ci étant assez vague, j'ai mis en place les règles de gestion qui me parraissaient les plus pertinentes.



Je suis donc parti de la [définition de wikipedia concernant un compte Epargne](http://fr.wikipedia.org/wiki/Compte_d%27%C3%A9pargne).
J'ai divisé l'année en 24 quinzaine. Pour chaque quinzaine du mois (du 1 au 15 et du 16 au 28/29/30/31) le calcul de l'intérêt correspond au solde le plus bas de cette période multiplié par le taux d'intérêt et divisé par le nombre de quinzaine dans l'année (soit 24).

Le calcul se fait donc à chaque débit (et crédit dans le cas ou il n'y a pas de débit dans la quinzaine) et la valeur de l'intérêt calculée est stockée dans un tableau de taille 24 (correspondant au quinzaines de l'année) .

Ci-dessous le diagramme de classe concernant la classe CompteEpargne héritant de la classe Compte : 

![alt tag](https://raw.githubusercontent.com/jdflaugergues/TP_banque/master/img/DiagrammeClasse.jpg)
