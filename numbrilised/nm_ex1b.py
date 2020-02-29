def taylor(n,z):
    n+=1
    sum = 0
    f = 1
    for i in range(n):
        f *= max(i,1)
        if i%2==0:
            if i%4==0:
                sum += z**i/f
            else:
                sum -= z**i/f
#        print (sum,z**i/f,z,i,f)
    return sum

def cos(x):
    sum = 0
    f = 1
    for i in range(170):
        f *= max(i,1)
        if i%2==0:
            if i%4==0:
                sum += x**i/f
            else:
                sum -= x**i/f
#        print (sum,z**i/f,z,i,f)
    return sum
 
    
#print (taylor(13,4.7))

x = float(input())

delta = 1
i=1
while delta>10**(-8):
    delta = abs(taylor(i,x)-cos(x))
#    print (delta)
    i += 1
i -= 1
print (i)