from sklearn.datasets import load_digits
digits=load_digits()
digits.data[0]
digits.images[0]
digits.data[0].reshape(8,8)
digits.target[0]
#you can see the pictures using this piece of code:
from matplotlib import pyplot as plt
plt.gray()
plt.matshow(digits.images[0])
plt.show()
#to count the number of examples of a particular class, you can use:
Y=digits.target
print(len(Y[Y==0]))

from sklearn.neural_network import MLPClassifier
X = digits.data

clf = MLPClassifier(solver='lbfgs', alpha=1e-5, hidden_layer_sizes=(5, 2),random_state=1)
clf.fit(X, Y)
print(clf.score(X, Y))
