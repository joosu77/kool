import numpy as np
import matplotlib.pyplot as plt

# Lähendatav funktsioon:
def f(x):
    return abs(x)

# Leiab polünoomi ning väljastab selle kordajad:
def vahimRuutMeetod(m,n,alfa):
    # Sõlmed:
    x = [-1+2*i/m for i in range(m+1)]
    # maatriks A:
    A = np.asmatrix([[x[i]**j for j in range(n+1)] for i in range(m+1)])
    D = A.T*A
    b = A.T * np.asmatrix([f(x[i]) for i in range(m+1)]).T
    c = np.linalg.solve(D + np.identity(n+1) * alfa, b)
    return c.T.tolist()[0]

# Annab polünoomi väärtuse sisestatud argumendi ja polünoomi kordajate puhul
def polyParser(kordajad, x):
    sum = 0
    for i in range(len(kordajad)):
        sum += x**i*kordajad[i]
    return sum

# Leiab polünoomi ja funktsiooni vahelise vea:
def viga(kordajad):
    maxViga = 0
    m = len(kordajad)*10
    for i in  [-1+2*i/(m-1) for i in range(m)]:
        viga = abs(f(i)-polyParser(kordajad,i))
        if viga > maxViga:
            maxViga = viga
    return maxViga

# Leian antud kolmikutega polünoomid ning prindin nende vead:
kolmikud = [[6,6,10**(-3.5)], [6,7,10**(-3.5)], [12,9,0], [12,12,10**(-8)], [18,15,10**(-9)], [18,18,10**(-6)], [24,18,10**-12], [24,24,10**(-6)]]
for kolmik in kolmikud:
    print("kolmik: m="+str(kolmik[0])+" n="+str(kolmik[1])+" alfa="+str(kolmik[2]))
    kordajad = vahimRuutMeetod(kolmik[0], kolmik[1], kolmik[2])
    print ("viga: "+str(viga(kordajad))+"\n")

# Joonistan antud kolmikutega polünoomid välja:
joonistatavad = [[24,24,10**(-1)],[24,24,10**(-6)],[24,24,10**(-12)]]
sis = np.arange(-1,1,0.01)
for kolmik in joonistatavad:
    kordajad = vahimRuutMeetod(kolmik[0], kolmik[1], kolmik[2])
    plt.plot(sis, polyParser(kordajad, sis), label=" m="+str(kolmik[0])+" n="+str(kolmik[1])+" alfa="+str(kolmik[2]))
plt.plot(sis, f(sis),label="|x|")
plt.legend()
plt.show()