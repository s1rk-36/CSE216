import csv
from os import read

class ServerAttackDetector(object):

    def __init__(self, name):
        self.name = name

    def detect(self):
    
        reader = csv.reader(open(self.name, "r"))
        next(reader)
        next(reader)

        
        reader2 = csv.reader(open(self.name, "r"))
        next(reader2)

        a = 0
        b = "No suspicion found"
        num = 1

        for row in reader:
            row2 = next(reader2)
            num += 1
            duration = float(row[1])
            time1 = float(row[0][17:23])
            time2 = float(row2[0][17:23])
            

            if (row[2] == "UDP  " and row[12] == "suspicious" and (duration < 1)) :
                if(row2[2] == "UDP  " and row2[12] == "suspicious" and  (abs(time1 - time2) < 1)):
                    a = num
                    b = row
                    break
        return a, b