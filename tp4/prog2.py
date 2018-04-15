# -*- coding: utf-8 -*-
import random
import pandas as pd
from matplotlib import pyplot as plt

data = pd.read_csv("full_dataset.csv")

features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]
pred_col = ["result"]

turns_before_end = 4
turn_df = data[data["turns_to_end"] == turns_before_end]
X = turn_df[features_cols]
Y = turn_df[pred_col]

from sklearn import neighbors
from sklearn.model_selection import KFold
kf=KFold(n_splits=3 ,shuffle=False)
scores=[]
k_values = range(1,600,5)
for k in k_values:
    score=0
    clf = neighbors.KNeighborsClassifier(k)
    for learn,test in kf.split(X):
        X_train=X.iloc[learn]
        Y_train=Y.iloc[learn]
        clf.fit(X_train, Y_train.values.ravel())
        X_test=X.iloc[test]
        Y_test=Y.iloc[test]
        score = score + clf.score(X_test,Y_test)
    scores.append(score)
print(scores)
print("best k:",k_values[scores.index(max(scores))])
plt.plot(k_values, scores)
plt.show()
