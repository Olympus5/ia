# TP2 Intelligence Artificiel

## Min-Max et Alpha-Beta

### Erwan IQUEL - Antoine LEVAL

------------

### Différence entre Min-Max et Alpha-Beta

Tout d'abord, **Alpha-Beta** est une amélioration de l'algorithme **Min-Max**. La seule différence entre les deux, est la notion *d'élagage/coupure* qu'on retrouve dans Alpha-Beta. En effet, dans Min-Max on itère sur l'ensemble des noeuds jusqu'à une profondeur *pmax* ou jusqu'à tomber sur un *coup gagnant*, tandis que Alpha-Beta sera *"plus Intelligent"*, il ne se concentra que sur les noeuds dits intéressant, c'est-à-dire:

> l'élagage αβ évite d'évaluer des nœuds dont on est sûr que leur qualité sera inférieure à un nœud déjà évalué, il permet donc d'optimiser grandement l'algorithme minimax sans en modifier le résultat ([https://fr.wikipedia.org/wiki/%C3%89lagage_alpha-b%C3%AAta](https://fr.wikipedia.org/wiki/%C3%89lagage_alpha-b%C3%AAta))

Nous avons aussi remarqué lors du TP que **le nombre d'itérations entre Min-Max et Alpha-Beta différé grandement.**

* Min-Max
![A la fin il y a une itération de 2799](/home/erwan/Images/minmax_it.png)
* Alpha-Beta
![A la fin il y a une itération de 299](/home/erwan/Images/alphabeta_it.png)

### Heuristique du Puissance 4

Lors de ce TP, nous nous sommes rendu compte que concevoir une **heuristique** est quelques chose de **(très) difficile**. Vous pourrez voir en effet que notre évaluation n'est pas des meilleurs (fichier *FonctionEvaluationEleve.java*). Nous allons ici vous faire part du rôle de nos méthodes, ainsi que l'idée principal.

* ***private double evalCoupsGagnant(Grille grille, int joueur, int coup)***
    - Calcule si il y a un coups gagnant ou non. Renvoie 1000 ou -1000 si c'est un coup gagnant adverse
* ***private double evalAlignementHorizontal(Grille grille, int joueur, int coup)***
    - Calcule le nombre d'élément aligné horizontalement dans la colonne *coup*. Renvoie:
        - **nb_pions \* 0.1 \* 2**, si on peut encore aligner 4 pions à droite ou à gauche
        - **nb_pions \* 0.1**, si on peut aligner 4 pions soit à droite ou soit à gauche
        - **0**, si aucun alignement de 4 pions est possible
* ***private double evalAlignementVertical(Grille grille, int joueur, int coup)***
    - Calcule le nombre d'élément aligné verticalement dans la colonne *coup*. Renvoie:
        - **nb_pions \* 0.1**, si on peut aligner 4 pions
        - **0**, si aucun alignement de 4 pions est possible
* ***private double evalAlignementDiagonale1(Grille grille, int joueur, int coup)***
    - Calcule le nombre d'élément aligné en pente positive dans la colonne *coup*. Renvoie:
        - **nb_pions \* 0.1 \* 2**, si on peut encore aligner 4 pions à droite ou à gauche
        - **nb_pions \* 0.1**, si on peut aligner 4 pions soit à droite ou soit à gauche
        - **0**, si aucun alignement de 4 pions est possible
* ***private double evalAlignementDiagonale1(Grille grille, int joueur, int coup)***
    - Calcule le nombre d'élément aligné en pente négative dans la colonne *coup*. Renvoie:
        - **nb_pions \* 0.1 \* 2**, si on peut encore aligner 4 pions à droite ou à gauche
        - **nb_pions \* 0.1**, si on peut aligner 4 pions soit à droite ou soit à gauche
        - **0**, si aucun alignement de 4 pions est possible
