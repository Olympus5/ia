import pandas as pd
from matplotlib import pyplot as plt
from sklearn.model_selection import train_test_split
import random # to generate random numbers
from sklearn.model_selection import KFold
from sklearn.metrics import confusion_matrix
from sklearn.neural_network import MLPClassifier

data = pd.read_csv("full_dataset.csv")

features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

pred_col = ["result"]

x_col="my_health"
y_col="opponent_health"

mlp = MLPClassifier(hidden_layer_sizes=(10,10, 10), max_iter=1000)

for i in range(10,0,-1):
     print('turns before end: ', i)

     turn_df = data[data["turns_to_end"]==i]
     X = turn_df[features_cols]
     Y = turn_df[pred_col]

     X_train,X_test,Y_train,Y_test=train_test_split(X,Y,test_size=0.3,random_state=random.seed())
     mlp.fit(X_train, Y_train.values.ravel())
     print("Train score: ", mlp.score(X_train, Y_train))
     print("Test score: ", mlp.score(X_test, Y_test))
