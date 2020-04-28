import math
import numpy as np
import matplotlib.pyplot as plt

def f(x):
    return np.e**x*np.sin(x)

def fNthDeriv(x, n):
    if n%4 == 1:
        return (-4)**(n//4)*math.e**x*(math.sin(x)+math.cos(x))
    elif n%4 == 2:
        return (-4)**(n//4)*math.e**x*2*math.cos(x)
    elif n%4 == 3:
        return (-4)**(n//4)*math.e**x*2*(math.cos(x)-math.sin(x))
    elif n%4 == 0:
        return (-4)**(n//4)*math.e**x*math.sin(x)


def newtoniLeidja(nullkohad, f, tapsus):
    kordajad = [nullkohad[0]]
    while kontrollNewton(kordajad, f) > tapsus:
        kordajad.append(nullkohad[len(kordajad)])
    return kordajad

def kontrollNewton(nullkohad, f):
    n = len(nullkohad)*10
    maxViga = 0
    for i in range(10):
        viga = abs(f(-1+2*i/n)-newtonInterpol(nullkohad, -1+2*i/n, f))
        if maxViga < viga:
            maxViga = viga
    return maxViga

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

def tayloriEhitaja(x0, fNthDeriv, tapsus):	
    kordajad = [fNthDeriv(x0,0)]
    while kontrollTaylor(kordajad, f) > tapsus:
        n = len(kordajad)
        kordaja = fNthDeriv(x0, n)/math.factorial(n)
        for i in range(len(kordajad)):
            kordajad[i] += kordaja*(-1*x0)**(n-i)*math.factorial(n+1)/(math.factorial(i+1)*math.factorial(n-i+1))
        kordajad.append(kordaja)
    return kordajad

def taylorParser(x, kordajad):
    sum = 0
    for i in range(len(kordajad)):
        sum += kordajad[i] * x**i
    return sum

def kontrollTaylor(kordajad, f):
    n = len(kordajad)*10
    maxViga = 0
    for i in range(n):
        viga = abs(f(-1+2*i/n)-taylorParser(-1+2*i/n,kordajad))
        if viga > maxViga:
            maxViga = viga
    return maxViga

nullkohad = [0,-0.97,0.97,-0.65,0.65,-0.4,0.4,-0.88,0.88]

fig, plots = plt.subplots(3, 2)
    
for m in [0,1,2]:
    print ("\n\nm="+str(m)+":")
    print ("Newtoni interpolatsioonipolynoom:")
    a = newtoniLeidja(nullkohad, f, 10**(-1*m))
    print ("Selle suurim viga lõigus [-1,1]: "+str(kontrollNewton(a,f)))
    print ("\nTaylori polünoom:")
    b = tayloriEhitaja(0, fNthDeriv, 10**(-1*m))
    for i in range(len(b)):
        print (str(b[i])+"x^"+str(i), end="")
        if i == len(b)-1:
            print()
        else:
            print(" * ", end="")
    print ("Selle suurim viga lõigus [-1,1]: "+str(kontrollTaylor(b,f)))
    
    t1 = np.arange(-2.5,2.5,0.1)
    t2 = np.arange(-1,1,0.1)
    plots[m, 0].plot(t1, newtonInterpol(a, t1, f), 'red', label="Newtoni interpolatsioonipolünoom")
    plots[m, 0].plot(t1, taylorParser(t1, b), 'blue', label="Taylori polünoom")
    plots[m, 0].plot(t1, f(t1), 'green', label="f(x)")
    plots[m, 0].title.set_text("m="+str(m)+", polünoomid")
    plots[m, 0].legend()
    
    plots[m, 1].plot(t2, f(t2) - newtonInterpol(a, t2, f), 'red', label="f(x) - Newtoni polünoom")
    plots[m, 1].plot(t2, f(t2) - taylorParser(t2, b), 'blue', label="f(x) - Taylori polünoom")
    plots[m, 1].title.set_text("m="+str(m)+", polünoomide vead")
    plots[m, 1].legend()
    
plt.show()