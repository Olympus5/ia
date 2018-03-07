# TP2 Intelligence Artificiel

## Min-Max et Alpha-Beta

### Erwan IQUEL - Antoine LEVAL

------------

### Différence entre Min-Max et Alpha-Beta

Tout d'abord, **Alpha-Beta** est une amélioration de l'algorithme **Min-Max**. La seule différence entre les deux, est la notion *d'élagage/coupure* qu'on retrouve dans Alpha-Beta. En effet, dans Min-Max on itère sur l'ensemble des noeuds jusqu'à une profondeur *pmax* ou jusqu'à tomber sur un *coup gagnant*, tandis que Alpha-Beta sera *"plus Intelligent"*, il ne se concentra que sur les noeuds dits intéressant, c'est-à-dire:

> l'élagage αβ évite d'évaluer des nœuds dont on est sûr que leur qualité sera inférieure à un nœud déjà évalué, il permet donc d'optimiser grandement l'algorithme minimax sans en modifier le résultat ([https://fr.wikipedia.org/wiki/%C3%89lagage_alpha-b%C3%AAta](https://fr.wikipedia.org/wiki/%C3%89lagage_alpha-b%C3%AAta))

Nous avons aussi remarqué lors du TP que **le nombre d'itérations entre Min-Max et Alpha-Beta différé grandement.**

* Min-Max
* Alpha-Beta

### Heuristique du Puissance 4

Lors de ce TP, nous nous sommes rendu compte que concevoir une **heuristique** est quelques chose de **(très) difficile**. Vous pourrez voir en effet que notre évaluation n'est pas des meilleurs (fichier *FonctionEvaluationEleve.java*). Nous allons ici vous faire part du rôle de nos méthodes, ainsi que l'idée principal.

* ***public double evaluation(Grille grille, int joueur)***
* ***private double evalCoupsGagnant(Grille grille, int joueur, int[] coups)***
* ***private double evalAlignementHorizontal(Grille grille, int joueur)***
* ***private double evalAlignementVertical(Grille grille, int joueur)***
* ***private double evalAlignementDiagonale1(Grille grille, int joueur)***
* ***private double evalAlignementDiagonale1(Grille grille, int joueur)***
