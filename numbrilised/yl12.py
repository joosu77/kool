import math
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.lines as mlines

def f(x):
    return math.log(x)

def fNthPrim(x,n):
    if n==0:
        return math.ln(x)
    else:
        return (-1)**(n+1)*x**(-n)*math.factorial(n-1)

def fprim(h, n):
    if n==0:
        return (f(2+h)-f(2))/h
    elif n==1:
        return (f(2+h)-f(2-h))/(2*h)
    elif n==2:
        return (f(2-2*h)-8*f(2-h)+8*f(2+h)-f(2+2*h))/(12*h)
        
def f2prim(h):
    return (f(2+h)-2*f(2)+f(2-h))/(h*h)

minViga = [100,100,100,100]
minH = [0,0,0,0]
for h in range(1,18):
    for i in [0,1,2]:
        viga = abs(fprim(10**(-h),i) - fNthPrim(2,1))
        if viga < minViga[i]:
            minViga[i] = viga
            minH[i] = h
    viga = abs(f2prim(10**(-h)) - fNthPrim(2,2))
    if viga < minViga[3]:
        minViga[3] = viga
        minH[3] = h

for i in [0,1,2]:
    print ("Esimese tuletise valemi "+str(i+1)+" puhul on optimaalne h = "+str(10**(-minH[i]))+" veaga "+str(minViga[i]))
print ("Teise tuletise leidmiseks optimaalne h = "+str(10**(-minH[3]))+" veaga "+str(minViga[3]))

# Esimese valemi jaoks optimaalse h arvutamine:
#  g(h) = 2*epsilon/h+M*h/2
#  epsilon = 2^(-52)
#  M = 2^(-2)
#  g(h) = 2^(-51)*h^(-1) + h*2^(-3)
#  g'(h) = -2^(-51)*h^(-2) + 2^(-3) = 0
#  h = 2^(-24) =~ 5.96 * 10^(-8)
#  Arvutuslikult sain h = 10^(-8)
#
# Samamoodi teise valemi optimaalne h:
#  h = (3*2^(-53))^(1/3)*1.9 =~ 1.32 * 10^(-5)
#  Arvutuslikult sain h = 10^(-5)
#
# Kolmanda valemi optimaalne h:
#  h = (15*2^(-57))^(1/5)*1.8 =~ 1.14 * 10^(-3)
#  Arvutuslikult sain h = 10^(-3)
#
# Neljanda valemi optimaalne h:
#  h = 1.9^2*(5*2^49)^(-1/4) =~ 4.96 * 10^(-4)
#  Arvutuslikult sain h = 10^(-4)
#
# Seega kõik arvutuslikud tulemused on optimaalsete h-dega samas suurusjärgus

fig, plots = plt.subplots(4)
punktid = []
for h in range(1,18):
    for i in [0,1,2]:
        viga = abs(fprim(10**(-h),i) - fNthPrim(2,1))
        plots[i].plot(math.log(h,10), math.log(viga,10),'blue', marker='+')#, label='Viga')
    plots[0].plot(math.log(h,10), math.log(2**(-51)*h**(-1)+h*2**(-3),10),'red', marker='o')#, label='g(h)')
    plots[1].plot(math.log(h,10), math.log(2**(-52)*h**(-1)+h**2*(1/3)*1.9**(-3),10),'red', marker='o')#, label='g(h)')
    plots[2].plot(math.log(h,10), math.log(3*2**(-53)*h**(-1)+h*(4/5)*1.8**(-5),10),'red', marker='o')#, label='g(h)')

    viga = abs(f2prim(10**(-h)) - fNthPrim(2,2))
    plots[3].plot(math.log(h,10), math.log(2**(-48)*h**(-2)+h**2*10*1.9**(-6),10),'red', marker='o')#, label='g(h)')
    plots[3].plot(math.log(h,10), math.log(viga,10),'blue', marker='+')#, label='Viga')
plots[0].title.set_text("Esimene diferentsvalem")
plots[1].title.set_text("Teine diferentsvalem")
plots[2].title.set_text("Kolmas diferentsvalem")
plots[3].title.set_text("Teist järku diferentsvalem")
for i in [0,1,2,3]:
    plots[i].legend(handles=[mlines.Line2D([],[],color='red', marker='o', label='g(h)'),mlines.Line2D([],[],color='blue', marker='+', label='Viga')])
plt.show()