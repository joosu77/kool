import math
def f(x):
    return x*math.e**x

def algf(x):
    return math.e**2*(x-1)
    
def ristkylik(a,b,f,n):
    h = float(b-a)/n
    sum = 0
    for i in range(n):
        sum += f(a+(i+0.5)*h)
    sum *= h
    return sum

def trapets(a,b,f,n):
    h = float(b-a)/n
    sum = 0
    for i in range(n+1):
        if i == 0 or i == n:
            sum += f(a+i*h)
        else:
            sum += 2*f(a+i*h)
    sum *= h/2
    return sum

def simpson(a,b,f,n):
    h = float(b-a)/n
    sum = 0
    for i in range(n+1):
        if i == 0 or i == n:
            sum += f(a+i*h)
        else:
            sum += 2*(i%2+1)*f(a+i*h)
    sum *= h/3
    return sum

def runge(a,b,f,valem,eps):
    integreeria = {"ristkylik":ristkylik,"trapets":trapets,"simpson":simpson}[valem]
    aste = 2
    if valem == "simpson":
        aste = 4
    luger = 1
    eelmine = integreeria(a,b,f,2**luger)
    luger += 1
    uus = integreeria(a,b,f,2**luger)
    while abs((eelmine-uus)/(2**aste-1)) > eps:
        luger += 1
        eelmine = uus
        uus = integreeria(a,b,f,2**luger)
    return [uus, 2**(luger)]

print (runge(1,2,f,"ristkylik",10**(-5)))
print (runge(1,2,f,"trapets",10**(-5)))
print (runge(1,2,f,"simpson",10**(-5)))
