import math
def f(x):
    return 1/(1+x*x)

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

def nicole_cotes(a,b,f,n):
    kordajad = [[1/8,3/8,3/8,1/8],[7/90,32/90,12/90,32/90,7/90],[19/288,75/288,50/288,50/288,75/288,19/288],[41/840,216/840,27/840,272/840,27/840,216/840,41/840]][n-3]
    sum = 0
    h = float(b-a)/n
    for i in range(len(kordajad)):
        sum += kordajad[i] * f(a+i*h)
    sum *= (b-a)
    return sum

tapne = 2*math.atan(4)
for i in range(3,7):
    t = trapets(-4,4,f,i)
    n = nicole_cotes(-4,4,f,i)
    print(str(i)+" "+str(n)+" "+str(tapne-n)+" "+str(tapne-t))