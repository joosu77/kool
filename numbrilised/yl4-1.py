import math
import matplotlib.pyplot as plt
import numpy

alfa = 26

def f(x):
    return x*numpy.sin(float(x)/(x-alfa))

def g(x):
    return 0

def iter(x0, x1, epsilon):
    eelmine = x0
    uus = x1
    iterarv = 0
    while abs(f(uus))>epsilon:
        temp = uus
        uus = uus - f(uus)*(uus-eelmine)/(f(uus)-f(eelmine))
        eelmine = temp
        iterarv += 1
        print ("x",iterarv, ": ",uus, " f(x):", f(uus))
    return (uus,iterarv)

print (iter(11,10,10**(-8)))
print (iter(22,23,10**(-8)))
print (iter(18.5,19,10**(-8)))

f2 = numpy.vectorize(f)
g2 = numpy.vectorize(g)
numbrid = numpy.arange(0.0, 25.0, 0.1)
plt.plot(numbrid, f2(numbrid))
plt.plot(numbrid,g2(numbrid))
plt.plot(18.5, f(18.5), '-gD', markevery=1)
plt.plot(19, f(19), '-gD', markevery=1)
#plt.plot(19 - f(19)*(0.5)/(f(19)-f(18.5),f(19 - f(19)*(0.5)/(f(19)-f(18.5))))

vals = numpy.linspace(15,22,1000)
x_vals = [18.5/2,19*2]
y_vals = [f(18.5/2),f(19*2)]
plt.plot(vals,f(19)-19*(f(19)-f(18.5))/(0.5)+vals*(f(19)-f(18.5))/(0.5))

plt.show()