import numpy as np

R = 6378.13500
d = int(input())/100000
V = 4*np.pi*(3*R*R*d+3*R*d*d+d*d*d)/3

dR = 0.0005
dd = 0.000005

osatuletisR = abs(4*np.pi*(6*R*d+3*d*d)/3)
osatuletisd = abs(4*np.pi*(3*R*R+6*R*d+3*d*d)/3)

absdelta = osatuletisR*dR+osatuletisd*dd
reldelta = absdelta/V

print ([V,absdelta,reldelta])