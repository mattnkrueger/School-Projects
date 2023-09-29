#Matthew Krueger (mnkrueger)
#CS1 Fundamentals
#Project 1 Phase 3
#Date: 4/6/2023


# =================================================== PHASE 1 ===================================================
def extractCityStateNames(line):
    pieces = line.split(",")
    return pieces[0] + pieces[1][:3]


def extractCoordinates(line):
    pieces = line.split(",")
    return [int(pieces[1].split("[")[1]), int(pieces[2].split("]")[0])]

def extractPopulation(line):
    pieces = line.split(",")
    return int(pieces[2].split("]")[1])


def loadData(cityList, coordList, popList, distanceList):
    f = open("miles.dat")
    cityIndex = 0
    distances = []
    distanceList.append([])
    for line in f:
        if line[0].isalpha():
            if distances != []:
                distanceList.append(distances[::-1])
                distances = []
            
            cityList.append(extractCityStateNames(line))
            coordList.append(extractCoordinates(line))
            popList.append(extractPopulation(line))
            cityIndex = cityIndex + 1       
        elif line[0].isdigit():
            distances.extend([int(x) for x in line.split()])
        
    if distances != []:
        distanceList.append(distances[::-1])
            
def getCoordinates(cityList, coordList, name):
    return coordList[cityList.index(name)]


def getPopulation(cityList, popList, name):
    return popList[cityList.index(name)]

def getDistance(cityList, distanceList, name1, name2):
    index1 = cityList.index(name1)

    index2 = cityList.index(name2)

    if index1 == index2:
          return 0
    elif index1 < index2:

        return distanceList[index2][index1]
    else:

        return distanceList[index1][index2]
    

def nearbyCities(cityList, distanceList, name, r):
    result = []
    i = cityList.index(name)  
    j = 0
    for d in distanceList[i]:      # For every other previous city
        if d <= r :      # If within r of named city
            result = result + [cityList[j]]  # Add to result
        j = j + 1   
    j = i + 1
    while (j < len(distanceList)): # For every other previous city
        if distanceList[j][i] <= r: # If within r of named city
            result = result + [cityList[j]] # Add to result
        j = j + 1     
    return result
# =================================================== PHASE 2 ===================================================
def locateFacilities(cityList, distanceList, r):
    served = [False] * len(cityList)
    facilities = []
    while not all(served):
        bestFacilityI = possibleFacilities(cityList, distanceList, r, served, facilities)
        facilities += [cityList[bestFacilityI]]
        served[bestFacilityI] = True
        nearServingCities = nearbyCities(cityList, distanceList, cityList[bestFacilityI], r)
        for city in nearServingCities:
            served[cityList.index(city)] = True
    return facilities

def possibleFacilities(cityList, distanceList, r, served, facilities):
    nearbyCity = []
    inRange = [0] * len(cityList)
    for i in range(len(cityList)):
        if cityList[i] not in facilities:
            nearbyCity = nearbyCities(cityList, distanceList, cityList[i], r)
        if served[i] == False:
            counter = 1
        else:
            counter = 0
        for city in nearbyCity:
            if served[cityList.index(city)] == False:
                counter += 1
        inRange[i] = counter
        i += 1
    best = max(inRange) 
    bestFacilityIndex = inRange.index(best)
    return bestFacilityIndex

# =================================================== PHASE 3 ===================================================
def display(facilities, cityList, distanceList, coordList):
    #place pushpins on facilities in google earth
    #for remaining cities, line segments are drawn to the nearest facility
    #create a kml file that can be opened in google earth that shows the facilities and the cities they serve
    #submission to icon: project1phase3.py, visualization300.kml & visualization800.kml 
    #visualization300.kml & visualization800.kml are the kml files that are created to show the facilities served cities in r = 300 and r = 800 respectively

    #create kml file called visualization300.kml that says hello and updates file
    f = open("visualization300.kml", "w")
    f.write("Helo\n")
    f.close()

    




# =================================================== MAIN ===================================================



#cityList = []
#coordList = []
#popList = []
#distanceList = []
#loadData(cityList, coordList, popList, distanceList)
#r = 10000
#locateFacilities(cityList, distanceList, r)
#print(locateFacilities(cityList, distanceList, r))