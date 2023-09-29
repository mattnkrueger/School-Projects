#Matthew Krueger
#project2phase1b.py submitted 4/24
#project2phase2b.py #due 5/2

import matplotlib.pyplot as plt
import numpy as np
import random
import math 

# =============================================================================
#            --------------------- phase 1: ---------------------
# =============================================================================

def createUserList(): #returns a list of dictionaries with user info
    f = open("ml-100k/u.user", "r") 
    userList = []  
    for users in f: 
        info = users.split('|') 
        user = {} 
        user['age'] = int(info[1]) 
        user['gender'] = info[2] 
        user['occupation'] = info[3]
        user['zip'] = info[4][:len(info[4])-1]
        userList.append(user)
    return userList

def createGenreList(): #returns a list of genres
    f = open("ml-100k/u.genre", "r")
    genreList = [] 
    for genres in f:
        info = genres.split('|')
        if info[0] != '\n':
            genreList.append(info[0])
    return genreList  

def createMovieList(): #returns a list of dictionaries with movie info
    f = open("ml-100k/u.item", encoding="windows-1252")
    movieList = [] 
    for movies in f:
        info = movies.split('|')
        movie = {}
        movie['title'] = info[1]
        movie['release date'] = info[2]
        movie['video release date'] = info[3]
        movie['IMDB url'] = info[4] 
        genreList = []
        i = 1
        while i < len(info):
            if str(info[i]).isdigit():
                genreList.append(int(info[i]))
            i += 1
        genreList.append(int(info[len(info)-1].replace('\n', '')))
        movie['genre'] = genreList
        movieList.append(movie)
    return movieList
        
def readRatings(): #returns a list of tuples with ratings info
    f = open("ml-100k/u.data", "r")
    ratingTuples = []
    for ratings in f:
        info = ratings.split()
        user = int(info[0])
        movie = int(info[1])
        rating = int(info[2])
        currentTuple = (user, movie, rating)
        ratingTuples.append(currentTuple)
    return ratingTuples

def createRatingsDataStructure(numUsers, numItems, ratingTuples): #returns a list of dictionaries with ratings info
    ratingsList1 = []
    ratingsList2 = []
    for i in range(numUsers):
        ratingsList1.append({})

    for i in range(numItems):
        ratingsList2.append({})

    for rating in ratingTuples:
        ratingsList1[rating[0]-1][rating[1]] = rating[2]
        ratingsList2[rating[1]-1][rating[0]] = rating[2]
    
    return [ratingsList1, ratingsList2]

def demGenreRatingFractions(userList, movieList, rLu, gender, ageRange, ratingRange):
    
    # Initialize the numerators and denominator of the to-be-computed fractions for all 19 genres
    numGenres = len(movieList[0]["genre"])
    numerator = [0]*numGenres
    denominator = 0
    
    # Walk down the user IDs, keeping in mind that they range from 1 through numUsers
    for i in range(len(userList)):
        
        # Check if this user fits the demographic constraints
        # If gender is "A", it does not matter what the user's gender is. 
        # Note that the user's age has to be strictly less than ageRange[1] for the user to qualify
        if ((gender == "A") or (userList[i]["gender"] == gender)) and (ageRange[0] <= userList[i]["age"] < ageRange[1]):
            
            # Update denominator by the number of movies this user has rated
            denominator = denominator + len(rLu[i])
            
            # Walk down the ratings provided by this user by using the provided ratings list rLu
            for movie in rLu[i]:
                
                # Store the rating user i+1 provides to movie in a variable called rating
                rating = rLu[i][movie]
                
                # Check if this rating is in the given range
                if (ratingRange[0] <= rating <= ratingRange[1]):
                
                    # movieList[movie-1] contains 19 bits representing the genres
                    # For each genre, update the denominator and if in rating range,
                    # update the numerator as well
                    j = 0
                    for bit in movieList[movie-1]["genre"]:
                        numerator[j] = numerator[j] + bit
                        j = j + 1
                       
    return [numerator[i]/denominator if denominator > 0 else None for i in range(len(numerator))]
    
#graph data
def plotGraphs(userList, movieList, ratingTuples, rLu, rLm, genreList):
    #choose genres
    selectedGenres = ["Action", "Comedy", "Drama", "Horror", "Romance"]

    #high ratings for all ages
    maleFractions = demGenreRatingFractions(userList, movieList, rLu, 'M', (0, 100), (4, 5))
    femaleFractions = demGenreRatingFractions(userList, movieList, rLu, 'F', (0, 100), (4, 5))
    colors = ['green','cyan']
    barWidth = 0.4
    r1 = np.arange(len(selectedGenres))
    r2 = [x + barWidth for x in r1]
    #Plot 
    for i, genre in enumerate(selectedGenres):
        genreIndex = genreList.index(genre)
        plt.bar(r1[i], maleFractions[genreIndex], color = colors[0], width = barWidth, label='Male' if i == 0 else None)
        plt.bar(r2[i], femaleFractions[genreIndex], color = colors[1], width = barWidth, label='Female' if i == 0 else None)
    plt.xticks([r + barWidth / 2 for r in range(len(selectedGenres))], selectedGenres)
    plt.title(f"High Ratings for All Users")
    plt.xlabel("Genre")
    plt.ylabel("Fraction of High Ratings")
    plt.legend()
    plt.show()

    #low ratings for all ages
    maleFractions = demGenreRatingFractions(userList, movieList, rLu, 'M', (0, 100), (1, 2))
    femaleFractions = demGenreRatingFractions(userList, movieList, rLu, 'F', (0, 100), (1, 2))
    colors = ['green','cyan']
    # Set bar width and x-positions
    barWidth = 0.4
    r1 = np.arange(len(selectedGenres))
    r2 = [x + barWidth for x in r1]
    # Plot bars
    for i, genre in enumerate(selectedGenres):
        genreIndex = genreList.index(genre)
        plt.bar(r1[i], maleFractions[genreIndex], color = colors[0], width = barWidth, label='Male' if i == 0 else None)
        plt.bar(r2[i], femaleFractions[genreIndex], color = colors[1], width = barWidth, label='Female' if i == 0 else None)
    # Set x-axis tick labels
    plt.xticks([r + barWidth / 2 for r in range(len(selectedGenres))], selectedGenres)
    plt.title(f"Low Ratings for All Users")
    plt.xlabel("Genre")
    plt.ylabel("Fraction of Low Ratings")
    plt.legend()
    plt.show()

    #all ratings for ages 20 up to not including 30
    maleFractions = demGenreRatingFractions(userList, movieList, rLu, 'M', (20, 30), (1, 5))
    femaleFractions = demGenreRatingFractions(userList, movieList, rLu, 'F', (20, 30), (1, 5))
    colors = ['green','cyan']
    barWidth = 0.4
    r1 = np.arange(len(selectedGenres))
    r2 = [x + barWidth for x in r1]
    for i, genre in enumerate(selectedGenres):
        genreIndex = genreList.index(genre)
        plt.bar(r1[i], maleFractions[genreIndex], color = colors[0], width = barWidth, label='Male' if i == 0 else None)
        plt.bar(r2[i], femaleFractions[genreIndex], color = colors[1], width = barWidth, label='Female' if i == 0 else None)
    plt.xticks([r + barWidth / 2 for r in range(len(selectedGenres))], selectedGenres)
    plt.title(f"Ratings for 20-30 Year Olds")
    plt.xlabel("Genre")
    plt.ylabel("Fraction of Ratings")
    plt.legend()
    plt.show()

    #all ratings for ages 50 up to not including 60
    maleFractions = demGenreRatingFractions(userList, movieList, rLu, 'M', (50, 60), (1, 5))
    femaleFractions = demGenreRatingFractions(userList, movieList, rLu, 'F', (50, 60), (1, 5))
    colors = ['green','cyan']
    barWidth = 0.4
    r1 = np.arange(len(selectedGenres))
    r2 = [x + barWidth for x in r1]
    for i, genre in enumerate(selectedGenres):
        genreIndex = genreList.index(genre)
        plt.bar(r1[i], maleFractions[genreIndex], color = colors[0], width = barWidth, label='Male' if i == 0 else None)
        plt.bar(r2[i], femaleFractions[genreIndex], color = colors[1], width = barWidth, label='Female' if i == 0 else None)
    plt.xticks([r + barWidth / 2 for r in range(len(selectedGenres))], selectedGenres)
    plt.title(f"Ratings for 50-60 Year Olds")
    plt.xlabel("Genre")
    plt.ylabel("Fraction of Ratings")
    plt.legend()
    plt.show()

# =============================================================================
#            --------------------- phase 2: ---------------------
# =============================================================================

#5 prediction algoritms:
# -----------------------------------------------------------------------------
# 1) Prediction Algorithm 1
def randomPrediction(u, m): #passes tests
    rating = random.randint(1,5) #generate a random rating
    randomRatingTuple = (u, m, rating)
    return rating

# 2) Prediction Algorithm 2
def meanUserRatingPrediction(u, m, rLu): #passes tests
    allRatings = [] #hold all the ratings
    moviesAndRatings = rLu[u-1] #get the movies and ratings for the user
    mArItems = moviesAndRatings.items() #get the items in the dictionary
    for movie, rating in mArItems:
        allRatings.append(rating)
    meanRating = sum(allRatings)/len(allRatings)
    return meanRating

# 3) Prediction Algorithm 3
def meanMovieRatingPrediction(u, m, rLm): #passes tests
    allRatings = [] #hold all the ratings
    usersAndRatings = rLm[m-1] #get the users and ratings for the movie
    uArItems = usersAndRatings.items() #get the items in the dictionary
    for user, rating in uArItems:
            allRatings.append(rating)
    if len(allRatings) == 0:
        return None
    meanRating = sum(allRatings)/len(allRatings)
    return meanRating

#4) Prediction Algorithm 4 
def demRatingPrediction(u,m,userList,rLu):
    #try to remove for index in indexes
    user = userList[u-1] #index u starts at 1, index u-1 starts at 0
    age = user['age'] #gets correct user age
    gender = user['gender']
    ageRange = (age-5, age+5)
    i = 0 #index of userList
    total_U_ratings = [] #holds the ratings of the users in the age range
    while i < len(userList): #goes through all users
        if (userList[i] != user) and (userList[i]['age'] <= age+5) and (userList[i]['age'] >= age-5) and (userList[i]['gender'] == gender): #if the person's age is in the range
            for movie, rating in rLu[i].items(): #goes through all the movies and ratings of the user
                if movie == m: #if the movie is the movie we are looking for
                    total_U_ratings.append(rating) #add the rating to the list
        i += 1
    if len(total_U_ratings) == 0: #if there are no ratings in the list
        return None
    else:
        return sum(total_U_ratings)/len(total_U_ratings) #return the average rating
    
#5) Prediction Algorithm 5
#helper function to get common genre(s) movies
def sameGenre(g1, g2):
    index = 0
    genreT_F = []
    while index < len(g1): #while the index is not at the end of the list
        if g1[index] == 1 and g1[index] == g2[index]:
            genreT_F.append(True) #if the genre is the same, add True to the list
            return True
        index += 1
    return False

def genreRatingPrediction(u, m, movieList, rLu): 
    movieIndex = movieList[m-1] #index m starts at 1, index m-1 starts at 0
    genreMovie = movieIndex['genre'] #gets the correct movie genre
    user = u-1
    moviesDictionaries = rLu[user] #gets the dictionary of movies and ratings for the user
    movieRatings = [] #holds the ratings for the movies in the same genre
    for movie, rating in moviesDictionaries.items(): #goes through all the movies and ratings for the user
        if (movie - 1) != (m-1): #if the movie is not the same movie
            genreNew = movieList[movie-1]['genre'] #gets the genre of the movie
            if sameGenre(genreMovie, genreNew) == True:
                movieRatings.append(rating)
    if len(movieRatings) == 0: #if there are no ratings in the list
        return None
    else:
        return sum(movieRatings)/len(movieRatings)
# -----------------------------------------------------------------------------
#functions for cross-validation
# -----------------------------------------------------------------------------
# 1) Split Data into Training and Testing Sets
def partitionRatings(rawRatings, testPercent):
    testSize = int(len(rawRatings) * testPercent / 100)
    testSet = random.sample(rawRatings, testSize)
    trainingSet = [rating for rating in rawRatings if rating not in testSet]
    return [trainingSet, testSet]

# 2) Calculate RMSE
def rmse(actualRatings, predictedRatings):
    sqrDiff = []
    for i in range(len(actualRatings)):
        if predictedRatings[i] is not None:
            sqrDiff.append((actualRatings[i] - predictedRatings[i])**2)
    mean = sum(sqrDiff) / len(sqrDiff)
    return math.sqrt(mean)

# 3) Prediction Function to find r'
#helper functions to call each of the 5 prediction algorithms 
def algo1(testSet):
    rPrimes = [] 
    for u, m, r in testSet:
        rPrimes.append(randomPrediction(u, m)) 
    return rPrimes

def algo2(training_rLu, testSet):
    rPrimes = []
    for u, m, r in testSet:
        rPrimes.append(meanUserRatingPrediction(u, m, training_rLu))
    return rPrimes

def algo3(training_rLm, testSet):
    rPrimes = []
    for u, m, r in testSet:
        rPrimes.append(meanMovieRatingPrediction(u, m, training_rLm))
    return rPrimes

def algo4(training_rLu, userList, testSet): #note, this does not pass all tests due to rounding errors
    rPrimes = []
    for u, m, r in testSet:
        rPrimes.append(demRatingPrediction(u, m, userList, training_rLu))
    return rPrimes

def algo5(training_rLu, movieList, testSet):
    rPrimes = []
    for u, m, r in testSet:
        rPrimes.append(genreRatingPrediction(u, m, movieList, training_rLu))
    return rPrimes

def rPrimeMain(userList, movieList, training_rLu, training_rLm, testSet):
    groundTruth = testSet #keeps unmodified copy of testSet
    #convert groundTruth to list of 20,000 r values
    rValues = []
    for u,m,r in groundTruth:
        rValues.append(r)
    #call each of the 5 prediction algorithms
    rP1 = algo1(testSet)
    rP2 = algo2(training_rLu, testSet)
    rP3 = algo3(training_rLm, testSet)
    rP4 = algo4(training_rLu, userList, testSet)
    rP5 = algo5(training_rLu, movieList, testSet)

    #add all rP to list to iterate through to get rmse
    rPlist = [rP1, rP2, rP3, rP4, rP5] #length 5 with each element being a list of 20,000 r' values
    
    rmseList = [] #list of 5 rmse values (one for each prediction algorithm)
    for i in range(len(rPlist)):
        if rPlist[i] == None:
            rmseList.append(None)
        rmseList.append(rmse(rValues, rPlist[i]))
    return rmseList

# =============================================================================
#            --------------------- Main Code: ---------------------
# =============================================================================

userList = createUserList()
movieList = createMovieList()
ratingTuples = readRatings()
rLu, rLm = createRatingsDataStructure(len(userList), len(movieList), ratingTuples)
genreList = createGenreList()
# plotGraphs(userList, movieList, ratingTuples, rLu, rLm, genreList)

#rmse lists 
rmse1_list = []
rmse2_list = []
rmse3_list = []
rmse4_list = []
rmse5_list = []
num = 20

#repeat 10 times
for i in range(10):
    [trainingSet, testSet] = partitionRatings(ratingTuples, num) #split data into new training and testing sets
    [training_rLu, training_rLm] = createRatingsDataStructure(len(userList), len(movieList), trainingSet) #create ratings data structure for training set
    allRmse = rPrimeMain(userList, movieList, training_rLu, training_rLm, testSet) #call rPrimeMain function to get rmse values for each prediction algorithm
    rmse1_list.append(allRmse[0])
    rmse2_list.append(allRmse[1])
    rmse3_list.append(allRmse[2])
    rmse4_list.append(allRmse[3])
    rmse5_list.append(allRmse[4])

# visualize the 5 RMSE values using a boxplot
def draw_boxplot(data, labels):
    plt.boxplot(x=data, labels=labels)
    plt.title("Algorithm performance comparison")
    plt.ylabel("RMSE values")
    plt.show()
    plt.close()
data = [rmse1_list, rmse2_list, rmse3_list, rmse4_list, rmse5_list]
labels = ["Algorithm1", "Algorithm2", "Algorithm3", "Algorithm4", "Algorithm5"]
draw_boxplot(data, labels)


print('code executed')