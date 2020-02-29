import math

x0 = 7*math.pi/24

def f(x):
    return x+0.3-math.tan(x)

def g1(x):
    return math.atan(x+0.3)

def g2(x,q):
    return x+f(x)*(1+q)/3

def iter(x0, g, epsilon, maxit, q=0):
    lastx = x0
    for i in range(maxit):
        print (lastx)
        if q:
            newx = g(lastx, q)
        else:
            newx = g(lastx)
        if (abs(lastx-newx) <= epsilon):
            return newx
        lastx = newx
        
    print ("iter limit")
    return lastx

# Leian esimese lahendi:
print ("Hakkan esimest lahendit leidma")
print (iter(x0, g1, 10**(-6), 10000))

# Leian teise lahendi:
print ("Hakkan teist lahendit leidma")
print (iter(x0, g2, 10**(-6), 10000, 0.9))


# Leian kolmanda lahendi:
print ("Hakkan kolmandat lahendit leidma")
print (iter(x0, g2, 10**(-6), 10000, 0.8))