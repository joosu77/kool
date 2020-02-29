import numpy as np
import matplotlib.pyplot as plt

def f1(x):
    return x*x*(np.e)**(-x*x)
def f2(x):
    return x*x*x*x*(np.e)**(-x*x)

i = np.linspace(0,3,31)
plt.plot(i,f1(i),color="red")
plt.plot(i,f2(i))
plt.legend(["f1","f2"])
plt.show()