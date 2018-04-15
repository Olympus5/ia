import pandas as pd #charge le module pandas et le renomme pd
data = pd.read_csv("full_dataset.csv")

print(len(data))

print(data.columns[0])

print(data.columns[2])

print(data.columns[-1])
print(data.shape)
print(data.iloc[0])

print(data[0:5])

print(data[-5:-1])

print(data[0:10:3])

print(data["my_health"])

print(data[["my_health","my_hand"]][0:5])

turns_before_end = 4   # 4 tours avant la fin

turn_df = data[data["turns_to_end"]==turns_before_end]
print(len(turn_df))

features_cols = ["my_health", "opponent_health", "my_hand", "opponent_hand", "my_board_nb_creatures",
     "my_board_total_health", "my_board_total_attack", "opponent_board_nb_creatures",
     "opponent_board_total_health", "opponent_board_total_attack", "me_playing"]

pred_col = ["result"]

X = turn_df[features_cols]
Y = turn_df[pred_col]

from matplotlib import pyplot as plt # replace the name "pyplot" by "plt"
x_col="my_health"
y_col="opponent_health"
plt.scatter(X[x_col], X[y_col],c=Y) # all the functions defined in a given library should be prefixed by the name of the library
plt.show()

help(plt.scatter)
plt.xlabel(x_col)

plt.ylabel(y_col)
plt.title("Hearthstone data: health")

plt.scatter(X[x_col], X[y_col],c=Y)

plt.show()

print(Y["result"]==0)
print(X[Y["result"]==0])
plt.scatter(X[Y["result"]==0][x_col], X[Y["result"]==0][y_col], color="red",label="lose")
plt.scatter(X[Y["result"]==1][x_col], X[Y["result"]==1][y_col], color="green",label="win")
plt.legend()

plt.show()
