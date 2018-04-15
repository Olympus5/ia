import pandas as pd
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split
import random # to generate random numbers
from sklearn.model_selection import KFold
from sklearn.metrics import confusion_matrix
from sklearn import tree


data = pd.read_csv("full_dataset.csv")

turns_before_end = 4   # 4 tours avant la fin
turn_df = data[data["turns_to_end"]==turns_before_end]

features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

pred_col = ["result"]

X = turn_df[features_cols]
Y = turn_df[pred_col]

x_col="my_health"
y_col="opponent_health"

file_name = 'tree{criterion}.dot'

for i in ['gini', 'entropy']:
     clf = tree.DecisionTreeClassifier(criterion=i)
     clf.fit(X,Y)
     file=file_name.format(criterion=i)
     tree.export_graphviz(clf,out_file=file)
