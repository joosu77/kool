q = 0.27

def f1(x2,x3):
    return float(13-x2**2+4*x3)/15

def f2(x1,x3):
    return float(11+x3-x1**2)/10

def f3(x2):
    return float(22+x2**3)/25

def leiaKaugus(x11,x12,x21,x22,x31,x32):
    return max(abs(x11-x12),abs(x21-x22),abs(x31-x32))



def iter(x10,x20,x30,eps):
    x1 = x10
    x2 = x20
    x3 = x30
    kaugus = leiaKaugus(x1,f1(x2,x3),x2,f2(x1,x3),x3,f3(x2))
    while kaugus*q/(1-q)>eps:
        print("q: "+str(q)+" x1: "+str(x1)+" x2: "+str(x2)+" x3: "+str(x3))
        eelmine1 = x1
        eelmine2 = x2
        eelmine3 = x3
        
        x1 = f1(eelmine2,eelmine3)
        x2 = f2(eelmine1,eelmine3)
        x3 = f3(eelmine2)
        kaugus = leiaKaugus(x1,f1(x2,x3),x2,f2(x1,x3),x3,f3(x2))


def seidel(x10,x20,x30,eps):
    x1 = x10
    x2 = x20
    x3 = x30
    kaugus = leiaKaugus(x1,f1(x2,x3),x2,f2(x1,x3),x3,f3(x2))
    while kaugus*q/(1-q)>eps:
        print("q: "+str(q)+" x1: "+str(x1)+" x2: "+str(x2)+" x3: "+str(x3))
        x1 = f1(x2,x3)
        x2 = f2(x1,x3)
        x3 = f3(x2)
        kaugus = leiaKaugus(x1,f1(x2,x3),x2,f2(x1,x3),x3,f3(x2))
        
print "leian vastuse iteratsioonimeetodiga:"
iter(0.75,0.75,0.75,10**(-5))

print "leian vastuse seideliimeetodiga:"
seidel(0.75,0.75,0.75,10**(-5))