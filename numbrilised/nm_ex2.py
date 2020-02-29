import math
alfa = 26

def f(x):
    return (10+alfa)*x-(1+0.001*alfa)**x

def g1(x):
    return (1+0.001*alfa)**x/(10+alfa)

def g2(x):
    return math.log((10+alfa)*x)/math.log(1+0.001*alfa)

def iter(x0, g, epsilon, maxit):
    lastx = x0
    for i in range(maxit):
        newx = g(lastx)
        if (abs(lastx-newx) <= epsilon):
            return newx
        lastx = newx
    return lastx

print (alfa)

# Leian väiksema lahendi:
print (iter(0.5, g1, 10**(-6), 10000))

# Leian suurema lahendi esimese pakkumise:
x0 = 10
while f(x0)>0:
    x0 *= 10

# Leian suurema lahendi:
print (iter(x0, g2, 10**(-3), 10000))