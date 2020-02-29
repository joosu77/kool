import numpy as np
import matplotlib.pyplot as plt

def f1(x):
    return np.sin(x)
d = input("sisesta nr 0.9 .. 1 ")
for i in range(100):
    if f1(i)>float(d):
        print (i)
        break
