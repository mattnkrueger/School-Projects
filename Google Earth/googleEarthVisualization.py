#Matthew Krueger (mnkrueger@uiowa.edu)
#Project 1: Google Earth Facility Problem Solved

#
#   EXTRACT DATA FROM FILE: miles.dat 
#   UPDATE LISTS WITH INFORMATION
#

import random

#EXTRACT CITY AND STATE NAMES
def extractCityStateNames(line): #extracts city and state names from miles.dat
    pieces = line.split(",")
    return pieces[0] + pieces[1][:3]

#EXTRACT COORDS
def extractCoordinates(line): #extracts coordinates from miles.dat
    pieces = line.split(",")
    return [int(pieces[1].split("[")[1]), int(pieces[2].split("]")[0])]

#EXTRACT POPULATION
def extractPopulation(line): #extracts population from miles.dat
    pieces = line.split(",")
    return int(pieces[2].split("]")[1])

#EXTRACT FROM miles.dat
def loadData(cityList, coordList, popList, distanceList): #extracts data from miles.dat
    f = open("miles.dat") #open file
    cityIndex = 0
    distances = []
    distanceList.append([])
    for line in f: #for each line in file
        if line[0].isalpha():  #if line starts with a letter
            if distances != []:
                distanceList.append(distances[::-1])
                distances = []
            
            cityList.append(extractCityStateNames(line))
            coordList.append(extractCoordinates(line))
            popList.append(extractPopulation(line))
            cityIndex = cityIndex + 1       
        elif line[0].isdigit(): #if line starts with a number
            distances.extend([int(x) for x in line.split()])
        
    if distances != []:
        distanceList.append(distances[::-1])

#GET COORDINATES OF CITY
def getCoordinates(cityList, coordList, name): #get coordinates of city
    return coordList[cityList.index(name)]

#GET POPULATION OF CITY
def getPopulation(cityList, popList, name): #get population of city
    return popList[cityList.index(name)]

#GET DISTANCE BETWEEN TWO CITIES
def getDistance(cityList, distanceList, name1, name2): #get distance between two cities
    index1 = cityList.index(name1)

    index2 = cityList.index(name2)

    if index1 == index2:
          return 0
    elif index1 < index2:

        return distanceList[index2][index1]
    else:

        return distanceList[index1][index2]
    
#FIND NEARBY CITIES IN RADIUS R
def nearbyCities(cityList, distanceList, name, r): #find nearby cities in radius r
    result = []
    i = cityList.index(name)  
    j = 0
    for d in distanceList[i]:   
        if d <= r :      
            result = result + [cityList[j]]  
        j = j + 1   
    j = i + 1
    while (j < len(distanceList)): 
        if distanceList[j][i] <= r: 
            result = result + [cityList[j]] 
        j = j + 1     
    return result

#
#   SOLVE FACILITY LOCATION PROBLEM
#   FIND MOST OPTIMAL PLACEMENTS OF FACILITIES SUCH THAT ALL CITIES ARE IN A DISTANCE R FROM FACILITY
#

#FUNCTION TO FIND FACILITIES THAT SERVE CITIES
def locateFacilities(cityList, distanceList, r): #find facilities that serve cities
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

#HELPER FUNCTION TO FIND BEST FACILITIES
def possibleFacilities(cityList, distanceList, r, served, facilities): #find best facilities
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

#
#   UPLOAD TO GOOGLE EARTH
#   CREATE A KML TEXT FILE TO VISUALLY REPRESENT THE FACILITIES AND CITIES SERVED
#

#CREATE STYLE FORMAT FOR LINE
def createLineStyle(name, width, color):  #create style format for line
    result =  "<Style id=\"" + name+ "\">\n"
    result += "  <LineStyle>\n"
    result += "    <color>"+ color +"</color>\n"
    result += "    <width>"+ width + "</width>\n"
    result += "  </LineStyle>\n"
    result += "</Style>"
    return result

#RANDOM STYLE GENERATOR
def randomStyle():  #random style generator
    list = ["yellowLine", "redLine", "greenLine", "blueLine", "purpleLine", "orangeLine", "pinkLine", "brownLine"]
    style = random.choice(list)
    return style

#MATCH STYLE TO DESCRIPTION
def style_desc(style): #match style to description
    styleList = ["yellowLine", "redLine", "greenLine", "blueLine", "purpleLine", "orangeLine", "pinkLine", "brownLine"]
    dl = ["yellow line", "red line", "green line", "blue line", "purple line", "orange line", "pink line", "brown line"]
    index = styleList.index(style)
    return dl[index]

#CREATE A CUSTOM PIN TO MARK CITIES
def createCityPoint(name, description, coords):  #create a custom pin to mark cities
    long = float(coords[1]/100)*-1
    lat = float(coords[0]/100)
    result = ""
    result += "<Placemark>\n"
    result += " <name>"+name+"</name>\n"
    result += " <description>"+ description+"</description>"
    result += " <Point>\n"
    result += "  <coordinates>"+ str(long) + ","+ str(lat) + ",0" +"</coordinates>\n"
    result += " </Point>\n"
    result += "</Placemark>"
    return result

#CREATE A CUSTOM PIN TO MARK FACILITIES 
def createFacilityPoint(name, description, coords, icon):  #create a custom pin to mark facilities
    long = float(coords[1]/100)*-1
    lat = float(coords[0]/100)
    result = ""
    result += "<Placemark>\n"
    result += " <name>"+name+"</name>\n"
    result += " <Style>\n"
    result += "    <IconStyle>\n"
    result += "        <color>ff336699</color>\n"
    result += "        <scale>1.1</scale>\n"
    result += "        <Icon>\n"
    result += "           <href>"+ icon + "</href>\n"
    result += "        </Icon>\n"
    result += "   </IconStyle>\n"
    result += "   <LabelStyle>\n"
    result += "       <color>ff336699</color>\n"
    result += "   </LabelStyle>\n"
    result += " </Style>"
    result += " <description>"+description+"</description>\n"
    result += " <Point>\n"
    result += "  <coordinates>"+ str(long) + ","+ str(lat) + ",0" +"</coordinates>\n"
    result += " </Point>\n"
    result += "</Placemark>"
    return result

#CREATE A LINE TO CONNECT CITY TO FACILITY
def createLine(name, description, style, coordCity, coordFacility):  #create a line to connect city to facility
    result =  ""
    result += "<Placemark>\n"
    result += "   <name>" + name + "</name>\n"
    result += "   <description>" + description + "</description>\n"
    if (len(style) > 0):
        result += "   <styleUrl>#" + style + "</styleUrl>\n"
    result += "   <LineString>\n"
    result += "     <coordinates>" + coordCity + "," + coordFacility + "</coordinates>\n"
    result += "   </LineString>\n"
    result += "</Placemark>"
    return result

#RETURN A RANDOM URL FOR THE FACILITY ICON
def randomUrl():
    num = random.randint(0,7) #gets a random number between 0 and 8
    urlList = ["https://www.gstatic.com/earth/images/stockicons/190201-2013-alien_4x.png", "https://www.gstatic.com/earth/images/stockicons/190201-2074-checkmark_4x.png", "https://www.gstatic.com/earth/images/stockicons/190201-2163-historic-building_4x.png", "https://www.gstatic.com/earth/images/stockicons/190201-2396-x-cross_4x.png","https://www.gstatic.com/earth/images/stockicons/190201-2115-farm-barn_4x.png" , "https://www.gstatic.com/earth/images/stockicons/190201-2276-real-estate_4x.png", "https://www.gstatic.com/earth/images/stockicons/190201-2156-heart_4x.png", "https://www.gstatic.com/earth/images/stockicons/190201-2182-info_4x.png"]
    url = urlList[num] #gets the url at the index of the random number
    return url

#LOOP THROUGH TO CREATE KML CODE    
def display(facilities, cityList, distanceList, coordList, r):  #loop through to create kml code
    
    #CREATE FILE NAMED visualization___ with radius in blank space
    fileName = "visualization" + str(r) + ".kml" 

    #OPEN FILE
    visualization_KML = open(fileName, "w") 

    #KML file header 
    visualization_KML.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
    visualization_KML.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n")
    visualization_KML.write("<Document>\n\n")

    #CREATE LINE STYLES
    yellowLine = createLineStyle("yellowLine", "2", "7f00ffff")
    redLine = createLineStyle("redLine", "2", "ff0000ff")
    greenLine = createLineStyle("greenLine", "2", "ff00ff00")
    blueLine = createLineStyle("blueLine", "2", "ffff0000")
    purpleLine = createLineStyle("purpleLine", "2", "ff800080")
    orangeLine = createLineStyle("orangeLine", "2", "ff0080ff")
    pinkLine = createLineStyle("pinkLine", "2", "ff336699")
    brownLine = createLineStyle("brownLine", "2", "ffff00ff")

    #WRITE LINE STYLES TO FILE
    visualization_KML.write(yellowLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(redLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(greenLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(blueLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(purpleLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(orangeLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(pinkLine)
    visualization_KML.write("\n\n")
    visualization_KML.write(brownLine)

    #PINPOINT FACILITIES
    for facility in facilities:
        visualization_KML.write("\n\n")
        name = facility
        description = facility
        cityIndex = cityList.index(facility)
        coords = coordList[cityIndex]
        randomPin = randomUrl()
        cityKML = createFacilityPoint(name, description, coords, randomPin)
        visualization_KML.write(cityKML)
    
    #PINPOINT CITIES
    for city in cityList:
        if (city not in facilities):
            visualization_KML.write("\n\n")
            name = city
            description = city
            cityIndex = cityList.index(city)
            coords = coordList[cityIndex]
            cityKML = createCityPoint(name, description, coords)
            visualization_KML.write(cityKML)
    
    #CREATE LINES FROM CITY TO FACILITY
    non_facility_cities = [city for city in cityList if city not in facilities]
    count = 0
    for city in non_facility_cities:
        closestD = float('inf')
        for facility in facilities:
            newDist = getDistance(cityList, distanceList, city, facility)
            if (newDist < closestD):
                closestD = newDist
                bestFacility = facility
                count += 1
        closestFacility = bestFacility
        rawCityCoords = getCoordinates(cityList, coordList, city)
        rawFacilityCoords = getCoordinates(cityList, coordList, closestFacility)
        realCityCoords = '-' + str(rawCityCoords[1]/100) +',' + str(rawCityCoords[0]/100) + ',0'
        realFacilityCoords = '-' + str(rawFacilityCoords[1]/100) +',' + str(rawFacilityCoords[0]/100) + ',0'
        name = city + " to " + closestFacility        
        style = randomStyle()
        description = 'A ' + style_desc(style) + ' from ' + name
        makeLine = createLine(name, description, style, realCityCoords, realFacilityCoords)
        visualization_KML.write(makeLine)
        visualization_KML.write("\n\n")

    #KML CLOSE FILE
    visualization_KML.write("</Document>\n")
    visualization_KML.write("</kml>")
    visualization_KML.close()

#
#   MAIN CODE
#
#

cityList = []
coordList = []
popList = []
distanceList = []
r = 800
loadData(cityList, coordList, popList, distanceList)
facilities = locateFacilities(cityList, distanceList, r)
display(facilities, cityList, distanceList, coordList, r)
