import math

def f(x):
    return x*x-2*x*math.e**(-x)+math.e**(-2*x)

def fprim(x):
    return 2*x-2*math.e**(-x)+2*x*math.e**(-x)-2*math.e**(-2*x)

def iter(x_0, delta, lahendikordsus):
    eelviimane = x_0
    eelmine = x_0
    uus = x_0-f(x_0)/fprim(x_0)
    while abs(eelmine-uus)>delta:
        eelviimane = eelmine
        eelmine = uus
        uus = uus-lahendikordsus*f(uus)/fprim(uus)
        kiirus = (uus-eelmine)/(eelmine-eelviimane)
        print "x: " + str(eelmine) + "kiirus: "+str(kiirus)
    return uus

print iter(0.5,10**(-5),1)

print iter(0.5,10**(-5),2)