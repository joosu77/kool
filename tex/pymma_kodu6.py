import numpy as np
import matplotlib.pyplot as plt

def ff(x):
    return np.pi/4-x/2
def f(x,k):
    #return 2/np.pi*(np.pi*k-2*np.sin(np.pi*k)+k*np.pi*np.cos(np.pi*k))/(4*k*k)*np.sin(k*x)
    return (1+(-1)**k)/(2*k)*np.sin(k*x)
def g(x,k):
    return 2*(1-np.cos(k*np.pi))/(k*np.pi)*np.sin(k*x)
def h(x,k):
    return 2/np.pi*(-np.pi*k*np.cos(np.pi*k)+np.sin(np.pi*k))/k**2*np.sin(k*x)
def s(x,n):
    res = 0
    for i in range(1,n+1):
        res+=f(x,i)
    return res
def ss(x,n):
    res = 0
    for i in range(1,n+1):
        res+=s(x,i)
    return res/(n+1)

t = np.arange(-5,5,0.01)
#plt.plot(t,s(t, 10), label='s10')
#plt.plot(t,s(t, 100), label='s100')
plt.plot(t,ff(t), label='f')
plt.plot(t,ss(t,10), label='sigma10')
plt.plot(t,ss(t,100), label='sigma100')
plt.legend()
plt.show()