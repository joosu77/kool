import numpy

H = [[0 for _ in range(12)] for _ in range(12)]
for i in range (12):
    for j in range (12):
        # +1 mitte -1 kuna i ja j algavad 0st mitte 1st
        H[i][j] = float(1)/(i+j+1)

xlah = numpy.matrix([1 for _ in range(12)]).I
b = numpy.dot(H,xlah)
bd = numpy.round(b,9)

# Gaussi meetod:
x = numpy.linalg.solve(H, b)
print ("x = ", x)
print ("x viga = ", numpy.linalg.norm(x-xlah,2))
xd = numpy.linalg.solve(H, bd)
print ("xd = ", xd)
print ("xd viga = ", numpy.linalg.norm(xd-xlah,2))

Hpoord = numpy.linalg.inv(H)
xp = numpy.dot(Hpoord, b)
print ("xp = ", xp)
print ("xp viga = ", numpy.linalg.norm(xp-xlah,2))
xpd = numpy.dot(Hpoord, bd)
print ("xpd = ", xpd)
print ("xpd viga = ", numpy.linalg.norm(xpd-xlah,2))

eelmine = numpy.matrix([0 for _ in range(12)]).I
uus = eelmine - (1.99/numpy.linalg.norm(H,2))*(H*eelmine - bd)
lugeja = 0
otsinC1 = True
otsinC2 = True
kaugus = numpy.linalg.norm(H*uus-bd)
delta = numpy.sqrt(12)*10**-9
while kaugus > delta/7.5:
    eelmine = uus
    uus = eelmine - (1.99/numpy.linalg.norm(H,2))*(H*eelmine - bd)    
    lugeja += 1
    #print (kaugus)
    kaugus = numpy.linalg.norm(H*uus-bd)
    if (otsinC1 and kaugus < delta*7.5):
        otsinC1 = False
        print ("x1 = ", uus)
        print ("x1 viga = ", numpy.linalg.norm(uus-xlah,2))
        print ("m1 = ", lugeja)
    elif (otsinC2 and kaugus < delta):
        otsinC2 = False
        print ("x2 = ", uus)
        print ("x2 viga = ", numpy.linalg.norm(uus-xlah,2))
        print ("m2 = ", lugeja)
print ("x3 = ", uus)
print ("x3 viga = ", numpy.linalg.norm(uus-xlah,2))
print ("m3 = ", lugeja)

print ("H maatriksi konditsiooniarv = ", numpy.linalg.cond(H,2))