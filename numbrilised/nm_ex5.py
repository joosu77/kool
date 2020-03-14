q = 7.0/15

def g1(x1,x2,x3):
    return float(13-x2**2+4*x3)/15

def g2(x1,x2,x3):
    return float(11+x3-x1**2)/10

def g3(x1,x2,x3):
    return float(22+x2**3)/25


def iter(x0,g1,g2,g3,q,eps,maxit):
    x1 = x0[0]
    x2 = x0[1]
    x3 = x0[2]
    kaugus = max(abs(x1-g1(x1,x2,x3)),abs(x2-g2(x1,x2,x3)),abs(x3-g3(x1,x2,x3)))
    it = 0
    while kaugus*q/(1-q)>eps and it<maxit:
        print("q: "+str(q)+" x1: "+str(x1)+" x2: "+str(x2)+" x3: "+str(x3))
        eelmine1 = x1
        eelmine2 = x2
        eelmine3 = x3
        
        x1 = g1(eelmine1,eelmine2,eelmine3)
        x2 = g2(eelmine1,eelmine2,eelmine3)
        x3 = g3(eelmine1,eelmine2,eelmine3)
        kaugus = max(abs(x1-g1(x1,x2,x3)),abs(x2-g2(x1,x2,x3)),abs(x3-g3(x1,x2,x3)))
        it+=1
    return ([x1,x2,x3],it)


def seidel(x0,g1,g2,g3,q,eps,maxit):
    x1 = x0[0]
    x2 = x0[1]
    x3 = x0[2]
    kaugus = max(abs(x1-g1(x1,x2,x3)),abs(x2-g2(x1,x2,x3)),abs(x3-g3(x1,x2,x3)))
    it = 0
    while kaugus*q/(1-q)>eps and it<maxit:
        print("q: "+str(q)+" x1: "+str(x1)+" x2: "+str(x2)+" x3: "+str(x3))
        x1 = g1(x1,x2,x3)
        x2 = g2(x1,x2,x3)
        x3 = g3(x1,x2,x3)
        kaugus = max(abs(x1-g1(x1,x2,x3)),abs(x2-g2(x1,x2,x3)),abs(x3-g3(x1,x2,x3)))
        it+=1
    return ([x1,x2,x3],it)
        
iter([0.75,0.75,0.75],g1,g2,g3,q,10**(-5),100)

seidel([0.75,0.75,0.75],g1,g2,g3,q,10**(-5),100)

iter([1,1,1],g1,g2,g3,q,10**(-5),1)