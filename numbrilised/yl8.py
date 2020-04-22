import matplotlib.pyplot as plt
import numpy as np
import math

def vordsedSolmed(n):
    return [-1+2*i/n for i in range(n+1)]

def tsebosoviSolmed(n):
    return [math.cos(math.pi*(2*i+1)/(2*(n+1))) for i in range(n+1)]

def lagrangeInterpol(nullkohad, x, f):
    n = len(nullkohad)
    tulemus = 0
    for i in range(n):
        hetkeLiige = f(nullkohad[i])
        for j in range(n):
            if i != j:
                hetkeLiige *= (x-nullkohad[j])/(nullkohad[i]-nullkohad[j])
        tulemus += hetkeLiige
    return tulemus

def newtonInterpol(nullkohad, x, f):
    n = len(nullkohad)
    tulemus = 0
    for i in range(n):
        hetkeLiige = newtonKordaja(nullkohad[:(i+1)], f)
        for j in range(i):
            hetkeLiige *= (x-nullkohad[j])
        tulemus += hetkeLiige
    return tulemus

def newtonKordaja(nullkohad, f):
    n = len(nullkohad)
    tulemus = 0
    for i in range(n):
        omega = 1
        for j in range(n):
            if i!=j:
                omega *= (nullkohad[i]-nullkohad[j])
        tulemus += f(nullkohad[i])/omega
    return tulemus


def viga(nullkohad, testNullkohad, f):
    n1 = len(nullkohad)
    n2 = len(testNullkohad)
    viga = 0
    # x väärtus, mil suurim viga realiseerus:
    veaX = 0
    for i in range(n2):
        delta = abs(newtonInterpol(nullkohad,testNullkohad[i],f)-f(testNullkohad[i]))
        if viga<delta:
            viga = delta
            veaX = testNullkohad[i]
    return [viga,veaX]

def a(x):
    return abs(x)

for i in range(4):
    n = [6,12,18,24][i]
    vordsetegaViga = viga(vordsedSolmed(n),vordsedSolmed(10*n),a)
    print("Võrdsete sõlmedega n="+str(n)+", viga="+str(vordsetegaViga[0])+", realiseerus x väärtusel: "+str(vordsetegaViga[1]))
    tsebosovigaViga = viga(tsebosoviSolmed(n),vordsedSolmed(10*n),a)
    print("Tšebõšovi sõlmedega n="+str(n)+", viga="+str(tsebosovigaViga[0])+", realiseerus x vaartusel: "+str(tsebosovigaViga[1]))

# Järgmistel ridadel on ka graafikul n=20 mõlemate nullkohtadega kui see sisse tagasi kommenteerida
#t = np.arange(-1,1,0.1)
#plt.plot(t,newtonInterpol(nullkohad1,t,a),'red')
#plt.plot(t,newtonInterpol(nullkohad2,t,a),'blue')
#plt.plot(t,abs(t),'green')
#plt.show()