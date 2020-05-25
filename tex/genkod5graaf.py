import matplotlib.pyplot as plt
import numpy as np

def f(k,x):
    sum = 0
    for i in range(k+1):
        sum += (i*x)/(1+i**5*x**2)
    return sum

t = np.arange(-0.2,0.2,0.001)
plt.plot (t,f(3,t), label='S3(x)')
plt.plot (t,f(5,t), label='S5(x)')
plt.plot (t,f(100,t), label='S100(x)')
plt.legend()
plt.show()