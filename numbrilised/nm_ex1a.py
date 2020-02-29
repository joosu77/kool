pi = 3.14159265359
R = 6378.13500
d = int(input())/100000
V = 4*pi*(3*R*R*d+3*R*d*d+d*d*d)/3

# delta R = 0.0005 kuna see sisendandmete täpsus
# suhteline viga: sum 1..n osatuletis u*delta(x_i)

dR = 0.0005
dd = 0.000005

osatuletisR = abs(4*pi*(6*R*d+3*d*d)/3)
osatuletisd = abs(4*pi*(3*R*R+6*R*d+3*d*d)/3)

absdelta = osatuletisR*dR+osatuletisd*dd
reldelta = absdelta/V

print ([V,absdelta,reldelta])