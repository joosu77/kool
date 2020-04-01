import numpy

def F(x):
    return [3*x[0]-x[1]*x[1]-x[2]-2,x[0]*x[0]+10*x[1]-x[2]-11,x[1]*x[1]+7*x[2]-7]

def dF(x):
    return [[3,-2*x[1],-1],[2*x[0],10,-1],[0,2*x[1],7]]

def norm(x,x1):
    return (abs(x[0]-x1[0])+abs(x[1]-x1[1])+abs(x[2]-x1[2]))

def newton (x0, F, dF, eps, maxit):
    jargmine = x0-(numpy.matrix(dF(x0)).I*numpy.matrix(F(x0)).T).T
    vana = x0
    ite = 1
    jargmine = jargmine.A[0]
    print(jargmine)
    print(vana)
    while (norm(vana, jargmine) > eps and ite<maxit):
        vana = jargmine
        jargmine = vana-(numpy.matrix(dF(vana)).I*numpy.matrix(F(vana)).T).T
        jargmine = jargmine.A[0]
        print ("lähend: ", end='')
        print (vana, end='')
        print (", kaugus eelmisest: ",end='')
        print (norm(vana,jargmine))
        ite += 1
    return (jargmine.tolist(),ite)


def modnewton (x0, F, dF, eps, maxit):
    jargmine = x0-(numpy.matrix(dF(x0)).I*numpy.matrix(F(x0)).T).T
    vana = x0
    ite = 1
    jargmine = jargmine.A[0]
    print(jargmine)
    print(vana)
    while (norm(vana, jargmine) > eps and ite<maxit):
        vana = jargmine
        jargmine = vana-(numpy.matrix(dF(x0)).I*numpy.matrix(F(vana)).T).T
        jargmine = jargmine.A[0]
        print ("lähend: ", end='')
        print (vana, end='')
        print (", kaugus eelmisest: ",end='')
        print (norm(vana,jargmine))
        ite += 1
    return (jargmine.tolist(),ite)

print (newton([1,1,1],F,dF,10**(-5),1000))
print()
print (modnewton([1,1,1],F,dF,10**(-5),1000))

#print()
#print(F([-1.0,1.2,-0.11]))