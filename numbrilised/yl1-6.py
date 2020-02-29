import numpy as np

def taylor(n,z):
   sum = 0
    f = 1
    for i in range(n):
        f *= max(i,1)
        if i%2==0:
            if i%4==0:
                sum += z**i/f
            else:
                sum -= z**i/f
    return sum
    
x = float(input())

delta = 1
i=1
while delta>10**(-8):
    delta = abs(taylor(i,x)-np.cos(x))
    i += 1

print (i)