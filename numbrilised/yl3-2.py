# x* = 0.5
def f1(x):
    return (2*x-1)/(x-1)

def f1prim(x):
    return -1/(x-1)**2
    
def f1jagatis(x):
    return -1*(2*x-1)*(x-1)

# x* = 1.75
def f2(x):
    return (4*x-7)/(x-2)

def f2prim(x):
    return -1/(x-2)**2

def f2jagatis(x):
    return -1*(4*x-7)*(x-2)

def iter(x_0, delta, lahendikordsus, fjagatis):# f, fprim):
    eelviimane = x_0
    eelmine = x_0
    #uus = x_0-f(x_0)/fprim(x_0)
    uus = x_0-fjagatis(x_0)
    while abs(eelmine-uus)>delta:
        eelviimane = eelmine
        eelmine = uus
        #uus = uus-lahendikordsus*f(uus)/fprim(uus)
        uus = uus-lahendikordsus*fjagatis(uus)
        kiirus = (uus-eelmine)/(eelmine-eelviimane)
        #print "x: " + str(eelmine) + " kiirus: "+str(kiirus)
    return uus

print ("f1, 0.9")
print iter(0.9,10**(-5), 1, f1jagatis)
print ("f1, 0")
print iter(0.0,10**(-5), 1, f1jagatis)
print ("f1, 3")
print iter(3.0,10**(-5), 1, f1jagatis)
print ("f1, 0.3")
print iter(0.3,10**(-5), 1, f1jagatis)
print ("f1, -1")
print iter(-1.0,10**(-5), 1, f1jagatis)

print ("f2, 1.625")
print iter(1.625,10**(-5), 1, f2jagatis)
print ("f2, 1.5")
print iter(1.5,10**(-5), 1, f2jagatis)
print ("f2, 3")
print iter(3.0,10**(-5), 1, f2jagatis)
print ("f2, 1.99")
print iter(1.99,10**(-5), 1, f2jagatis)
print ("f2, 0.5")
print iter(0.5,10**(-5), 1, f2jagatis)
