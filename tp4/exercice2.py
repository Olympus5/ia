import pandas as pd
from matplotlib import pyplot as plt
from sklearn import neighbors

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

nb_neighb = 25
clf = neighbors.KNeighborsClassifier(nb_neighb)
clf.fit(X, Y.values.ravel())
Z = clf.predict(X)

from sklearn.model_selection import train_test_split
import random # to generate random numbers

X_train,X_test,Y_train,Y_test=train_test_split(X,Y,test_size=0.3,random_state=random.seed())
#help(train_test_split)
print(len(X_train))

print(len(X_test))
print(len(X_train[Y_train["result"]==0]))

print(len(X_train[Y_train["result"]==1]))

clf=clf.fit(X_train, Y_train.values.ravel())

Y_pred =clf.predict(X_test)
from sklearn.metrics import confusion_matrix

cm = confusion_matrix(Y_test, Y_pred)

print(cm)
print('---------------------------------------------------')
from sklearn.model_selection import KFold
help(KFold)
kf=KFold(n_splits=10,shuffle=True)
for learn,test in kf.split(X):
        print("app : ",learn," test ",test)
