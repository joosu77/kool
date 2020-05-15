import random
f = open("sisend.txt", "w")
f.write("10000\n")
f.write("20000\n")
for i in range(20000):
    f.write(str(int(random.random()*10000))+" "+str(int(random.random()*10000))+" "+str(int(random.random()*10000))+"\n")
f.close()    