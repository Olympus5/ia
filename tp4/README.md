# Rapport TP4

Erwan IQUEL - Antoine LEVAL

---

## Avant propos

**Ce TP a été fait sous python3.6 avec Anaconda3**

---

## Exercice 1:

1. Affiche la taille du dataframe (*36148*)
2. Affiche le nom de la 1ère colonne (*game_id*)
3. Donne les stats d'une partie.
4. Donne les metadonnées des tours allant de 0 à 4 du dataframe
5. Donne la vie des 36148 tours du dataframe
6. Donne la vie et le nombre de carte dans la main des tours allant de 0 à 4 du dataframe
7. Renvoie l'ensemble des tours qui sont à "4 tours de la fin"
8. Créé un nuage de point, où chaque point représente "notre" vie et la vie de l'adversaire au "4 tours de la fin"
9. Affiche le nuage de point créé précédemment
10. Ajoute pour label de l'axe x du nuage de point "my_health"
11. Affiche les informations des tours où l'adversaire est à "4 tours de la fin" dans le dataframe
12. Affiche "notre" vie et la vie de l'adversaire quand l'adversaire est à "4 tours de la fin" (sous forme de nuage de points)
13. Nous avons ce message (erreur ???): "No handles with labels found to put in legend."
14. random est là pour minimiser tout biais qu'il y aurait dans les données

## Exercice 2:

1. Applique l'algorithme KNN à notre jeu de donnée ("4 tours de la fin"), pour l'entrainer
2. Probabilités de victoire adverse sur les 5 premières lignes suite à l'entrainement (en classifiant les données)
3. Evalue la qualité de notre modèle
4. Notre évaluation est pessimiste tout simplement car notre jeu de données est *beaucoup* trop simple. Il se passe le phénomène d'*underfitting* (et on a donc de mauvaises performances)
5. Nombre de partie gagnante pour l'adversaire (durant l’entraînement de notre KNN)
6. Importe le module de matrice de confusion
7. En Machine Learning, une matrice de confusion va surtout être utilisé pour jugé de la qualité de notre classificateur. Elle va permettre de compter le nombre d'éléments prédis vraiment vrais/faux par rapport aux résultats.
8. Les données ne sont pas triées. Pour illustrer le problème, gardons notre jeu hearthstone. Nous avons donc *n* Parties, allant de *0* à *n*, chacunes durent 10 tours (chaque partie est différente de l'une l'autre). Si on applique notre algorithme sur un lot de 10 données (*non triées*) alors nous nous concentrons que sur le cas de la partie *i* et c'est en cela que réside le problème, une "mauvaise" répartitions des "cas".
9. Premièrement l’hyper-paramètre diffère grandement (*un écart de plus 100 voisins*). Et deuxièmement, notre classificateur est moins performant dans le deuxième cas.

## Exercice 3:

1. Crée un arbre de décision
2. Applique l'arbre de décision sur le jeu de données
3. Prédiction sur les lignes allant de 0 à 10 de notre dataset (avec arbre de décision)
4. Évalue la qualité de notre modèle (arbre de décision)
5. Exporte l'arbre pour être lu avec le logiciel graphviz
6. Le score du set d'*entrainement* est de *100%* pendant que celui du *test* est de environ 75%. Il y a eu sur-apprentissage (overfitting)
7. Même problème que précédemment, sur-apprentissage

## Exercice 4:

1. `clf = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(5, 2),random_state=1)` Crée un MLP avec les caractéristiques suivantes:
    * solver: Solver de type quasi-newton
    * alpha: Précision des calcules, ici 10^-5
    * hidden_layer_sizes: il y a 2 couches cachées de neurones. Une de 5 neurones et une de 2 neurones.
    * random_state: Ici, la valeur est fixé à 1. Ce qui va nous garantir d'avoir la même séquence de nombre aléatoire à chaque fois qu'on lance notre programme. Bien evidemment la séquence pour 1 et différente pour la séquence de 2.
2. `clf.fit(X, Y)` Fait travailler notre réseau de neurones
3. Nous avons donc joué avec le nombre de neurones pas couche, le nombre de couches et le taux d'apprentissage. Nous constatons donc:
    * Plus le nombre de noyaux par couches est petit moins le score est bon.
    * Ordonner les neurones comme-ci, *2,5,10*, est moins efficace que, *10,5,2*
    * Plus il y a de couche, meilleur est le résultat. (nous avons essayé avec des valeurs inférieur à 5 sinon on a déjà de très bon résultat avec 1 seule couche)
    * En changeant le taux d'apprentissage (learning rate), nous ne voyons pas de grande différence entre *constant*, *invscaling* et *adaptive* avec ce dataset

## Exercice 5:
2. Nous choisirons le **KNN**. Pour *l'arbre de décision*, nous tombons vite dans un problème d'*overfitting*, alors que le réseau de neurones les scores varie trop en plus d'être "moins bon" que le *KNN*
3. Plus le nombre de tour avant la fin est petit plus la précision de notre modèle est bon

---

## Erreurs

**Nous avons rencontré un problème avec la fonction `scatter` de matplotlib dans le code que vous fournissez** et ne sachant pas si c'était volontaire de votre part nous nous permettons de vous le signaler:
```python
plt.scatter(X[x_col], X[y_col],c=Y)
```
```
Traceback (most recent call last):
  File "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/colors.py",
  line 166, in to_rgba
    rgba = _colors_full_map.cache[c, alpha]
KeyError: ('result', None)

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File
  "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/axes/_axes.py",
  line 4274, in scatter
    colors = mcolors.to_rgba_array(c)
  File "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/colors.py",
  line 267, in to_rgba_array
    result[i] = to_rgba(cc, alpha)
  File "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/colors.py",
  line 168, in to_rgba
    rgba = _to_rgba_no_colorcycle(c, alpha)
  File "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/colors.py",
  line 212, in _to_rgba_no_colorcycle
    raise ValueError("Invalid RGBA argument: {!r}".format(orig_c))
ValueError: Invalid RGBA argument: 'result'

During handling of the above exception, another exception occurred:

Traceback (most recent call last):
  File "errors.py", line 41, in <module>
    plt.scatter(X[x_col], X[y_col],c=Y) # all the functions defined in a given
    library should be prefixed by the name of the library
  File "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/pyplot.py",
  line 3470, in scatter
    edgecolors=edgecolors, data=data, **kwargs)
  File
  "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/__init__.py",
  line 1855, in inner
    return func(ax, *args, **kwargs)
  File
  "/usr/local/anaconda3/lib/python3.6/site-packages/matplotlib/axes/_axes.py",
  line 4279, in scatter
    .format(c.shape, x.size, y.size))
ValueError: c of shape (2049, 1) not acceptable as a color sequence for x with
size 2049, y with size 2049

```
(nous avons essayé avec python2.7, python3.5 et python3.6, le dernier était sous Anaconda3)

---

## Liste de liens qui nous on été utiles:

* https://stats.stackexchange.com/questions/180827/model-construction-when-to-shuffle-data-and-when-to-sort-it
* https://blogs.msdn.microsoft.com/big_data_france/2014/06/17/evaluer-un-modle-en-apprentissage-automatique/
* https://openclassrooms.com/courses/initiez-vous-au-machine-learning
* https://openclassrooms.com/courses/evaluez-et-ameliorez-les-performances-d-un-modele-de-machine-learning
* \+ Cours
