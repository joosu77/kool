def f(x):
    return x**2

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


print (ristkylik(2,13,f,100))
print (trapets(2,13,f,100))
print (simpson(2,13,f,100))
