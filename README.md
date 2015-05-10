## TP 06 - Package

1. Réaliser un package qui contiendra la classe Compte. Utiliser la classe Compte depuis votre application (classe qui contient le main devra être dans un autre package). 

2. Réaliser une classe « IhmTextCompte » (dans un autre package)  qui contient des méthodes pour la gestion d’une interface utilisateur en mode texte. Réaliser une méthode « lireSomme » qui permette de lire une somme au clavier. Utiliser cette méthode dans votre application pour récupérer les sommes à créditer et débiter sur votre compte.

3. Nous souhaitons pouvoir afficher les opérations refusées (Debit impossible) sur un Compte dans la console en cas de litige. Réaliser une classe « Journal» qui permet de stocker les différentes opérations refusées sur l’ensemble des comptes. Le stockage des messages se fera dans une chaine par concaténation (on n’a pas encore vu l’utilisation des collections). Nous voulons garantir que notre programme va utiliser une seule et même instance de la classe Journal (pattern singleton).

4. Concevoir un fichier .jar qui va contenir votre application.   

5. Vérifier le contenu du fichier .jar et notamment si le Main-class du fichier manifest.mf est correct.  

6. Lancer votre application en ligne de commande. 

7. Créer une documentation pour votre application (utilisation de la javadoc).  
