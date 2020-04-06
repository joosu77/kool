sis = open("tm_2019_lp_veebi.csv","r")
val = open("andmed.txt", "w")
for line in sis:
    list = line.split(",")
    if (list[5] != '' and list[6] != '' and list[7] != '' and list[8] != '' and list[9] != '' and list[10] != '' and list[11] != ''):
        val.write(f'{list[3]},{list[5]},{list[6]},{list[7]},{list[8]},{list[9]},{list[10]},{list[11]}\n')
    
sis.close()
val.close() 