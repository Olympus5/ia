import pandas as pd
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split
import random # to generate random numbers
from sklearn.model_selection import KFold
from sklearn.metrics import confusion_matrix
from sklearn.neural_network import MLPClassifier
from sklearn.datasets import load_digits

digits = load_digits()
X = digits.data
Y = digits.target

# Le nombre d'it max par défaut (200) est trop petit on l'a donc passé à 1000 (pour être large)

# mlp = MLPClassifier(hidden_layer_sizes=(4,4), max_iter=1000)
# mlp = MLPClassifier(hidden_layer_sizes=(1,1), max_iter=1000)
# mlp = MLPClassifier(hidden_layer_sizes=(10,5,2), max_iter=1000)
# mlp = MLPClassifier(hidden_layer_sizes=(2,5,10), max_iter=1000)
# mlp = MLPClassifier(hidden_layer_sizes=(4), max_iter=1000)
# mlp = MLPClassifier(hidden_layer_sizes=(4,4,4), max_iter=1000)
# mlp = MLPClassifier(max_iter=1000) # par défaut le learning_rate est à constant
# mlp = MLPClassifier(learning_rate="invscaling", max_iter=1000)
mlp = MLPClassifier(learning_rate="adaptive", max_iter=1000)

X_train,X_test,Y_train,Y_test=train_test_split(X,Y,test_size=0.3,random_state=random.seed())
mlp.fit(X_train, Y_train)
print("Train score: ", mlp.score(X_train, Y_train))
print("Test score: ", mlp.score(X_test, Y_test))
