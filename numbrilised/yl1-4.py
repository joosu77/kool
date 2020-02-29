import numpy as np
import matplotlib.pyplot as plt

def f1(x):
    return np.sin(x)

i = np.linspace(1,100,100)
plt.bar(i,f1(i),color="red")
plt.show()

